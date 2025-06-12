//ST7920 LCD 驱动
#include <intrins.h>
#include "delay.h"
#include "st7920.h"

//串口模式下的管脚对应：RS-CS(一般直接接电源),RW-SID,E-SCLK
sbit LCD_CS_PORT = P2^0;	//CS=RS, 一般直接接电源
sbit LCD_SCLK_PORT = P2^1;  //SCLK=DE, 串行数据输入
sbit LCD_SID_PORT = P2^2;	//SID=WR, 串行时钟
sbit LCD_RST_PORT = P2^3;	//RST, 复位

//初始化
void LCD_init(void)
{							  
	LCD_CS_PORT = 1;
	LCD_RST_PORT = 0;
    delay_ms(50);
	LCD_RST_PORT = 1;

	
    delay_ms(50);
    LCD_write_command(0x30);
    delay_ms(1);
    LCD_write_command(0x0C);
    delay_ms(1);
    LCD_write_command(0x02);
    delay_ms(1);
    LCD_write_command(0x01);
    delay_ms(1);
}

//写命令, 首先写入0xf8, 然后写入命令
void LCD_write_command(unsigned char command)
{
    LCD_SCLK_PORT = 0;
    LCD2_spi_write_byte(0xf8);
    _nop_();
    LCD2_spi_write_byte_standard(command);
}

//写数据, 首先写入0xfa, 然后写入数据
void LCD_write_data(unsigned char dat)
{
    LCD_SCLK_PORT = 0;
    LCD2_spi_write_byte(0xfa);
    _nop_();
    LCD2_spi_write_byte_standard(dat);
}

//串行模式下写一个字节
void LCD2_spi_write_byte(unsigned char dat)
{
    unsigned char i;
    for(i=0; i<8; i++)
    {
        if (dat & 0x80)
            LCD_SID_PORT = 1;
        else
            LCD_SID_PORT = 0;

        _nop_();
        _nop_();
        LCD_SCLK_PORT = 0;
        _nop_();
        LCD_SCLK_PORT = 1;
        dat <<= 1;
    }
}

//标准处理，一个字节要拆成两个字节发送
void LCD2_spi_write_byte_standard(unsigned char dat)
{
    LCD2_spi_write_byte(dat & 0xf0);
    LCD2_spi_write_byte((dat << 4) & 0xf0);
}


// 设置文本模式的地址
void LCD_set_text_address(unsigned int rowCol)
{
//第一行首地址：0x80
//第二行首地址：0x80+0x10 (0x90)
//第三行首地址：0x80+0x08 (0x88)
//第四行首地址：0x80+0x10+0x08 (0x98)

    unsigned char start = 0x80;
    unsigned char row = (rowCol >> 8) & 0xff;
    unsigned char col = rowCol & 0xff;

    if (row == 1) {
        start = 0x90;
    }
    if (row == 2) {
        start = 0x88;
    }
    if (row == 3) {
        start = 0x98;
    }
    LCD_write_command(start + col);
}

//将字符模式的行列号转换为内部的XY坐标，返回的高字节为X，低字节为Y
//row (HIGH_BYTE(rowCol)): 行号，0-3
//col (LOW_BYTE(rowCol)): 列号，0-7
unsigned int LCD_rowCol_to_inter_Xy(unsigned int rowCol)
{
    unsigned char row = (rowCol >> 8) & 0x03;
    unsigned char col = rowCol & 0x07;
    unsigned char x = col + 8 * (unsigned char)(row / 2);
    unsigned char y = (row * 16) & 0x1f;
    return (x << 8) | y;
}

//设置绘图模式的地址 (重要；以实际显示屏左上角为原点的坐标系)
//x: 0~7 *16 (注意x必须为16的整数倍)
//y:0-63
void LCD_set_graphic_address(unsigned char x, unsigned char y)
{
    unsigned char xWord, downPage, yInter;

    x &= 0x7f;  //限制在0-127之间, 0111 1111
    y &= 0x3f;  //限制在0-63之间, 0011 1111
    xWord = x / 16;           //内部X地址，一个地址管16位
    downPage = y / 32;        //0:上半屏 1:下半屏
    yInter = y & 0x1f;        //内部Y坐标, y=0~63 & 0001 1111
    
    LCD_write_command(0x80 + yInter); //先设置Y地址
    LCD_write_command(0x80 + xWord + 8 * downPage); // 若downPage=1,则xWord+8, 因为下半屏x地址大8
}

//显示清屏函数
void LCD_clear(void)
{
    unsigned char x, y;
    LCD_write_command(0x08);  //避免清屏过程中闪烁，先关显示
    delay_10us(10);
    LCD_write_command(0x01);  //清DDRAM

    //清GDRAM,12864仅用了一半的GDRAM，清一半即可
    //如果没有使用到绘图GDRAM，也可以不清GDRAM，省点时间和代码空间
    LCD_startGraphic();
    for (y = 0; y < 32; y++)
    {
        LCD_write_command(0x80 + y); //y
        LCD_write_command(0x80 + 0); //x
        for (x = 0; x < 16; x++)
        {
            LCD_write_data(0x00);
            LCD_write_data(0x00);
        }
    }
    LCD_endGraphic();

    LCD_write_command(0x0C);  //显示开
    delay_ms(10);
}

//开启扩展命令的绘图指令
void LCD_startGraphic(void)
{
    LCD_write_command(0x34);          //扩展指令集
    LCD_write_command(0x36);          //绘图命令开启
}

//结束扩展命令的绘图指令(可选)
void LCD_endGraphic(void)
{
    LCD_write_command(0x36); //绘图命令关闭
    LCD_write_command(0x30); //回到基本指令
}


//显示一个字符，12864可以显示4行，每行8个汉字或每行16个字母
//col: 列号，0-15
//row: 行号，0-3
//字符编码：小于0x80的为ASCII，否则为汉字，汉字编码高字节在左边
void LCD_write_char(unsigned int rowCol, unsigned int cod)
{
    unsigned char high = (cod >> 8) & 0xff;
    unsigned char low = cod & 0xff;
    LCD_set_text_address(rowCol);
    if (cod > 0x80)
    {
        LCD_write_data(high);
    }
    LCD_write_data(low);
}

//显示一个字符串，注意不要超过一行的长度（8个汉字或16个字母）
//rowCol: 左8位为行(0-3)，右8位为列(0-15)
void LCD_write_string(unsigned int rowCol, const char * p)
{
    LCD_set_text_address(rowCol);
    while (*p != 0)
    {
        LCD_write_data(*p);
        p++;
    }
}