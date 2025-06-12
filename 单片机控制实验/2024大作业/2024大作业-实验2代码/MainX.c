#include<reg51.h>
#include<stdio.h>
#include<intrins.h>
#include<string.h>
#include<stdlib.h>

// 定义计时相关的宏
#define TICK_TIME_US_2000 2000  // 每个tick周期为2000微秒
#define TICK_PER_SEC_500 500    // 每秒500个tick
#define TIMER_0_PRESET_VALUE TICK_TIME_US_2000  // 定时器0预设值

// 外部变量和函数声明
extern unsigned char temperature_value[];
extern void delay_ms(unsigned int x);
extern void LCD_Initialize();
extern void LCD_String(unsigned char r,unsigned char c,unsigned char *str);
extern unsigned char ds18b20_read_temperature();
extern void motor_control();
extern void motor_stop();
extern void init_timer1_pwm();

// 显示缓冲区定义
unsigned char display_upper_buffer[16];  // 上半部分显示缓冲区
unsigned char display_down_buffer[16];   // 下半部分显示缓冲区
unsigned char ticks = 0;  // 计时tick计数
unsigned char i;          // 循环变量
unsigned char duty_cycle; // 占空比

// 温度变量定义
float present_temp = 0.0;  // 当前温度
float target_temp = 0.0;  // 目标温度
float new_temp = 0.0;     // 新读取的温度

// 定义控制按钮引脚
sbit P33 = P3^3;  // 开关
sbit P34 = P3^4;  // +0.5温度调节
sbit P35 = P3^5;  // +1温度调节
sbit P36 = P3^6;  // +2温度调节
sbit P37 = P3^7;  // +4温度调节

// 初始化定时器0中断
void init_timer0_interrupt()
{
    TMOD = 0x01;  // 定时器0工作在模式1
    TH0 = (65536 - TIMER_0_PRESET_VALUE) / 256;  // 设置定时器高8位
    TL0 = (65536 - TIMER_0_PRESET_VALUE) % 256;  // 设置定时器低8位
    TR0 = 1;  // 启动定时器0
    ET0 = 1;  // 使能定时器0中断
    IT0 = 1;  // 外部中断0设置为低电平触发
    EX0 = 1;  // 使能外部中断0
    EA = 1;   // 全局中断使能
}

// 读取当前温度函数
void readPresentTemp()
{
    EA = 0;  // 禁用中断
    ds18b20_read_temperature();  // 读取温度
    delay_ms(50);
    present_temp = (((int)temperature_value[1]<<8)|((int)temperature_value[0]))*0.0625;  // 转换温度值
    EA = 1;  // 使能中断
    
    // 更新显示缓冲区
    for (i = 0; i < 15; i++)
    {
        display_upper_buffer[i] = display_upper_buffer[i+1];
        display_down_buffer[i] = display_down_buffer[i+1];
    }
    
    // 根据温度更新显示
    if (present_temp < 10)
    {
        display_upper_buffer[15] = ' ';
        display_down_buffer[15] = '_';
    }
    else if (present_temp < 12.5)
    {
        display_upper_buffer[15] = ' ';
        display_down_buffer[15] = '#';
    }
    else
    {
        display_upper_buffer[15] = '#';
        display_down_buffer[15] = '#';
    }
}

// 定时器0中断服务函数
void time0ISR() interrupt 1
{
    motor_control();  // 控制电机
    ticks++;
    if ((ticks % TICK_PER_SEC_500) == 0)  // 每秒读取一次温度
    {
        readPresentTemp();
    }
    TH0 = (65536 - TIMER_0_PRESET_VALUE) / 256;  // 重新加载定时器值
    TL0 = (65536 - TIMER_0_PRESET_VALUE) % 256;
}

// 读取目标温度函数
void readTargetTemp()
{
    unsigned char bit34 = (unsigned char)P34;
    unsigned char bit35 = (unsigned char)P35;
    unsigned char bit36 = (unsigned char)P36;
    unsigned char bit37 = (unsigned char)P37;
    new_temp = 10 + bit34*0.5 + bit35*1 + bit36*2 + bit37*4;
    target_temp = new_temp;
}

// 显示屏幕内容函数
void printScreen()
{
    unsigned char buffer[16];
    if (P33)
    {
        sprintf(buffer, "  targ: %5.2f\xDF\x43", target_temp);
        LCD_String(0, 0, buffer);
        sprintf(buffer, "  pres: %5.2f\xDF\x43", present_temp);
        LCD_String(1, 0, buffer);
    }
    else
    {
        LCD_String(0, 0, display_upper_buffer);
        LCD_String(1, 0, display_down_buffer);
    }
}

// 主函数
void main()
{
    target_temp = 0.0;
    present_temp = 0.0;
    duty_cycle = 0;
    
    init_timer0_interrupt();  // 初始化定时器0
    init_timer1_pwm();       // 初始化定时器1
    
    LCD_Initialize();        // 初始化LCD
    LCD_String(0, 0, "2024 Final Exp 2");
    LCD_String(1, 0, "    waiting...  ");
    
    delay_ms(200);
    
    while(1)
    {
        readTargetTemp();  // 读取目标温度
        printScreen();     // 更新屏幕显示
        delay_ms(50);
    }
}