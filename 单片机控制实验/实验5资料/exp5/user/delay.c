#include "delay.h"
#include "intrins.h"

/**********************************************************************************************************
函数名称：ms延时函数
输入参数：延时时间
输出参数：无
函数返回：无
**********************************************************************************************************/
void delay_ms(unsigned int t) 
{  
	unsigned int i; 
	unsigned char j; 
	for(i=0;i<t;i++) 
	{  
		for(j=0;j<200;j++); 
		for(j=0;j<102;j++); 
	} 
}
void delay_10us(unsigned char n){
	unsigned char i;
	for(i=n; i>0; --i){
		_nop_();
		//_nop_(); //主频为12MHz
	}
}
