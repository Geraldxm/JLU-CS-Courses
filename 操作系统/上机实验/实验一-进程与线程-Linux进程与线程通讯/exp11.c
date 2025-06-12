//#include <sys/wait.h>
#include "sys/types.h"
#include "sys/file.h"
#include "unistd.h"

char r_buf[4]; /*读缓冲*/
char w_buf[4]; /*写缓冲*/
//用于存储从管道中读取/写入的数据

int pipe_fd[2]; //存储管道的文件描述符，pipe_fd[0]表示读端，pipe_fd[1]表示写端
pid_t pid1, pid2 ,pid3 ,pid4;   //子进程的进程id

int producer (int id);
int consumer (int id);

int main(int argc, char ** argv) {
    //pipe()传入文件描述符，创建一个管道
    if(pipe(pipe_fd) < 0) {
        printf("pipe create error\n");
        return -1;
    } else {
        printf("pipe create success\n");
        //使用fork()创建四个子进程
        if((pid1 = fork()) == 0) 
            producer(1);
        if((pid2 = fork()) == 0)
            producer(2);
        if((pid3 = fork()) == 0)
            consumer(1);
        if((pid4 = fork()) == 0)
            consumer(2);
    }


    // strcpy(w_buf,"fth\0");
    // write(pipe_fd[1], w_buf, 4);
    
    //父进程关闭管道的两端
    close(pipe_fd[0]);
    close(pipe_fd[1]);
    //如果父进程不关闭管道的读端和写端，那么管道将一直保持打开状态


    //父进程等待所有子进程终止
    int i, pid, status;
    for(i = 0; i < 4; i++)
    {
        pid = wait(&status);
        printf("%d exit!\n", pid);
    }
        //在每次循环中，wait(&status)会暂停父进程的执行
        //直到任一子进程退出。一旦有子进程退出
        //wait(&status)会返回被终止子进程的进程ID，并将子进程的退出状态保存在status中。
    exit(0);
}

//生产者
int producer(int id) {
    printf("producer %d is running!\n", id);
    close(pipe_fd[0]);  //关闭读端
    int i = 0;
    for(i = 0; i < 4 ; i++) {
        sleep(2);
        //写入缓冲区 char w_buff[4]
        if(id == 1)
            strcpy(w_buf, "aaa\0");
        else
            strcpy(w_buf, "bbb\0");
        //生产者1发布消息aaa，生产者2发布消息bbb

        //使用write函数将写缓冲区的内容写入管道
        if(write(pipe_fd[1], w_buf, 4) == -1)   //pipe_fd[1]是写端
            printf("write to pipe error\n");
    }
    //关闭写端，生产结束，以id为状态退出
    close(pipe_fd[1]);
    printf("producer %d is over!\n", id);
    exit(id);
}

//消费者
int consumer(int id) {
    close(pipe_fd[1]);  //关闭写端
    printf("consumer %d is running!\n", id);
    if(id == 1)
        strcpy(w_buf, "ccc\0");
    else
        strcpy(w_buf, "ddd\0");
    //在这段代码中，消费者进程在读取数据之前会将写缓冲区的内容设置为"ccc"或"ddd"，但是实际上这个设置是无效的。
    //因为管道的写端pipe_fd[1]已经被关闭。
    //因此这个w_buf仅仅起到在后面输出时标识消费者的作用吧
    while(1) {
        sleep(1);
        strcpy(r_buf, "eee\0");
        //这里设r_buf为eee，但实际上下面读取后会被覆盖。
        //使用read函数从管道中读取数据到读缓冲区
        //printf("befor read() consumer %d r_buf = %s\n",id,r_buf);
        //此时r_buf的值是eee，但是下面马上进行更改
        if(read(pipe_fd[0], r_buf, 4) == 0)
            break;  //如果读取到的字节数为0，就退出
        printf("consumer %d get %s, while the w_buf is %s\n", id, r_buf, w_buf);
        //printf("after read() consumer %d r_buf = %s\n",id,r_buf);
    }
    close(pipe_fd[0]);
    printf("consumer %d is over!\n", id);
    exit(id);
}
