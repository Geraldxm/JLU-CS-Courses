#include <reg51.h>

sbit LED = P1^7;
sbit SW1 = P1^0;

void delay(){
	unsigned char i;
	unsigned char j;
	for(i=255; i>0; i--){
		for(j= 200; j>0; j--){
		}
	}
}

void main(){
	while(1){
		if (SW1==1){
			LED = 1;
		}else{
			LED = ~LED;
		}
		delay();
	}
}
