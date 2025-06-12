DATA SEGMENT
DATA ENDS

CODE SEGMENT
ASSUME CS:CODE, DS:DATA

START:
    MOV AL, 'A' ; 要显示的字符
    MOV DX, 10H ; 显示个数为 16
    MOV BL, 0F0H ; 初始化颜色，前景为黑色，背景为白色

A1:
    PUSH DX
    MOV AH, 9 ; 显示带有属性的字符
    MOV BH, 0 ; 页号为 0
    MOV CX, 1 ; 显示一个字符
    INT 10H

    MOV AH, 3 ; 读取光标位置
    MOV BH, 0 ; 页数为 0
    INT 10H

    ADD DL, 1 ; 递增光标的列号
    ADD DH, 1 ; 递增光标的行号

    MOV AH, 2 ; 设置光标
    INT 10H

    ADD BL, 1 ; 字符属性加 1，改变前景颜色
    POP DX
    DEC DX ; 显示字符个数统计
    JNZ A1

    MOV AH, 4CH
    INT 21H

CODE ENDS
END START
