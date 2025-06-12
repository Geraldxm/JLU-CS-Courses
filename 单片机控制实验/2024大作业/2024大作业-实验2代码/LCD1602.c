#include<reg51.h>
#include<intrins.h>

#define INT8U unsigned char
#define INT16U unsigned int

// 2*16 char

sbit RS=P2^0;
sbit RW=P2^1;
sbit EN=P2^2;

void delay_ms(INT16U x)
{
	INT8U i;
	while(x--)
	{
		for(i=0;i<120;i++);
	}
}

void Busy_Wait()
{
	INT8U LCD_Status;
	do
	{
		P0=0xFF;
		EN=0;RS=0;RW=1;
		EN=1; LCD_Status=P0;
		EN=0;
	}
	while(LCD_Status&0x80);//1000 0000
}

void Write_LCD_Command(INT8U cmd)
{
	Busy_Wait();
	EN=0;RS=0;RW=0;
	P0=cmd;
	EN=1;_nop_();EN=0;

}

void Write_LCD_Data(INT8U data1)
{
	Busy_Wait();
	EN=0;RS=1;RW=0;
	P0=data1;
	EN=1;_nop_();EN=0;
}

void LCD_String(INT8U r,INT8U c,INT8U *str)
{
	INT8U i=0;
	INT8U code DDRAM[]={0x80,0xC0};
	Write_LCD_Command(DDRAM[r]|c);
	for(i=0;i<16&&str[i];i++)
	{
		Write_LCD_Data(str[i]);
	}
	for(;i<16;i++)
		Write_LCD_Data(' ');
}

void LCD_Initialize()
{
	Write_LCD_Command(0x38); delay_ms(1);//置功能位，8位，双行，每一个字符占5*7
	Write_LCD_Command(0x01); delay_ms(1);//清屏 
	Write_LCD_Command(0x06); delay_ms(1);//字符进入模式，屏幕不动，字符后移
	Write_LCD_Command(0x0C); delay_ms(1);//开显示，关光标
}