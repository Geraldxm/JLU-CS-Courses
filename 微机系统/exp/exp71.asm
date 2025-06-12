DATA SEGMENT
    STRING DB 100 DUP(0) ; 用来存放输入的字符串，最大长度为100字节
DATA ENDS

CODE SEGMENT
    ASSUME CS:CODE, DS:DATA

MAIN PROC FAR
    MOV AX, DATA
    MOV DS, AX
    LEA BX, STRING ; 将字符串的起始地址加载到BX寄存器
    MOV CL, 0 ; 小写字母数，初始化为0
AA1:
    MOV AH, 1 ; 输入字符
    INT 21H
    ;0DH就是回车的ASCII码
    CMP AL, 0DH ; 当输入回车时，计算小写字母个数并输出
    JZ AA3
    CMP AL, 41H ; 小于 'A' 的字符，退出
    JC AA4
    CMP AL, 5BH ; 大于 'Z' 的字符，退出
    JNC AA2
    MOV [BX], AL ; 将输入的字符存入内存
    INC BX ; 移动到下一个存储位置
    JMP AA1 ; 继续输入字符
AA2:
    CMP AL, 61H ; 小于 'a' 而大于 'Z' 的字符，退出
    ; 这是a
    JC AA4
    CMP AL, 7BH ; 大于 'z' 的字符，退出，7BH=123，122是z
    JNC AA4
    MOV [BX], AL ; 将输入的字符存入内存
    INC BX ; 移动到下一个存储位置
    INC CL ; 小写字母数量加一
    JMP AA1 ; 继续输入字符
AA3:
    PUSH CX ; 保存小写字母的数量
    ;INT 10H AH=3读取光标位置
    MOV AH, 3 ; 获取当前光标位置
    INT 10H
    MOV AH, 2 ; 设置光标位置
    LEA DI, STRING ; 将字符串的起始地址加载到DI寄存器
    SUB BX, DI ; 计算光标列位置
    ;BX上面是字符串的最后的位置，BX=BX-DI，使其变成整个字符串的长度
    MOV DL, BL ; 将光标列位置保存到DL寄存器
    ;往后移动一位，便于输出
    INC DL ; 光标向后移动一位，使数字和前面的字符串分开
    INT 10H
    POP CX ; 恢复小写字母的数量

BB1:
    CMP CX, 100 ; 比较结果和 100
    JC CC1 ; 如果结果小于 100，则跳转到标签 CC1
    MOV DL, 30H ; 将十进制数字 0 的 ASCII 值加载到 DL 寄存器
    INC DL ; 百位计数
    SUB CX, 100 ; 模 100，获取十位及以下的数值
    CMP CX, 100 ; 检查是否仍然大于等于 100
    JNC BB1 ; 如果仍然大于等于 100，则继续循环
    MOV AH, 2 ; 设置 AH 寄存器的值为 2，表示要打印字符
    INT 21H ; 调用 DOS 中断 21H，显示百位数值
CC1: 
    CMP CX, 10 ; 比较结果和 10
    JC CC2 ; 如果结果小于 10，则跳转到标签 CC2
    MOV DL, 30H ; 将十进制数字 0 的 ASCII 值加载到 DL 寄存器
BB2: 
    INC DL ; 十位计数
    SUB CX, 10 ; 模 10，获取个位数值
    CMP CX, 10 ; 检查是否仍然大于等于 10
    JNC BB2 ; 如果仍然大于等于 10，则继续循环
    MOV AH, 2 ; 设置 AH 寄存器的值为 2，表示要打印字符
    INT 21H ; 调用 DOS 中断 21H，显示十位数值
CC2: 
    MOV DL, CL ; 将 CX 的值（即个位数值）加载到 DL 寄存器
    ADD DL, 30H ; 将个位数值转换为 ASCII 字符
    MOV AH, 2 ; 设置 AH 寄存器的值为 2，表示要打印字符
    INT 21H ; 调用 DOS 中断 21H，显示个位数值

AA4:
    MOV AH, 4CH ; 程序结束，返回DOS
    INT 21H
CODE ENDS

END MAIN ; 程序结束
