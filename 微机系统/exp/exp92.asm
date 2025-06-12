; 定义代码段
CSEG SEGMENT
ASSUME CS:CSEG

START PROC FAR
    PUSH CS
    POP DS

    ; 获取原 1CH 号的中断向量
    MOV AX, 351CH
    INT 21H
    MOV CS:WORD PTR OLD1C, BX
    MOV CS:WORD PTR OLD1C+2, ES ; 保存原 1CH 号的中断向量

    ; 设置新的 1CH 号的中断向量
    MOV DX, OFFSET INT1C
    MOV AX, 251CH
    INT 21H

    ; 此后，每 55 毫秒就进入一次新的 1CH 号的中断处理程序
WAITN:
    MOV AH, 1
    INT 16H ; 查有无键按下
    JZ WAITN ; 转等待键按下

    MOV AH, 0
    INT 16H ; 读键盘

    LDS DX, CS:OLD1C
    MOV AX, 251CH
    INT 21H ; 恢复原 1CH 中断向量

    MOV AH, 4CH
    INT 21H ; 返回 DOS
START ENDP

; 定义数据空间
OLD1C DD ? ; 保存原中断向量
COUNT DW 0 ; 调用 1CH 中断程序的次数
HHH DB ?, ?, ':' ; "时"
MMM DB ?, ?, ':' ; "分"
SSS DB ?, ?, '$' ; "秒"

; 定义新的 1CH 号的中断处理程序
INT1C PROC FAR
    CMP COUNT, 0 ; 调用次数为 "0" 时（1 秒到）
    JZ NEXT
    DEC COUNT ; 显示次数递减（第一次 18-1）
    IRET

NEXT:
    MOV COUNT, 18 ; 置显示次数初值
    STI
    PUSH DS ; 保护现场
    PUSH ES
    PUSH AX
    PUSH BX
    PUSH CX
    PUSH DX
    PUSH SI
    PUSH DI

    MOV AH, 2
    INT 1AH ; 读实时时钟

    MOV AL, CH ; 时送 AL
    CALL TTASC ; 转换成 ASCII 码
    MOV WORD PTR HHH, AX ; 保存时

    MOV AL, CL ; 分转换
    CALL TTASC
    MOV WORD PTR MMM, AX

    MOV AL, DH ; 秒转换
    CALL TTASC
    MOV WORD PTR SSS, AX

    CALL CLS ; 清屏

    MOV BH, 0
    MOV DX, 0140H   ;DH=01 DL=40
    MOV AH, 2
    INT 10H ; 设置光标（1，65）

    PUSH CS
    POP DS
    MOV DX, OFFSET HHH
    MOV AH, 9
    INT 21H ; 显示实时时钟

    POP DI
    POP SI
    POP DX
    POP CX
    POP BX
    POP AX
    POP ES
    POP DS
    IRET ; 中断返回
INT1C ENDP

; 将 AL 中的 BCD 数据转换成 ASCII 码存入 AX 中
TTASC PROC
    MOV AH, AL
    AND AL, 0FH
    SHR AH, 1
    SHR AH, 1
    SHR AH, 1
    SHR AH, 1
    ADD AX, 3030H
    XCHG AH, AL
    RET
TTASC ENDP

; 清屏子程序
CLS PROC
    MOV AX, 0600H
    MOV CX, 0
    MOV DX, 184FH
    MOV BH, 7
    INT 10H
    RET
CLS ENDP

CSEG ENDS
END START
