#include <reg51.h>
#include "tlc2543.h"
#include "delay.h"

sbit DIN = P0^0; // ���������ź���
sbit DOUT = P0^1; // ��������ź���
sbit CS = P0^2; // Ƭѡ�ź���
sbit CLK = P0^3; // ʱ���ź���
sbit EOC = P0^4; // ʱ���ź���

unsigned int read2543(unsigned char port)
{
    unsigned int ad = 0, i;
    CLK = 0;
    CS	= 0;
    port <<= 4; // ѡͨ��������λ����ADͨ��������λ�Զ���0

    for (i = 0; i < 12; i++ ) //����12�Σ�12λ������������
    {
        if (DOUT)
            ad |= 0x01;             // �൱��ÿ�μ�����1ʱ�����λ�͸�1�������0
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
