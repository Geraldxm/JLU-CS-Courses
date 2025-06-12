DATA SEGMENT
    RESULT DW 0 ; 声明一个名为 RESULT 的字型变量，用于存储结果
DATA ENDS

CODE SEGMENT
    ASSUME DS:DATA, CS:CODE

MAIN PROC FAR ; 主程序定义为 FAR 类型
    MOV AX, DATA
    MOV DS, AX ; 将数据段地址加载到 DS 寄存器

    MOV CX, 5 ; 设置循环次数为 5
    CALL FACT ; 调用 FACT 函数计算结果

    CMP CX, 10000 ; 比较结果和 10000
    JC BB1 ; 如果结果小于 10000，则跳转到标签 BB1

    MOV DL, 30H ; 将十进制数字 0 的 ASCII 值加载到 DL 寄存器
AA1: 
    INC DL ; 万位计数
    SUB CX, 10000 ; 模 10000，获取千位及以下的数值
    CMP CX, 10000 ; 检查是否仍然大于等于 10000
    JNC AA1 ; 如果仍然大于等于 10000，则继续循环
    MOV AH, 2 ; 设置 AH 寄存器的值为 2，表示要打印字符
    INT 21H ; 调用 DOS 中断 21H，显示万位数值

BB1: 
    CMP CX, 1000 ; 比较结果和 1000
    JC BB2 ; 如果结果小于 1000，则跳转到标签 BB2

    MOV DL, 30H ; 将十进制数字 0 的 ASCII 值加载到 DL 寄存器
AA2: 
    INC DL ; 千位计数
    SUB CX, 1000 ; 模 1000，获取百位及以下的数值
    CMP CX, 1000 ; 检查是否仍然大于等于 1000
    JNC AA2 ; 如果仍然大于等于 1000，则继续循环
    MOV AH, 2 ; 设置 AH 寄存器的值为 2，表示要打印字符
    INT 21H ; 调用 DOS 中断 21H，显示千位数值

BB2: 
    CMP CX, 100 ; 比较结果和 100
    JC BB3 ; 如果结果小于 100，则跳转到标签 BB3

    MOV DL, 30H ; 将十进制数字 0 的 ASCII 值加载到 DL 寄存器
AA3: 
    INC DL ; 百位计数
    SUB CX, 100 ; 模 100，获取十位及以下的数值
    CMP CX, 100 ; 检查是否仍然大于等于 100
    JNC AA3 ; 如果仍然大于等于 100，则继续循环
    MOV AH, 2 ; 设置 AH 寄存器的值为 2，表示要打印字符
    INT 21H ; 调用 DOS 中断 21H，显示百位数值

BB3: 
    CMP CX, 10 ; 比较结果和 10
    JC BB4 ; 如果结果小于 10，则跳转到标签 BB4

    MOV DL, 30H ; 将十进制数字 0 的 ASCII 值加载到 DL 寄存器
AA4: 
    INC DL ; 十位计数
    SUB CX, 10 ; 模 10，获取个位数值
    CMP CX, 10 ; 检查是否仍然大于等于 10
    JNC AA4 ; 如果仍然大于等于 10，则继续循环
    MOV AH, 2 ; 设置 AH 寄存器的值为 2，表示要打印字符
    INT 21H ; 调用 DOS 中断 21H，显示十位数值

BB4: 
    MOV DL, CL ; 将 CX 的值（即个位数值）加载到 DL 寄存器
    ADD DL, 30H ; 将个位数值转换为 ASCII 字符
    MOV AH, 2 ; 设置 AH 寄存器的值为 2，表示要打印字符
    INT 21H ; 调用 DOS 中断 21H，显示个位数值

    MOV AH, 4CH ; 设置 AH 寄存器的值为 4CH，表示程序结束
    INT 21H ; 调用 DOS 中断 21H，程序结束

MAIN ENDP ; 主程序结束

FACT PROC NEAR ; FACT 子程序，用于计算 1!+2!+3!+4!+5!
    AND CX, 0FFH ; 将 CX 寄存器的高位清零，确保只使用低位的 8 位
    MOV AX, 0 ; 将 AX 寄存器的值设为 0，用于累加阶乘结果

LOP2: 
    CALL FANG ; 调用 FANG 子程序，计算当前循环计数的阶乘，并将结果存入 DX
    ADD AX, DX ; 将计算得到的阶乘结果累加到 AX 寄存器中
    DEC CX ; 循环计数减一
    CMP CX, 0 ; 检查循环计数是否为 0
    JNZ LOP2 ; 如果不为 0，则继续循环

    MOV CX, AX ; 将 AX 寄存器中的累加结果存入 CX 寄存器，作为最终的结果
    RET ; 返回到调用 FACT 子程序的位置

FACT ENDP ; FACT 子程序结束

FANG PROC NEAR ; FANG 子程序，用于计算阶乘
    PUSH CX ; 保存 CX 寄存器的值
    PUSH AX ; 保存 AX 寄存器的值
    AND CX, 0FFH ; 将 CX 寄存器的高位清零，确保只使用低位的 8 位
    MOV AX, 1 ; 将 AX 寄存器的值设为 1，用于计算阶乘

LOP1: 
    MUL CX ; 将 CX 寄存器的值乘以 AX 寄存器的值，结果存入 AX
    DEC CX ; 循环计数减一
    CMP CX, 0 ; 检查循环计数是否为 0
    JNZ LOP1 ; 如果不为 0，则继续循环

    MOV DX, AX ; 将 AX 寄存器中的结果存入 DX 寄存器，作为阶乘结果
    POP AX ; 恢复 AX 寄存器的值
    POP CX ; 恢复 CX 寄存器的值
    RET ; 返回到调用 FANG 子程序的位置

FANG ENDP ; FANG 子程序结束

CODE ENDS
END MAIN ; 程序结束
