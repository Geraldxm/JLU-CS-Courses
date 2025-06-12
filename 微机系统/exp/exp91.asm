DATA SEGMENT
    STRING DB 'ERROR!DIVIDE BY ZERO!',0AH,0DH,'$'
DATA ENDS

CODE SEGMENT
    ASSUME CS:CODE, DS:DATA

MAIN PROC FAR
    LEA DX, INT0 ;中断服务程序偏移地址
    MOV AX, CS
    MOV DS, AX
    MOV AL, 32 ;选用32号软中断
    MOV AH, 25H ;调用设置中断向量中断
    INT 21H
    MOV AX, DATA
    MOV DS, AX
    MOV CX, 10 ;9/BL做10次
    MOV BL, 6 ;BL初值设为6.
A1:
    MOV AX, 9
    DIV BL
    ADD AL, 30H ;将十进制数转换成十六进制数
    MOV DL, AL ;显示除法结果
    MOV AH, 2
    INT 21H ;显示字符
    MOV DL, 0DH
    MOV AH, 2
    INT 21H
    MOV DL, 0AH
    MOV AH, 2
    INT 21H
    CMP CX,0
    JZ A3 
    DEC BL
    CMP BL, 0   ;当除数为0时
    JZ A2
    LOOP A1
A3: 
    MOV AH,4CH
    INT 21H
A2:
    INT 32
    SUB CX,1
    ;MOV AH, 4CH
    ;INT 21
    JMP A1
MAIN ENDP

INT0 PROC FAR ;中断服务程序
    LEA DX, STRING
    MOV AH, 9 ;调用字符串输出中断
    INT 21H
    MOV BL, 3 ;BL赋为3
    IRET ;返回主程序
INT0 ENDP
CODE ENDS
    END MAIN

CODE ENDS

END MAIN
