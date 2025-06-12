#include <reg51.h>
#include <intrins.h>
#include <stdio.h>
#include "st7920.h"
#include "tlc2543.h"
#include "delay.h"
#include "math.h"

#define MAX_N 30000  // 最大累加值, 即最大占空比
#define MAX_SPEED 100  // 最大转速, 单位 r/s, 测试得到

#define TICK_TIME_US 100  // 每个tick周期 = 100us
#define TICK_PER_MS (1000/TICK_TIME_US)  // 每毫秒的tick数 = 1000us / 100us = 10
#define TIMER_PRESET_VALUE TICK_TIME_US  // 定时器的预设值, 设为此值 = 100us = 0.1ms 时, 定时器每个tick周期触发一次

int targetSpeed;  // 目标转速
int motorSpeed;  // 实时转速
unsigned int motorN = 0;  // 越大转越快


// 绘制边框
void drawBorder(void) {
    LCD_startGraphic();  // 进入绘图模式
    
    unsigned char t, y;
    
    // Draw top border
    for (y = 0; y < 2; y++) {  // 宽度为2, 0-1
        LCD_set_graphic_address(0, y);  //设置绘图模式的起始地址 (重要；以实际显示屏左上角为原点的坐标系)
        for (t = 0; t < 16; t++) {  // 一次写两个字节
            LCD_write_data(0xFF);  // Fill entire row
            LCD_write_data(0xFF);  // Fill entire row
			// 写入两个字节，系统内部 x 地址自动加 1
        }
    }
    
    // Draw bottom border
    for (y = 62; y < 64; y++) {  // 宽度为2, 62-63
        LCD_set_graphic_address(0, y);
        for (t = 0; t < 16; t++) {
            LCD_write_data(0xFF);  // Fill entire row
            LCD_write_data(0xFF);  // Fill entire row
        }
    }
    
    // Draw left border
    for (y = 2; y < 62; y++) {  // 高度为60, 2-61
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

// 绘制电机速度条
// 位置为 x = 10~109, y = 5~12
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
    sprintf(printText, "目标转速 %3d r/s", targetSpeed);
    LCD_write_string(1 << 8, printText);
    sprintf(printText, "实时转速 %3d r/s", motorSpeed);
    LCD_write_string(2 << 8, printText);
    sprintf(printText, "  功率   %f%  ", (motorN / (MAX_N/100.0)));
    LCD_write_string(3 << 8, printText);
    
    // Display motor speed as bar
    displayMotorSpeedBar(motorSpeed, MAX_SPEED);
}
sbit motorDrive = P0^5;  // 电机驱动引脚

// PWM
// 每个tick周期触发一次
// 使用累加进位法，输出0/1，平滑控制电机驱动
void motorControl(void)
{
	// motorS 是累加器
	// motorN 是更新的 每个周期内高电平维持的时间长度
	// MAX_N 是最大累加值

	static unsigned int motorS = 0;  // 累加器
	motorS += motorN;

	if(motorS > MAX_N)
	{
		motorS -= MAX_N;
		motorDrive = 0;  // 电机停止
	}
	else
	{
		motorDrive = 1;  // 电机运行
	}
}

float integral = 0, last_error = 0;
const float Kp = 0.015, Ki = 0.007, Kd = 0.007;

// PID算法更新函数
void updateN(void)
{
	// 计算当前误差
	float error, derivative, output;
	error = targetSpeed - motorSpeed;
	
	// 计算积分和微分
	integral += error;
	derivative = error - last_error;
	last_error = error;

	// 计算输出
	output = Kp * error + Ki * integral + Kd * derivative;

	// 限制输出范围
	if(output > 1) output = 1;
	if(output < 0) output = 0;

	// 更新 motorN, 即每个周期内高电平维持的时间长度。
	motorN = (unsigned int) (MAX_N * output);
}

// 读取目标转速, 通过ADC读取
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


// 定时器中断服务函数
// 每个tick周期 = 100us = 0.1ms
// 功能：计算时间; 控制电机驱动输出
unsigned long int ticks = 0;
void timerISR(void) interrupt 1
{	
	ticks += 1;  // 更新ticks
	motorControl();  // 累加进位法控制电机驱动输出

	TH0 = (65536 - TIMER_PRESET_VALUE) / 256; // 设置定时器的高字节
	TL0 = (65536 - TIMER_PRESET_VALUE) % 256; // 设置定时器的低字节
}

// 外部中断服务函数
// 每次电机转一圈，触发一次
unsigned int motorRound;
void motorISR(void) interrupt 0 using 0
{	
	motorRound += 1;
}

// 更新电机速度
// 每0.5s更新一次
void updateMotorSpeed(void)
{
	static unsigned long int lastTick;	// 上次更新时的ticks值
	float interval = (ticks - lastTick) * TICK_TIME_US / 1000000.0;  // 计算两次更新的时间间隔

	if(interval >= 0.5)  // 大于0.5s时更新
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

	// 设置定时器的工作模式和预设值
	// 定时器0中断触发间隔为 100us = 0.1ms
	TMOD = 0x01; // 定时器0工作在模式1
	TH0 = (65536 - TIMER_PRESET_VALUE) / 256; // 设置定时器的高字节
	TL0 = (65536 - TIMER_PRESET_VALUE) % 256; // 设置定时器的低字节
	TR0 = 1; // 开启定时器并使能定时器中断
	ET0 = 1;
	IT0 = 1; // 外部中断0为低电平触发
  EX0 = 1; // 开EX0中断
	EA = 1;

	LCD_init();
	//LCD_write_string(0 << 8,"实验7&8  PID电机");
	LCD_write_string(1 << 8,"目标转速 000 r/s");
	LCD_write_string(2 << 8,"实时转速 000 r/s");
	LCD_write_string(3 << 8,"    运行中      ");
	//drawLCD();
	
	// 定时器中断时，更新电机速度
	// 外部中断时，更新电机圈数 (用于计算速度)
	// 主循环中，更新目标转速，更新电机控制信号，更新屏幕显示
	while(1)
	{
		//readTargetSpeed();  // 读取目标转速 (通过ADC读取)
		updateMotorSpeed();  // 更新电机速度 (通过外部中断，计算电机转速)
		updateN();  // 更新占空比 (通过PID算法，更新motorN)
		printScreen();
		wait_ms(500);
	}

	return 0;
}
