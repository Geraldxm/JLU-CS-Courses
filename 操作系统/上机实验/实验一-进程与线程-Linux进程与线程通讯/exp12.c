#define _GNU_SOURCE
#include<sched.h>   //调度用
#include<pthread.h> //线程、互斥锁、条件变量 
#include<stdio.h>
#include<stdlib.h>
#include<semaphore.h> //信号量，sem
#include"sys/wait.h"
#include<unistd.h>  //进程控制，文件操作，系统调用，管道
int producer(void *args);
int consumer(void *args);
pthread_mutex_t mutex;  //声明互斥锁
sem_t product;
sem_t warehouse;

pthread_t tid[16];   //保存子线程的线程标识符，主程序等待他们全部结束后，才退出
char buffer[8][4];
int bp=0;
//报错对sem_未定义的引用，需要在编译最后添加 -lpthread 或 -pthread
//gcc exp12.c -o exp12.out -lpthread
//


//int clone(int (*func)(void*),void *child_stack,int flags,void *func_arg,...
//func函数指针，指向子进程的入口函数
//用于保存子进程/线程的函数调用栈、局部变量等
//flags为掩码
//arg指向参数的地址，将自定义参数传给线程的入口函数

int main(int argc, char** argv){
	pthread_mutex_init(&mutex, NULL);
    //初始化一个互斥锁对象，将其设置为默认的属性和状态。
    //函数的第一个参数是互斥锁对象的指针，
    //即 &mutex，它指向要进行初始化的互斥锁。
    //第二个参数是一个指向 pthread_mutexattr_t 类型的指针，
    //用于指定互斥锁的属性。在这里传入 NULL 表示使用默认的属性。
	sem_init(&product,0,0);  
    //信号量初始化int sem_init(sem_t *sem, int pshared, unsigned int value); 
	//pshared 控制信号量的类型，值为 0 代表该信号量用于多线程间的同步
    //值如果大于 0 表示可以共享，用于多个相关进程间的同步
    //第三个参数表示初始信号量值为0
	sem_init(&warehouse,0,8);
    //初始量为8
	int clone_flag, arg;
	char* stack;
	clone_flag = CLONE_VM|CLONE_SIGHAND|CLONE_FS|CLONE_FILES;
    
    pthread_t retval;   
    int cnt=0;
    //CLONE_VM：表示子进程与父进程共享内存空间，即虚拟地址空间。
    //CLONE_SIGHAND：表示子进程与父进程共享信号处理器表，即信号处理行为。
    //CLONE_FS：表示子进程与父进程共享文件系统信息，包括当前工作目录和根目录。
    //CLONE_FILES：表示子进程与父进程共享文件描述符表，即打开的文件。
    //用以下的宏定义和头文件来解决CLONE未定义问题
    //#define _GNU_SOURCE        
    //#include <sched.h>
	int i;
	for(i = 0 ; i < 2; i++)
	{
		arg = i;        //arg用来向子线程传递参数 
		stack = (char*)malloc(4096); //给子线程分配一块stack 
		retval = (pthread_t)clone((void*)producer, &(stack[4095]), clone_flag, (void*)&arg);
        //retval=pthread_create(&tid[cnt],NULL,producer,NULL);
        //tid[cnt]=(pthread_t)retval;
        //cnt++;
        //int clone(int (*func)(void *), void *child_stack, int flags, void *arg)
		stack = (char*)malloc(4096);
		retval = (pthread_t)clone((void*)consumer, &(stack[4095]), clone_flag, (void*)&arg);
        //retval=pthread_create(&tid[cnt],NULL,consumer,NULL);
        //tid[cnt]=(pthread_t)retval;
        //cnt++;
        //printf("ret = %d\n",retval);
        sleep(1);
        //printf("loop over\n");
	}
    //使用 clone() 函数创建两个子线程，一个用于生产者，
    //一个用于消费者。每个子线程都有独立的堆栈空间，
    //并通过参数传递给子线程。主线程在创建完子线程后退出。

    // printf("wait for tid 0~3\n");
    // for (i = 0; i < cnt; i++) {
    //     pthread_join(tid[i], NULL);
    // }

    //printf("All child has been terminated!\n");

    sleep(1000);
    //这里必须要睡，不能直接退出，等待子线程执行完毕
	return 0;
    //exit(0);
}

//bp指向的是空盒子 
int producer(void* args)
{
    //在本例中，args用来传递id号，消费者1、2，生产者1、2
	int id = *((int*)args);
    //printf("producer id=%d\n",id);
	int i;
	for(i = 0; i < 10; i++)
	{
		sleep(i);
		sem_wait(&warehouse);        //对信号量进行P操作，尝试将信号量值-1，
		pthread_mutex_lock(&mutex);
		//获取互斥锁
        if(id == 0) strcpy(buffer[bp],"aaa\0");
		else strcpy(buffer[bp], "bbb\0");
        //0号生产aaa，1号生产bbb
		bp++;
		printf("producer%d produce %s in bp[%d]\n", id, buffer[bp-1], bp-1);
		pthread_mutex_unlock(&mutex);
		//解锁
        sem_post(&product);
	}
	printf("producer%d is over!\n", id);
	//exit(id);
    //若使用exit，即使在非main函数，也会终止程序
    return id;
}

int consumer(void *args)
{
	int id = *((int*)args);
    //printf("consumer id=%d\n",id);
	int i;
	for(i = 0; i < 10; i++)
	{
		sleep(10-i);
		sem_wait(&product);    //等待product，取一个product 
		pthread_mutex_lock(&mutex);
        bp--;
		printf("consumer%d get %s in bp[%d]\n", id, buffer[bp], bp); 
		strcpy(buffer[bp], "zzz\0");     //zzz代表已经取走了 
		pthread_mutex_unlock(&mutex);
		sem_post(&warehouse);
        //sleep(1);
	}
    printf("consumer%d is over!\n", id);
	//exit(id);
    return id;
}
