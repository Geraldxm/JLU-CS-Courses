#include<reg51.h>
#include<intrins.h>

// 定义计时相关的宏
#define TICK_TIME_US_1000 1000  // 每个tick的时间为1000微秒
#define TICK_PER_SEC_1000 1000  // 每秒的tick数为1000
#define TIMER_1_PRESET_VALUE TICK_TIME_US_1000  // 定时器1的预设值

// 定义电机控制引脚
sbit motor_IN1 = P1^0;  // 电机输入1
sbit motor_IN2 = P1^1;  // 电机输入2
sbit motor_EN = P1^2;   // 电机使能引脚

// 外部变量声明
extern float present_temp;   // 当前温度
extern float target_temp;   // 目标温度
extern unsigned char duty_cycle;  // 占空比

// 初始化定时器1用于PWM控制
void init_timer1_pwm()
{
    TMOD |= 0x10;  // 设置定时器1为模式1
    TH1 = (65536 - TIMER_1_PRESET_VALUE) / 256;  // 设置定时器高8位
    TL1 = (65536 - TIMER_1_PRESET_VALUE) % 256;  // 设置定时器低8位
    TR1 = 1;  // 启动定时器1
    ET1 = 1;  // 使能定时器1中断
    EA = 1;   // 全局中断使能
}

// 定时器1中断服务程序
void time1ISR() interrupt 3
{
    static unsigned int counter = 0;  // 计数器用于PWM周期控制
    
    counter++;  // 计数器递增
    if (counter >= 100)  // 达到100则重置
    {
        counter = 0;
    }
    if (counter < duty_cycle)  // 根据占空比控制电机
    {
        motor_IN1 = 1;  // 设置电机方向
        motor_IN2 = 0;
        motor_EN = 1;   // 使能电机
    }
    else
    {
        motor_EN = 0;  // 禁能电机
    }
    
    TH1 = (65536 - TIMER_1_PRESET_VALUE) / 256;  // 重新加载定时器值
    TL1 = (65536 - TIMER_1_PRESET_VALUE) % 256;
}

// 电机控制函数
void motor_control()
{
    if (present_temp <= target_temp)  // 当前温度低于或等于目标温度
    {
        duty_cycle = 0;  // 停止电机
        return;
    }
    else  // 当前温度高于目标温度
    {
        duty_cycle = present_temp*10 - target_temp*10 + 20;  // 计算占空比
        if (duty_cycle > 100) duty_cycle = 100;  // 占空比上限为100
        else if (duty_cycle < 0) duty_cycle = 0;  // 占空比下限为0
    }
}