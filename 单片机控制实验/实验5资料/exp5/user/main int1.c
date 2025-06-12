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

// 显示倒计时
void printNum()
{
	// 0-3位用作显示倒计时
	ch451_write(CH451_DIG0 | dig1);
	ch451_write(CH451_DIG1 | dig2);
	ch451_write(CH451_DIG2 | (dig3+128)); //点号
	ch451_write(CH451_DIG3 | dig4);

	if(flashDigit > 0 && flashFlag)
	// DIG3 通过加上位数*0x0100来选择显示的数码管是哪个，或27表示不显示
	// flashFlag 翻转，实现闪烁
		ch451_write((CH451_DIG3 + flashDigit * 0x0100) | 27);
	flashFlag = !flashFlag;

	// 高位清空不显示
	ch451_write(CH451_DIG4 | 27);
	ch451_write(CH451_DIG5 | 27);
	ch451_write(CH451_DIG6 | 27);
	ch451_write(CH451_DIG7 | 27);
}

void printTemperature(unsigned int temp)
{
	// 0-3位用作显示温度
	unsigned char digit;
	
	digit = temp % 10;
	ch451_write(CH451_DIG0 | digit);
	digit = (temp/=10) % 10;
	ch451_write(CH451_DIG1 | (digit + 128));	// 点号
	digit = (temp/=10) % 10;
	ch451_write(CH451_DIG2 | digit);
	
	// 高位清空不显示
	ch451_write(CH451_DIG3 | 27);
	ch451_write(CH451_DIG4 | 27);
	ch451_write(CH451_DIG5 | 27);
	ch451_write(CH451_DIG6 | 27);
	ch451_write(CH451_DIG7 | 27);
}

// 重置DS18B20
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

// 定时器中断服务函数
void timer1_ISR(void) interrupt 3
{
	//P20=1;
	TH1 = (65536 - TIMER_1_INT) / 256; // 设置定时器的高字节
	TL1 = (65536 - TIMER_1_INT) % 256; // 设置定时器的低字节
	if (buz_cnt == 300) // 一次中断计数一次，3秒300次
	// 置位flag，cnt，P20低电平，关闭定时器
	{
		buz_flag = 0;
		buz_cnt = 0;
		P20=0;
		// shut int
		TR1 = 0;	// 关闭定时器1
		ET1 = 0;	// disable timer1 int
		IT1 = 1;	// 高电平触发
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
				// 倒计时到0，置buz_flag为1
				if (seconds == 0)
					buz_flag = 1;
			}
			
			dig4 = seconds / 600 % 10;
			dig3 = seconds / 60 % 10;
			dig2 = seconds % 60 / 10;
			dig1 = seconds % 10;
		}
	}


	// 通过cnt_or_tmp判断显示温度还是倒计时
	if(count % 8 == 0)
	{
		if (cnt_or_tmp)
			// 显示倒计时
			printNum();
		else
			// 显示温度
			printTemperature(readTemperature());
	}


	// 倒计时到0，且flag为1
	// 此时开启蜂鸣器
	if (seconds == 0 && buz_flag == 1)
	{
		TH1 = (65536 - TIMER_1_INT) / 256; // 设置定时器的高字节
		TL1 = (65536 - TIMER_1_INT) % 256; // 设置定时器的低字节
		
		P20=1;	// 打开蜂鸣器，P20高电平，计数到三秒后重设为低电平

		TR1 = 1;	// 开启定时器
		ET1 = 1;	// enable timer1 int
		IT1 = 0;	// 低电平触发
		EX1 = 1;	// enable timer1 int

		buz_flag = 0;	// 进入后马上清除flag，否则会重复进入该if语句
	}
	
	// 重设
	TH0 = (65536 - TIMER_PRESET_VALUE) / 256; // 设置定时器的高字节
	TL0 = (65536 - TIMER_PRESET_VALUE) % 256; // 设置定时器的低字节
}


// 按下键盘中断
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

	// 开始设置 / 重置输入
	if(key == KEY_16)
	{
		setTimeMode = 1;
		paused = 1;
		flashDigit = 4;
	}
	// 设置完毕
	else if(key == KEY_15)
	{
		seconds = dig4 * 600 + dig3 * 60 + dig2 * 10 + dig1;
		setTimeMode = 0;	// 结束设置时间
		paused = 0;		// 开始计时
		flashDigit = 0;	// 闪烁位清零，不再闪烁

		buz_flag=0;	// 重置flag
	}
	// PAUSE/RUN
	else if(key == KEY_13 && !setTimeMode)	// 按下KEY_13且在 非设置时间模式
	{
		paused = !paused;
	}
	else if (key == KEY_12)	// 切换cnt_or_tmp，显示温度或者倒计时
	{
		cnt_or_tmp = !cnt_or_tmp;
	}
	else	// 数字键, KEY1/2/3/5/6/7/9/10/11/14
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
				if(num >= 6) num = 5;	// 限制秒的高位，最大为5
				dig2 = num;
			}
			if(flashDigit == 1)	// 秒的低位
				dig1 = num;
			// 闪烁位递减
			flashDigit = flashDigit > 1 ? flashDigit-1 : 4;
		}
	}
}

void main(void)
{
	// 初始化buz_flag, buz_cnt, P20
	buz_cnt = 0;
	buz_flag = 0;
	P20=0;

	ch451_write(0x201);
    ch451_init();
    // 系统参数设定：显示驱动使能，键盘扫描使能，其余默认
    ch451_write(0x403);
    // 显示参数设定：选择 BCD 译码方式，其余默认
    ch451_write(0x580);

	// 设置定时器的工作模式和预设值

	TMOD = 0x11;

	TH0 = (65536 - TIMER_PRESET_VALUE) / 256; // 设置定时器的高字节
	TL0 = (65536 - TIMER_PRESET_VALUE) % 256; // 设置定时器的低字节

	// 开启定时器并使能定时器中断
	TR0 = 1;	// 开启定时器
	ET0 = 1;	// enable timer0 int
	IT0 = 0;	// 低电平触发
    EX0 = 1;	// enable timer0 int
	
	EA = 1;	//	开总中断
	

	
	while(1)
	{
		startTemperature();
		delay_ms(1000);
		printTemperature(readTemperature());
	}
}

