DATA SEGMENT
    MATRIX1 DB 01H,02H,03H,04H
            DB 04H,03H,02H,01H
            DB 02H,04H,06H,08H
            DB 08H,06H,04H,02H
    MATRIX2 DB 01H,01H,01H,01H
    UNIT    DW 4 DUP(0)
DATA ENDS
CODE SEGMENT
    ASSUME CS:CODE, DS:DATA

START:
    MOV AX,DATA
    MOV DS,AX   ;将DS段设为DATA
    LEA SI,MATRIX1
    LEA DI,MATRIX2
    LEA BX,UNIT
    MOV CL,4H   ;CL控制大循环
AA0:
    SUB AX,AX   ;AX清零
    MOV CH,4H   ;CH控制小循环
AA1:
    MOV DX,AX
    MOV AL,[DI] ;把m2的数取入AL
    MUL BYTE PTR[SI]    
    ;MUL乘法，8位时，一个数默认在AL；16位时，一个数默认在AX
    ;8位乘法，结果放在AX；16位，结果高位在DX，低位在AX；
    ;故此处8位乘法，结果在AX
    ADD AX,DX   ;DX存放中间结果，AX存放总结果
    INC SI  ;matric1指向下一个
    INC DI  ;matric2指向下一个
    DEC CH  
    JNZ AA1 ;返回小循环   
    LEA DI,MATRIX2  ;把DI重新指向开头
    MOV [BX],AX ;把和放到[BX] 
    INC BX  ;UNIT指向下一个
    INC BX  ;DW，得加两次
    DEC CL
    JNZ AA0
    MOV AH,4CH
    INT 21H
CODE ENDS
END START
