#ifndef  __CH451_H  
#define  __CH451_H 

#define CH451_DIG0 0x0800 /*数码管位0显示*/
#define CH451_DIG1 0x0900 /*数码管位1显示*/
#define CH451_DIG2 0x0a00 /*数码管位2显示*/
#define CH451_DIG3 0x0b00 /*数码管位3显示*/
#define CH451_DIG4 0x0c00 /*数码管位4显示*/
#define CH451_DIG5 0x0d00 /*数码管位5显示*/
#define CH451_DIG6 0x0e00 /*数码管位6显示*/
#define CH451_DIG7 0x0f00 /*数码管位7显示*/

#define KEY_1 0x40
#define KEY_2 0x41
#define KEY_3 0x42
#define KEY_4 0x43

#define KEY_5 0x48
#define KEY_6 0x49
#define KEY_7 0x4A
#define KEY_8 0x4B

#define KEY_9  0x50
#define KEY_10 0x51
#define KEY_11 0x52
#define KEY_12 0x53

#define KEY_13 0x58
#define KEY_14 0x59
#define KEY_15 0x5A
#define KEY_16 0x5B

#define CH451_DOT 26

//函数原型声明，可以提出为单个头文件
void ch451_init(void);
void ch451_write(unsigned int command);
unsigned int ch451_read();

#endif