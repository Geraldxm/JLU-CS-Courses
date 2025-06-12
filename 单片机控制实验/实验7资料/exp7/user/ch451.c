#include <reg51.h>
#include "ch451.h"

sbit CH451_DOUT = P3^2; // DOUT INT0
sbit CH451_DIN = P1^1;  // DIN  SDA
sbit CH451_CLK = P1^2;  // DCLK SCL
sbit CH451_LOAD = P1^3; // LOAD PWM0

void ch451_init(void)
{
    CH451_DIN = 0; /*先低后高，选择4线输入*/
    CH451_DIN = 1;
    CH451_LOAD = 1;
    CH451_CLK = 1;
}

void ch451_write(unsigned int command)
{
    unsigned char i;
    CH451_LOAD=0; /*命令开始*/
    for(i=0;i<12;i++)
    {
        CH451_DIN=command&1;
        CH451_CLK=0;
        CH451_CLK=1;
        command>>=1; /*上升沿有效*/
    }
    CH451_LOAD=1; /*加载数据*/
}

unsigned int ch451_read()
{
	unsigned char key;
	unsigned char i;
	ch451_write(0x700);
	key = 0;

	for(i=0;i<7;i++) //从CH451读出按键值
	{
		key <<= 1;
		key |= CH451_DOUT;
		CH451_CLK = 0;
		CH451_CLK = 1;
	}
	return key;
}
