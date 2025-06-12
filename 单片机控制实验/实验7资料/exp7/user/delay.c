#include "delay.h"
#include "intrins.h"

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
		//_nop_(); //Ö÷ÆµÎª12MHz
	}
}
