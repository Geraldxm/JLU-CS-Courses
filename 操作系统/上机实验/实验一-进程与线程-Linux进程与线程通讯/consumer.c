#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>

#define MAX_MSG_SIZE 100

struct msg_buffer {
    long msg_type;
    char msg_text[MAX_MSG_SIZE];
};

int main() {
    key_t key;
    int msg_queue_id;
    struct msg_buffer message;

    // 创建唯一的key值
    key = ftok("/tmp", 'A');

    // 获取消息队列ID
    msg_queue_id = msgget(key, 0666 | IPC_CREAT);
    if (msg_queue_id == -1) {
        perror("msgget");
        exit(1);
    }

    // 从消息队列中接收消息
    msgrcv(msg_queue_id, &message, sizeof(message), 1, 0);

    printf("Received message: %s", message.msg_text);

    // 删除消息队列
    msgctl(msg_queue_id, IPC_RMID, NULL);

    return 0;
}
