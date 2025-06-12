#include <reg51.h>
//ST7920�Ļ�ͼ�ṹ�����Ͻ�Ϊ����ԭ�㣬д����ֽڱ�ʾ��ĳ�����꿪ʼ��8���������أ�
//ÿ�� 128/8=16 ���ֽڣ�ÿ���ֽ����Ϊ���λ���ұ�Ϊ���λ������64�С�
//DDRAM��ַ
//0x80  0x81  0x82  0x83  0x84  0x85  0x86  0x87    //��һ�к���λ��
//0x90  0x91  0x92  0x93  0x94  0x95  0x96  0x97    //�ڶ��к���λ��
//0x88  0x89  0x8a  0x8b  0x8c  0x8d  0x8e  0x8f     //�����к���λ��
//0x98  0x99  0x9a  0x9b  0x9c  0x9d  0x9e  0x9f     //�����к���λ��
#ifndef __ST7290_H_
#define __ST7290_H_

void LCD_init(void);
void LCD_write_command(unsigned char command);
void LCD_write_data(unsigned char dat);
void LCD_write_byte(unsigned char byte);
void LCD2_spi_write_byte(unsigned char dat);
void LCD2_spi_write_byte_standard(unsigned char dat);
void LCD_startGraphic(void);
void LCD_endGraphic(void);
void LCD_Inverse_16X16(unsigned int rowCol, unsigned char charNum, unsigned char reverse);
unsigned int LCD_rowCol_to_inter_Xy(unsigned int rowCol);
void LCD_set_text_address(unsigned int rowCol);
void LCD_set_graphic_address(unsigned char x, unsigned char y);
void LCD_write_char(unsigned int rowCol, unsigned int cod);
void LCD_write_string(unsigned int rowCol, const char * p);

//�����ֽڴճ���������
//Ϊ�˸��õ�����ʹ��X/Y��ROW/COL��ʹ��ROW/COL�Ķ���һ�����Ͳ�����X/YΪ�����ֽڲ���
#define ROW_COL(r, c) (((r) << 8) | (c & 0xff))

#endif
