// 假设有多个生产者向一个缓冲区不停发送消息，n个消费者进程从该缓冲区接受消息。该缓冲区只能存放一条消息，且所有接收者都读取某一条消息后，生产者才可以继续向缓冲区发送消息。
// 使用信号量semaphore和PV操作实现生产者进程和消费者进程收发消息的描述，用伪C代码实现。

semaphore buf_mutex;
semaphore w_mutex;
semaphore r_mutex;
semaphore n_readers_mutex;    //用来控制只有n个读者全部读完后才能写下一个内容
message buf[];

void init()
{
    sem_init(buf_mutex,1);
    sem_init(w_mutex,1);
    sem_init(r_mutex,1);
    sem_init(n_readers_mutex,1);   //当n个读者全部读完后，才能继续写
    //初值是1，最小会变成-(n-1)
}
void producer()
{
    while (1)
    {
        P(w_mutex);
        message msg=produce();
        P(n_readers_mutex); //等待所有读者读完
        P(buf_mutex);
            write(msg,buf);
            sem_set(n_readers_mutex,-(n-1));
        V(buf_mutex);
        V(r_mutex);
        V(w_mutex);
    }
}
void consumer()
{
    while (1)
    {
        message msg;
        P(r_mutex);
        P(buf_mutex);
            read(msg,buf);
        V(buf_mutex);
        V(n_readers_mutex);
        V(r_mutex);
        conosume(msg);
    }
}