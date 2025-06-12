#include <reg51.h>
#include "tlc2543.h"
#include "delay.h"

sbit DIN = P0^0; // 数据输入信号线
sbit DOUT = P0^1; // 数据输出信号线
sbit CS = P0^2; // 片选信号线
sbit CLK = P0^3; // 时钟信号线
sbit EOC = P0^4; // 时钟信号线

unsigned int read2543(unsigned char port)
{
    unsigned int ad = 0, i;
    CLK = 0;
    CS	= 0;
    port <<= 4; // 选通道。高四位代表AD通道，低四位自动补0

    for (i = 0; i < 12; i++ ) //进行12次（12位）的数据推送
    {
        if (DOUT)
            ad |= 0x01;             // 相当于每次检出输出1时。最低位就给1，否则给0
        DIN	= (bit) (port & 0x80);
        CLK	= 1;
        delay_10us(3);
        CLK = 0;
        delay_10us(3);
        port <<= 1;
        ad <<= 1;
    }
    CS = 1;
    ad >>= 1;
	while(!EOC);
    return (ad);
}
