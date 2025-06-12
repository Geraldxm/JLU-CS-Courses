#include "math.h"
#include "sched.h"
#include "pthread.h"
#include "stdlib.h"
#include "semaphore.h"
#include "stdio.h"

typedef struct{ //任务描述
    char task_id;
    int call_num;   //任务发生次数
    int ci;     //Ci
    int ti;     //Ti
    int ci_left;
    int ti_left;
    int flag;   //任务是否活跃，0/2
    int arg;    //参数
    pthread_t th;   //对应线程
}task;

void proc(int* args);   //执行
void* idle();           //空闲
int select_proc();      //实现调度

int task_num=0;
int idle_num=0;

int alg;    //选择的算法，1 EDF， 2 RMS
int curr_proc=-1;
int demo_time=100;  //演示时长

task* tasks;
pthread_mutex_t proc_wait[100];         //为每个任务创建一个互斥锁
pthread_mutex_t main_wait,idle_wait;    //
float sum=0;
pthread_t idle_proc;

int main(int argc, char** argv)
{
    //初始化并锁定main_wait，初始化并锁定idle_wait
    pthread_mutex_init(&main_wait,NULL);
    pthread_mutex_lock(&main_wait);
    pthread_mutex_init(&idle_wait,NULL);
    pthread_mutex_lock(&idle_wait);
    //初始化锁后，锁是解锁的状态。用lock获取锁，并继续往下执行。
    printf("Please input number of real time tasks:\n");
    scanf("%d",&task_num);
    tasks = (task*)malloc(task_num*sizeof(task));
    int i;
    for(i=0;i<task_num;i++)
    {
        //初始化并锁定task_num个互斥锁
        pthread_mutex_init(&proc_wait[i],NULL);
        pthread_mutex_lock(&proc_wait[i]);
    }
    //Ci是任务的处理时间，Ti是任务的发生周期
    for(i=0;i<task_num;i++)
    {
        printf("Please input task id, followed by Ci and Ti:");
        getchar();
        scanf("%c,%d,%d,",&tasks[i].task_id,&tasks[i].ci,&tasks[i].ti);
        tasks[i].ci_left=tasks[i].ci;
        tasks[i].ti_left=tasks[i].ti;
        tasks[i].flag=2;
        tasks[i].arg=i;
        tasks[i].call_num= 1;
        sum=sum+(float)tasks[i].ci/(float)tasks[i].ti;  //求sum，用来判断能否调度
    }
    printf("Please input algorithm, 1 for EDF, 2 for RMS:");
    getchar();
    scanf("%d",&alg);
    printf("Please input demo time:");
    scanf("%d",&demo_time);
    //r是可调度的充分条件
    double r=1;     //EDF的条件
    if (alg==2)
    {   //RMS的条件
        r=((double)task_num)*(exp(log(2)/(double)task_num)-1);
        printf("r is %lf\n",r);
    }
    //不可调度
    if (sum>r)
    {  
        printf("(sum=%lf>r=%lf), not schedulable!\n",sum,r);
        exit(2);
    }
    //若可调度
    pthread_create(&idle_proc,NULL,(void*)idle,NULL);   //闲逛进程
    //idle_proc引用，存储闲逛进程标识符
    //printf("main here\n");


    for(i=0;i<task_num;i++) 
        pthread_create(&tasks[i].th,NULL,(void*)proc,&tasks[i].arg);
    //创建所有任务进程
    
    //这个循环用来模拟系统的运行和任务调度，每个循环就代表一个时间单位
    for(i=0;i<demo_time;i++)
    {
        int j;
        //按调度算法选线程，若返回值不为-1，就表示有可运行的任务线程
        if((curr_proc=select_proc(alg))!=-1) 
        { 
            pthread_mutex_unlock(&proc_wait[curr_proc]); 
            //前面把这个锁定了，现在释放，对应的proc函数能够继续运行
            //再获取一次main_wait锁，主线程会被阻塞，等待proc的对主程序锁的释放
            pthread_mutex_lock(&main_wait);
        }
        else
        {   //无可运行任务，选择闲逛线程
            pthread_mutex_unlock(&idle_wait);
            pthread_mutex_lock(&main_wait);
        }
        //遍历所有任务，将每个任务的剩余时间ti_left-1
        //若ti_left==0，那么说明当前任务的周期结束，开始下一个周期
            //那么就将ti_left和ci_left重置为初始值
            //然后创建一个新的线程来执行，同时将任务标志flag设为2，表示活跃
        for(j=0;j<task_num;j++)
        { //Ti--， 为0时开始下一周期
            if(--tasks[j].ti_left==0)
            {
                tasks[j].ti_left=tasks[j].ti;
                tasks[j].ci_left=tasks[j].ci;
                pthread_create(&tasks[j].th,NULL,(void* )proc,&tasks[j].arg);
                tasks[j].flag=2;
            }
        }
    }
    printf("\n");
    sleep(10);
};

void proc(int* args)
{
    //只有任务的剩余执行时间ci_left>0才会继续
    while(tasks[*args].ci_left>0)
    {    
        //在主程序中，创建锁后马上获取锁，所以这里无法获取锁，必须等主函数解锁
        //等待主函数的调度
        pthread_mutex_lock(&proc_wait[*args]);

        //如果此时有闲逛线程，那么打印闲逛线程数量，将其置为零？为什么
        //为了计数，上一个有效进程到这一次有效进程之间有几个闲逛周期。
        if(idle_num!=0)
        {
            printf("idle(%d)",idle_num);
            idle_num=0;
        }

        //打印任务标识符task_id，调用次数call_num
        printf("%c%d",tasks[*args].task_id,tasks[*args].call_num);
        tasks[*args].ci_left--; //执行一个时间单位

        //任务剩余时间变为0，表示执行完毕
        if(tasks[*args].ci_left==0)
        {
            printf("(%d)",tasks[*args].ci);
            tasks[*args].flag=0;        //不再活跃
            tasks[*args].call_num++;    //为什么这里才自增调用次数？
                                        //这个实际上是完成次数，不是调用次数。
        }
        pthread_mutex_unlock(&main_wait);   //唤醒主线程
        //主函数被阻塞了，第二次调用lock时
    }
};

void* idle()
{
    //printf("idle here\n");
    while(1)
    {
        pthread_mutex_lock(&idle_wait); //等待被调度
        printf("-> ");   //空耗一个时间单位
        idle_num++; //记录闲逛线程被唤醒的次数
        pthread_mutex_unlock(&main_wait);   //唤醒主控线程
    }
};

int select_proc(int alg)
{
    int j;
    int temp1,temp2;
    temp1=10000;
    temp2=-1;
    //如果是RMS算法，其正在执行的任务不为-1，，且正在执行的任务flag!=0（还在运行）
    //那么继续执行该任务


    if ((alg==2)&&(curr_proc!=-1)&&(tasks[curr_proc].flag)!=0)
        return curr_proc;
    //注释掉即是抢占式


    //进入循环，遍历所有任务
    for (j=0;j<task_num;j++)
    {   
        //对于活跃任务flag==2进行判断
        if (tasks[j].flag==2)
        {
            switch(alg)
            {
                case 1:     //EDF算法
                    //EDF算法寻找剩余时间最少的
                    if (temp1>tasks[j].ti_left)
                    {
                        temp1=tasks[j].ti_left;
                        temp2=j;
                    }
                    break;
                case 2:     //RMS算法
                    //RMS算法找周期ti最小的
                    if (temp1>tasks[j].ti)
                    {
                        temp1=tasks[j].ti;
                        temp2=j;
                    }
                    break;
            }
        }
    }
    return temp2;
}