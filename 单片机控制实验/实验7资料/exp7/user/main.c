#include <reg51.h>
#include <intrins.h>
#include <stdio.h>
#include "st7920.h"
#include "tlc2543.h"
#include "delay.h"
#include "math.h"

#define MAX_N 30000  // ����ۼ�ֵ, �����ռ�ձ�
#define MAX_SPEED 100  // ���ת��, ��λ r/s, ���Եõ�

#define TICK_TIME_US 100  // ÿ��tick���� = 100us
#define TICK_PER_MS (1000/TICK_TIME_US)  // ÿ�����tick�� = 1000us / 100us = 10
#define TIMER_PRESET_VALUE TICK_TIME_US  // ��ʱ����Ԥ��ֵ, ��Ϊ��ֵ = 100us = 0.1ms ʱ, ��ʱ��ÿ��tick���ڴ���һ��

int targetSpeed;  // Ŀ��ת��
int motorSpeed;  // ʵʱת��
unsigned int motorN = 0;  // Խ��תԽ��


// ���Ʊ߿�
void drawBorder(void) {
    LCD_startGraphic();  // �����ͼģʽ
    
    unsigned char t, y;
    
    // Draw top border
    for (y = 0; y < 2; y++) {  // ���Ϊ2, 0-1
        LCD_set_graphic_address(0, y);  //���û�ͼģʽ����ʼ��ַ (��Ҫ����ʵ����ʾ�����Ͻ�Ϊԭ�������ϵ)
        for (t = 0; t < 16; t++) {  // һ��д�����ֽ�
            LCD_write_data(0xFF);  // Fill entire row
            LCD_write_data(0xFF);  // Fill entire row
			// д�������ֽڣ�ϵͳ�ڲ� x ��ַ�Զ��� 1
        }
    }
    
    // Draw bottom border
    for (y = 62; y < 64; y++) {  // ���Ϊ2, 62-63
        LCD_set_graphic_address(0, y);
        for (t = 0; t < 16; t++) {
            LCD_write_data(0xFF);  // Fill entire row
            LCD_write_data(0xFF);  // Fill entire row
        }
    }
    
    // Draw left border
    for (y = 2; y < 62; y++) {  // �߶�Ϊ60, 2-61
        LCD_set_graphic_address(0, y);
        LCD_write_data(0xC0);  // Fill first column
        LCD_write_data(0x00);  // A half byte for extra column
    }
    
    // Draw right border
    for (y = 2; y < 62; y++) {
        LCD_set_graphic_address(15 * 8, y);
        LCD_write_data(0x00);  // A half byte for the second last position
        LCD_write_data(0x03);  // Fill last column
    }
    
    LCD_endGraphic();
}

// ���Ƶ���ٶ���
// λ��Ϊ x = 10~109, y = 5~12
void displayMotorSpeedBar(int currentSpeed, int maxSpeed) {
    int barLength = (100 * currentSpeed) / maxSpeed;
    int barHeight = 8; // Define the height of the bar
    
    LCD_startGraphic();
    
	// x = 10~109, y = 5~12
    for (int y = 5; y < 4 + barHeight; y++) {  // Positioning vertically in the screen
        LCD_set_graphic_address(10, y);  // Start after the left border
		for (int t = 0; t < barLength / 8; t++) {  // Calculating how many full bytes
            LCD_write_data(0xFF);  // Fill byte
        }
        LCD_write_data(0xFF << (8 - (barLength % 8)));  // Fill remaining part of byte if any portion left
    }
    LCD_endGraphic();
}

void printScreen(void) {
    // Draw border
    drawBorder();

	char printText[16];
    sprintf(printText, "Ŀ��ת�� %3d r/s", targetSpeed);
    LCD_write_string(1 << 8, printText);
    sprintf(printText, "ʵʱת�� %3d r/s", motorSpeed);
    LCD_write_string(2 << 8, printText);
    sprintf(printText, "  ����   %f%  ", (motorN / (MAX_N/100.0)));
    LCD_write_string(3 << 8, printText);
    
    // Display motor speed as bar
    displayMotorSpeedBar(motorSpeed, MAX_SPEED);
}
sbit motorDrive = P0^5;  // �����������

// PWM
// ÿ��tick���ڴ���һ��
// ʹ���ۼӽ�λ�������0/1��ƽ�����Ƶ������
void motorControl(void)
{
	// motorS ���ۼ���
	// motorN �Ǹ��µ� ÿ�������ڸߵ�ƽά�ֵ�ʱ�䳤��
	// MAX_N ������ۼ�ֵ

	static unsigned int motorS = 0;  // �ۼ���
	motorS += motorN;

	if(motorS > MAX_N)
	{
		motorS -= MAX_N;
		motorDrive = 0;  // ���ֹͣ
	}
	else
	{
		motorDrive = 1;  // �������
	}
}

float integral = 0, last_error = 0;
const float Kp = 0.015, Ki = 0.007, Kd = 0.007;

// PID�㷨���º���
void updateN(void)
{
	// ���㵱ǰ���
	float error, derivative, output;
	error = targetSpeed - motorSpeed;
	
	// ������ֺ�΢��
	integral += error;
	derivative = error - last_error;
	last_error = error;

	// �������
	output = Kp * error + Ki * integral + Kd * derivative;

	// ���������Χ
	if(output > 1) output = 1;
	if(output < 0) output = 0;

	// ���� motorN, ��ÿ�������ڸߵ�ƽά�ֵ�ʱ�䳤�ȡ�
	motorN = (unsigned int) (MAX_N * output);
}

// ��ȡĿ��ת��, ͨ��ADC��ȡ
void readTargetSpeed(void)
{
	unsigned int value;
	int newSpeed;

	value = read2543(0);
	value = value > 4000 ? 4000 : value;
	newSpeed = value / 4000.0 * MAX_SPEED;

	if(newSpeed - targetSpeed > 1 || targetSpeed - newSpeed > 1)
	{
		targetSpeed = newSpeed;
		integral = 0;
		last_error = 0;
	}
}


// ��ʱ���жϷ�����
// ÿ��tick���� = 100us = 0.1ms
// ���ܣ�����ʱ��; ���Ƶ���������
unsigned long int ticks = 0;
void timerISR(void) interrupt 1
{	
	ticks += 1;  // ����ticks
	motorControl();  // �ۼӽ�λ�����Ƶ���������

	TH0 = (65536 - TIMER_PRESET_VALUE) / 256; // ���ö�ʱ���ĸ��ֽ�
	TL0 = (65536 - TIMER_PRESET_VALUE) % 256; // ���ö�ʱ���ĵ��ֽ�
}

// �ⲿ�жϷ�����
// ÿ�ε��תһȦ������һ��
unsigned int motorRound;
void motorISR(void) interrupt 0 using 0
{	
	motorRound += 1;
}

// ���µ���ٶ�
// ÿ0.5s����һ��
void updateMotorSpeed(void)
{
	static unsigned long int lastTick;	// �ϴθ���ʱ��ticksֵ
	float interval = (ticks - lastTick) * TICK_TIME_US / 1000000.0;  // �������θ��µ�ʱ����

	if(interval >= 0.5)  // ����0.5sʱ����
	{
		lastTick = ticks;
		motorSpeed = motorRound / interval;
		motorRound = 0;	
	}
}

void wait_ms(unsigned int ms)
{
	unsigned long int now = ticks;
	while(ticks - now < ms * TICK_PER_MS);
}

void drawLCD(void)
{
	unsigned char i,j;
	LCD_startGraphic();
	for(i=0;i<32;i++)
	{
		LCD_set_graphic_address(0, i);
		for(j=0;j<8;j++)
		{
			LCD_write_data(0x00);
			LCD_write_data(0x00);
		}
	}
	for(i=0;i<32;i++)
	{
		LCD_set_graphic_address(8, i);
		for(j=0;j<8;j++)
		{
			LCD_write_data(0x00);
			LCD_write_data(0x00);
		}
	}
	LCD_endGraphic();
}

void readTargetSpeed_ByKey(void)
{	
	int newSpeed;
	// fill in this part to set a newSpeed
	// ...
	// ...
	if (newSpeed - targetSpeed > 1 || targetSpeed - newSpeed > 1)
	{
		targetSpeed = newSpeed;
		integral = 0;
		last_error = 0;
	}
}

int main()
{
	unsigned char i;

	// ���ö�ʱ���Ĺ���ģʽ��Ԥ��ֵ
	// ��ʱ��0�жϴ������Ϊ 100us = 0.1ms
	TMOD = 0x01; // ��ʱ��0������ģʽ1
	TH0 = (65536 - TIMER_PRESET_VALUE) / 256; // ���ö�ʱ���ĸ��ֽ�
	TL0 = (65536 - TIMER_PRESET_VALUE) % 256; // ���ö�ʱ���ĵ��ֽ�
	TR0 = 1; // ������ʱ����ʹ�ܶ�ʱ���ж�
	ET0 = 1;
	IT0 = 1; // �ⲿ�ж�0Ϊ�͵�ƽ����
  EX0 = 1; // ��EX0�ж�
	EA = 1;

	LCD_init();
	//LCD_write_string(0 << 8,"ʵ��7&8  PID���");
	LCD_write_string(1 << 8,"Ŀ��ת�� 000 r/s");
	LCD_write_string(2 << 8,"ʵʱת�� 000 r/s");
	LCD_write_string(3 << 8,"    ������      ");
	//drawLCD();
	
	// ��ʱ���ж�ʱ�����µ���ٶ�
	// �ⲿ�ж�ʱ�����µ��Ȧ�� (���ڼ����ٶ�)
	// ��ѭ���У�����Ŀ��ת�٣����µ�������źţ�������Ļ��ʾ
	while(1)
	{
		//readTargetSpeed();  // ��ȡĿ��ת�� (ͨ��ADC��ȡ)
		updateMotorSpeed();  // ���µ���ٶ� (ͨ���ⲿ�жϣ�������ת��)
		updateN();  // ����ռ�ձ� (ͨ��PID�㷨������motorN)
		printScreen();
		wait_ms(500);
	}

	return 0;
}
