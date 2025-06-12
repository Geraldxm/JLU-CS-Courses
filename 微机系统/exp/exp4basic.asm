DATA SEGMENT
COURSE1 DB 70H,88H,92H,90H,99H          ; 第一门课成绩
        DB 67H,77H,88H,76H,69H          ; 第二门课成绩
        DB 74H,87H,77H,74H,70H          ; 第三门课成绩
        DB 99H,97H,94H,98H,96H          ; 第四门课成绩
NUM1 DW 5 DUP(0)                        ; 存储总分的数组
DATA ENDS

CODE SEGMENT
    ASSUME CS:CODE, DS:DATA
START:
    MOV AX, DATA
    MOV DS, AX
    LEA SI, COURSE1
    LEA DI, NUM1
    SUB SI, 5                       ; COURSE1首址减5以便循环
    MOV CL, 5                       ; 外层循环计数初值

AAl:
    MOV BX, SI                      ; 形成某个学生第一门课成绩的首址减5
    SUB AX, AX                      ; 清和寄存器
    MOV CH, 4                       ; 内循环计数初值

AA2:
    ADD BX, 5                       ; 形成某个学生1、2...4门课成绩地址
    ADD AL, [BX]                    ; 按BCD码求和
    DAA                             ; 压缩BCD码加调整
    ADC AH, 0                       ; 累加进位
    DEC CH                          ; 内循环计数控制
    JNZ AA2

    MOV [DI], AX                    ; 存总分
    INC SI                          ; 形成下个学生第一门课成绩的首址减5
    ADD DI, 2                       ; 形成下个学生的总分地址
    DEC CL                          ; 外循环计数控制
    JNZ AAl

    MOV AH, 4CH
    INT 21H
CODE ENDS

END START
