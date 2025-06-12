#include<reg51.h>
#include<intrins.h>

#define INT8U unsigned char
#define INT16U unsigned int

sbit DQ = P3^0;
INT8U temperature_value[]={0x00, 0x00};

void delay(INT16U x)
{
	while(--x);
}

INT8U ds18b20_init()
{
	INT8U status;
	DQ = 1; delay(8);
	DQ = 0; delay(90);
	DQ = 1; delay(5);
	
	status = DQ;
	delay(90);
	
	return status;
}

INT8U ds18b20_read_byte()
{
	INT8U i, data8=0x00;
	
	for(i=0x01; i!=0x00; i<<=1)
	{
		DQ=0; _nop_();
		DQ=1; _nop_();
		if(DQ)
			data8 = data8|i;
		delay(8);
	}
	
	return data8;
} 

void ds18b20_write(INT8U data8)
{
	INT8U i;
	
	for(i=0; i<8; i++)
	{
		DQ=1; _nop_();
		DQ=0; _nop_();
		data8 = data8>>1;
		DQ = CY;
		delay(8);
	}
}

INT8U ds18b20_read_temperature()
{
	if(ds18b20_init()) return 0;
	else
	{
		ds18b20_write(0xCC);
		ds18b20_write(0x44);
		ds18b20_init();
		ds18b20_write(0xCC);
		ds18b20_write(0xBE);
		temperature_value[0] = ds18b20_read_byte();
		temperature_value[1] = ds18b20_read_byte();
		return 1;
	}
}
