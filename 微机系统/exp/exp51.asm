DATA SEGMENT
    NUMBER1 DB 8 DUP(0) ; 被加数参数列表
    NUMBER2 DB 8 DUP(0) ; 加数参数列表
    NUMBER3 DB 9 DUP(0) ; 和参数列表
DATA ENDS

CODE SEGMENT
ASSUME CS:CODE, DS:DATA
MAIN PROC FAR ; 主程序定义为FAR类型
    MOV AX, DATA
    MOV DS, AX
    CALL SUB1 ; 调用SUB1非压缩BCD码接收子程序
    POP CX ; 取被加数位数，也用于控制循环
    LEA BX, NUMBER1 ; 取被加数参数表地址
AA3:
    POP AX ; 取被加数个位、十位、百位...
    MOV [BX], AL ; 被加数存入参数表
    INC BX ; 形成下位地址
    LOOP AA3
    MOV AH, 3 ; 查找当前光标位置
    ;调用参数BH=页号，返回CH=光标开始行，CL=光标结束行
    ;DH=行，DL=列
    INT 10H
    MOV AH, 2 ; 设置光标位置
    ;设置光标位置，BH=页号，DH=行，DL=列
    MOV DL, 8   ;在第8列输出一个+号                              
    INT 10H
    MOV DL, 2BH ; 输出+号
    MOV AH, 2
    INT 21H
    CALL SUB1 ; 调用SUB1非压缩BCD码接收子程序
    POP CX ; 取加数位数
    LEA BX, NUMBER2 ; 取加数参数表地址
AA4:
    POP AX ; 取加数个位、十位、百位..
    MOV [BX], AL ; 加数存入参数表
    INC BX ; 形成下位地址
    LOOP AA4
    MOV AH, 3 ; 查找当前光标位置
    INT 10H
    MOV AH, 2 ; 设置光标位置
    MOV DL, 17
    INT 10H
    MOV DL, 3DH ; 输出=号
    MOV AH, 2
    INT 21H
    LEA SI, NUMBER1 ; 取被加数参数表地址
    LEA DI, NUMBER2 ; 取加数参数表地址
    LEA BX, NUMBER3 ; 取和参数表地址
    SUB CX, CX ; 0 → CF
    MOV CX, 8 ; 加位数计数器初值
AA5:
    MOV AL, [SI] ; 取被加数
    ADC AL, [DI] ; 非压缩BCD码加
    AAA
    MOV [BX], AX ; 将和存到NUMBER3中
    INC DI ; 形成下一地址
    INC SI ; 形成下一地址
    INC BX ; 形成下一地址
    LOOP AA5
    ADC CL, CL ; 最高位送CL
    MOV [BX], CL ; 存最高位
    LEA AX, NUMBER3+8 ; 取和参数表最高位地址
    PUSH AX ; 向子程序提供和参数表最高位地址
    CALL SUB2 ; 非压缩BCD码显示子程序
    MOV CX, 16
    LEA BX, NUMBER1 ; 取被加数参数表地址
    XOR AL, AL
QQQ2:
    MOV [BX], AL
    INC BX
    LOOP QQQ2
    MOV AH, 4CH ; 返回DOS
    INT 21H
MAIN ENDP 

SUB1 PROC NEAR ; 非压缩BCD码数据输入子程序
    POP BX ; 保存返回地址
    SUB CX, CX ; 键入位数计数器清0
AA1:
    MOV AH, 1
    INT 21H
    CMP AL, 30H ; 判小于0键返主程序
    JC AA2  ;进位跳转
    CMP AL, 3AH ; 判大于9键返主程序
    ;由于CF=0是大于等于的情况，所以用3AH而不是39H
    ;也就是≥10
    JNC AA2
    INC CX ; 输入数据位数
    PUSH AX ; 非压缩BCD码压栈
    JMP AA1
AA2:
    PUSH CX ; 输入数据位数压栈
    PUSH BX ; 返回地址压栈
    RET ; 返回主函数
SUB1 ENDP

SUB2 PROC NEAR ; 非压缩BCD码显示子程序
    POP AX ; 保存返回地址
    POP BX ; 取和参数表最高位地址
    PUSH AX ; 返回地址压栈
    MOV CX, 9
AA7:
    MOV AL, [BX] ; 消除头部的无效数字0
    CMP AL, 0
    JNZ AA6
    DEC CX
    DEC BX
    JMP AA7
AA6:
    MOV DL, [BX] ; 取和最高位、次高位..个位
    ADD DL, 30H ; 形成ASCII码
    MOV AH, 2
    INT 21H
    DEC BX
    LOOP AA6
    RET
SUB2 ENDP

CODE ENDS
END MAIN
