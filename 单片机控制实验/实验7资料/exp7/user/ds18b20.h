#ifndef  __DS18B20_H  
#define  __DS18B20_H 

//����ԭ���������������Ϊ����ͷ�ļ�
void reset_ds18b20(void);
void writeByte_ds18b20(unsigned char dat);
unsigned char readByte_ds18b20(void);

#endif