//ST7920 LCD ����
#include <intrins.h>
#include "delay.h"
#include "st7920.h"

//����ģʽ�µĹܽŶ�Ӧ��RS-CS(һ��ֱ�ӽӵ�Դ),RW-SID,E-SCLK
sbit LCD_CS_PORT = P2^0;	//CS=RS, һ��ֱ�ӽӵ�Դ
sbit LCD_SCLK_PORT = P2^1;  //SCLK=DE, ������������
sbit LCD_SID_PORT = P2^2;	//SID=WR, ����ʱ��
sbit LCD_RST_PORT = P2^3;	//RST, ��λ

//��ʼ��
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

//д����, ����д��0xf8, Ȼ��д������
void LCD_write_command(unsigned char command)
{
    LCD_SCLK_PORT = 0;
    LCD2_spi_write_byte(0xf8);
    _nop_();
    LCD2_spi_write_byte_standard(command);
}

//д����, ����д��0xfa, Ȼ��д������
void LCD_write_data(unsigned char dat)
{
    LCD_SCLK_PORT = 0;
    LCD2_spi_write_byte(0xfa);
    _nop_();
    LCD2_spi_write_byte_standard(dat);
}

//����ģʽ��дһ���ֽ�
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

//��׼����һ���ֽ�Ҫ��������ֽڷ���
void LCD2_spi_write_byte_standard(unsigned char dat)
{
    LCD2_spi_write_byte(dat & 0xf0);
    LCD2_spi_write_byte((dat << 4) & 0xf0);
}


// �����ı�ģʽ�ĵ�ַ
void LCD_set_text_address(unsigned int rowCol)
{
//��һ���׵�ַ��0x80
//�ڶ����׵�ַ��0x80+0x10 (0x90)
//�������׵�ַ��0x80+0x08 (0x88)
//�������׵�ַ��0x80+0x10+0x08 (0x98)

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

//���ַ�ģʽ�����к�ת��Ϊ�ڲ���XY���꣬���صĸ��ֽ�ΪX�����ֽ�ΪY
//row (HIGH_BYTE(rowCol)): �кţ�0-3
//col (LOW_BYTE(rowCol)): �кţ�0-7
unsigned int LCD_rowCol_to_inter_Xy(unsigned int rowCol)
{
    unsigned char row = (rowCol >> 8) & 0x03;
    unsigned char col = rowCol & 0x07;
    unsigned char x = col + 8 * (unsigned char)(row / 2);
    unsigned char y = (row * 16) & 0x1f;
    return (x << 8) | y;
}

//���û�ͼģʽ�ĵ�ַ (��Ҫ����ʵ����ʾ�����Ͻ�Ϊԭ�������ϵ)
//x: 0~7 *16 (ע��x����Ϊ16��������)
//y:0-63
void LCD_set_graphic_address(unsigned char x, unsigned char y)
{
    unsigned char xWord, downPage, yInter;

    x &= 0x7f;  //������0-127֮��, 0111 1111
    y &= 0x3f;  //������0-63֮��, 0011 1111
    xWord = x / 16;           //�ڲ�X��ַ��һ����ַ��16λ
    downPage = y / 32;        //0:�ϰ��� 1:�°���
    yInter = y & 0x1f;        //�ڲ�Y����, y=0~63 & 0001 1111
    
    LCD_write_command(0x80 + yInter); //������Y��ַ
    LCD_write_command(0x80 + xWord + 8 * downPage); // ��downPage=1,��xWord+8, ��Ϊ�°���x��ַ��8
}

//��ʾ��������
void LCD_clear(void)
{
    unsigned char x, y;
    LCD_write_command(0x08);  //����������������˸���ȹ���ʾ
    delay_10us(10);
    LCD_write_command(0x01);  //��DDRAM

    //��GDRAM,12864������һ���GDRAM����һ�뼴��
    //���û��ʹ�õ���ͼGDRAM��Ҳ���Բ���GDRAM��ʡ��ʱ��ʹ���ռ�
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

    LCD_write_command(0x0C);  //��ʾ��
    delay_ms(10);
}

//������չ����Ļ�ͼָ��
void LCD_startGraphic(void)
{
    LCD_write_command(0x34);          //��չָ�
    LCD_write_command(0x36);          //��ͼ�����
}

//������չ����Ļ�ͼָ��(��ѡ)
void LCD_endGraphic(void)
{
    LCD_write_command(0x36); //��ͼ����ر�
    LCD_write_command(0x30); //�ص�����ָ��
}


//��ʾһ���ַ���12864������ʾ4�У�ÿ��8�����ֻ�ÿ��16����ĸ
//col: �кţ�0-15
//row: �кţ�0-3
//�ַ����룺С��0x80��ΪASCII������Ϊ���֣����ֱ�����ֽ������
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

//��ʾһ���ַ�����ע�ⲻҪ����һ�еĳ��ȣ�8�����ֻ�16����ĸ��
//rowCol: ��8λΪ��(0-3)����8λΪ��(0-15)
void LCD_write_string(unsigned int rowCol, const char * p)
{
    LCD_set_text_address(rowCol);
    while (*p != 0)
    {
        LCD_write_data(*p);
        p++;
    }
}