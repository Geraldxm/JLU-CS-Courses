#ifndef  __DS18B20_H  
#define  __DS18B20_H 

//函数原型声明，可以提出为单个头文件
void reset_ds18b20(void);
void writeByte_ds18b20(unsigned char dat);
unsigned char readByte_ds18b20(void);

#endif