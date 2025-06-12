#include <reg51.h>
#include "main.h"
#include "delay.h"
#include "ch451.h"
#include "ds18b20.h"

char flashDigit = 0;
sbit BUZZER1=P2^0;
sbit P20=P2^0;
unsigned int buz_cnt = 0;
bit flashFlag = 0;
bit buz_flag=0;
bit cnt_or_tmp = 0;
unsigned char dig4, dig3, dig2, dig1;

// ��ʾ����ʱ
void printNum()
{
	// 0-3λ������ʾ����ʱ
	ch451_write(CH451_DIG0 | dig1);
	ch451_write(CH451_DIG1 | dig2);
	ch451_write(CH451_DIG2 | (dig3+128)); //���
	ch451_write(CH451_DIG3 | dig4);

	if(flashDigit > 0 && flashFlag)
	// DIG3 ͨ������λ��*0x0100��ѡ����ʾ����������ĸ�����27��ʾ����ʾ
	// flashFlag ��ת��ʵ����˸
		ch451_write((CH451_DIG3 + flashDigit * 0x0100) | 27);
	flashFlag = !flashFlag;

	// ��λ��ղ���ʾ
	ch451_write(CH451_DIG4 | 27);
	ch451_write(CH451_DIG5 | 27);
	ch451_write(CH451_DIG6 | 27);
	ch451_write(CH451_DIG7 | 27);
}

void printTemperature(unsigned int temp)
{
	// 0-3λ������ʾ�¶�
	unsigned char digit;
	
	digit = temp % 10;
	ch451_write(CH451_DIG0 | digit);
	digit = (temp/=10) % 10;
	ch451_write(CH451_DIG1 | (digit + 128));	// ���
	digit = (temp/=10) % 10;
	ch451_write(CH451_DIG2 | digit);
	
	// ��λ��ղ���ʾ
	ch451_write(CH451_DIG3 | 27);
	ch451_write(CH451_DIG4 | 27);
	ch451_write(CH451_DIG5 | 27);
	ch451_write(CH451_DIG6 | 27);
	ch451_write(CH451_DIG7 | 27);
}

// ����DS18B20
void startTemperature(void){
	reset_ds18b20();
	delay_ms(1);
	writeByte_ds18b20(0xCC);
	writeByte_ds18b20(0x44);
}

unsigned int readTemperature(void){
	unsigned int tempInt;
	float tempFloat;
	unsigned char a, b;

	reset_ds18b20();
	delay_ms(1);
	writeByte_ds18b20(0xCC);
	writeByte_ds18b20(0xBE);
	a = readByte_ds18b20();
	b = readByte_ds18b20();
	tempInt = b;
	tempInt <<= 8;
	tempInt = tempInt | a;
	tempFloat = tempInt * 0.0625;
	tempInt = tempFloat * 10 + 0.5;
	return tempInt;
}

unsigned int seconds;
unsigned int displayNum;
unsigned char count;
bit paused = 0;

unsigned int BEEP1;

#define TIMER_PRESET_VALUE 50000  // 1/1000000 s = 1us, 50000us = 50ms
#define TIMER_1_INT 10000	// 10ms

// ��ʱ���жϷ�����
void timer1_ISR(void) interrupt 3
{
	//P20=1;
	TH1 = (65536 - TIMER_1_INT) / 256; // ���ö�ʱ���ĸ��ֽ�
	TL1 = (65536 - TIMER_1_INT) % 256; // ���ö�ʱ���ĵ��ֽ�
	if (buz_cnt == 300) // һ���жϼ���һ�Σ�3��300��
	// ��λflag��cnt��P20�͵�ƽ���رն�ʱ��
	{
		buz_flag = 0;
		buz_cnt = 0;
		P20=0;
		// shut int
		TR1 = 0;	// �رն�ʱ��1
		ET1 = 0;	// disable timer1 int
		IT1 = 1;	// �ߵ�ƽ����
		EX1 = 0;	// disable timer1 int
	}
}

void timer0_ISR(void) interrupt 1 
{	
	count += 1;
	if(count % 20 == 0)
	{
		if(!paused)
		{
			
			if(seconds > 0)
			{
				seconds -= 1;
				// ����ʱ��0����buz_flagΪ1
				if (seconds == 0)
					buz_flag = 1;
			}
			
			dig4 = seconds / 600 % 10;
			dig3 = seconds / 60 % 10;
			dig2 = seconds % 60 / 10;
			dig1 = seconds % 10;
		}
	}


	// ͨ��cnt_or_tmp�ж���ʾ�¶Ȼ��ǵ���ʱ
	if(count % 8 == 0)
	{
		if (cnt_or_tmp)
			// ��ʾ����ʱ
			printNum();
		else
			// ��ʾ�¶�
			printTemperature(readTemperature());
	}


	// ����ʱ��0����flagΪ1
	// ��ʱ����������
	if (seconds == 0 && buz_flag == 1)
	{
		TH1 = (65536 - TIMER_1_INT) / 256; // ���ö�ʱ���ĸ��ֽ�
		TL1 = (65536 - TIMER_1_INT) % 256; // ���ö�ʱ���ĵ��ֽ�
		
		P20=1;	// �򿪷�������P20�ߵ�ƽ�����������������Ϊ�͵�ƽ

		TR1 = 1;	// ������ʱ��
		ET1 = 1;	// enable timer1 int
		IT1 = 0;	// �͵�ƽ����
		EX1 = 1;	// enable timer1 int

		buz_flag = 0;	// ������������flag��������ظ������if���
	}
	
	// ����
	TH0 = (65536 - TIMER_PRESET_VALUE) / 256; // ���ö�ʱ���ĸ��ֽ�
	TL0 = (65536 - TIMER_PRESET_VALUE) % 256; // ���ö�ʱ���ĵ��ֽ�
}


// ���¼����ж�
bit setTimeMode = 0;
void keydown(void) interrupt 0
{
	unsigned char key;
	unsigned char num;
	// if (buz_flag == 0)
	// 	{P20=0;}
	// else
	// 	{P20=1;}

	
	EA = 0;
	key = ch451_read();
	EA = 1;

	// ��ʼ���� / ��������
	if(key == KEY_16)
	{
		setTimeMode = 1;
		paused = 1;
		flashDigit = 4;
	}
	// �������
	else if(key == KEY_15)
	{
		seconds = dig4 * 600 + dig3 * 60 + dig2 * 10 + dig1;
		setTimeMode = 0;	// ��������ʱ��
		paused = 0;		// ��ʼ��ʱ
		flashDigit = 0;	// ��˸λ���㣬������˸

		buz_flag=0;	// ����flag
	}
	// PAUSE/RUN
	else if(key == KEY_13 && !setTimeMode)	// ����KEY_13���� ������ʱ��ģʽ
	{
		paused = !paused;
	}
	else if (key == KEY_12)	// �л�cnt_or_tmp����ʾ�¶Ȼ��ߵ���ʱ
	{
		cnt_or_tmp = !cnt_or_tmp;
	}
	else	// ���ּ�, KEY1/2/3/5/6/7/9/10/11/14
	{
		if(setTimeMode)
		{
			if(key == KEY_1)num=7;
			else if(key == KEY_2)num=8;
			else if(key == KEY_3)num=9;
			else if(key == KEY_5)num=4;
			else if(key == KEY_6)num=5;
			else if(key == KEY_7)num=6;
			else if(key == KEY_9)num=1;
			else if(key == KEY_10)num=2;
			else if(key == KEY_11)num=3;
			else if(key == KEY_14)num=0;
			else return;
	
			if(flashDigit == 4)
				dig4 = num;
			if(flashDigit == 3)
				dig3 = num;
			if(flashDigit == 2)
			{
				if(num >= 6) num = 5;	// ������ĸ�λ�����Ϊ5
				dig2 = num;
			}
			if(flashDigit == 1)	// ��ĵ�λ
				dig1 = num;
			// ��˸λ�ݼ�
			flashDigit = flashDigit > 1 ? flashDigit-1 : 4;
		}
	}
}

void main(void)
{
	// ��ʼ��buz_flag, buz_cnt, P20
	buz_cnt = 0;
	buz_flag = 0;
	P20=0;

	ch451_write(0x201);
    ch451_init();
    // ϵͳ�����趨����ʾ����ʹ�ܣ�����ɨ��ʹ�ܣ�����Ĭ��
    ch451_write(0x403);
    // ��ʾ�����趨��ѡ�� BCD ���뷽ʽ������Ĭ��
    ch451_write(0x580);

	// ���ö�ʱ���Ĺ���ģʽ��Ԥ��ֵ

	TMOD = 0x11;

	TH0 = (65536 - TIMER_PRESET_VALUE) / 256; // ���ö�ʱ���ĸ��ֽ�
	TL0 = (65536 - TIMER_PRESET_VALUE) % 256; // ���ö�ʱ���ĵ��ֽ�

	// ������ʱ����ʹ�ܶ�ʱ���ж�
	TR0 = 1;	// ������ʱ��
	ET0 = 1;	// enable timer0 int
	IT0 = 0;	// �͵�ƽ����
    EX0 = 1;	// enable timer0 int
	
	EA = 1;	//	�����ж�
	

	
	while(1)
	{
		startTemperature();
		delay_ms(1000);
		printTemperature(readTemperature());
	}
}

