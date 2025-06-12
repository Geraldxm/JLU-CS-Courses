#include <reg51.h>
//ST7920的绘图结构：左上角为坐标原点，写入的字节表示从某个坐标开始的8个横向像素，
//每行 128/8=16 个字节，每个字节左边为最低位，右边为最高位，纵向64行。
//DDRAM地址
//0x80  0x81  0x82  0x83  0x84  0x85  0x86  0x87    //第一行汉字位置
//0x90  0x91  0x92  0x93  0x94  0x95  0x96  0x97    //第二行汉字位置
//0x88  0x89  0x8a  0x8b  0x8c  0x8d  0x8e  0x8f     //第三行汉字位置
//0x98  0x99  0x9a  0x9b  0x9c  0x9d  0x9e  0x9f     //第四行汉字位置
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

//两个字节凑成行列整型
//为了更好的区分使用X/Y和ROW/COL，使用ROW/COL的都是一个整型参数，X/Y为两个字节参数
#define ROW_COL(r, c) (((r) << 8) | (c & 0xff))

#endif
