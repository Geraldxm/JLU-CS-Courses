data segment
    NUMBER1 DB 1,2,3,4,5,6,7,8,9,10
    SUM1    DW ?
ends
code segment
        ASSUME  CS:CODE,DS:DATA
START:  MOV AX,DATA
        MOV DS,AX
        LEA BX,NUMBER1-1
        MOV AX,10   ;放初值为最后的值
        MOV DH,0
AA1:    INC BX         
        MOV DL,[BX] ;取数
        CMP DL,10
        JZ AA2
        ADD AX,DX   ;求和
        JMP AA1
AA2:    MOV SUM1,AX ;存和
        MOV AH, 4CH
        INT 21H
ends
end start
               