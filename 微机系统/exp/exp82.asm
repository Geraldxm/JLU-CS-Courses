WR      MACRO CHA,ATR,NUM ;宏定义,显示处理
        MOV AH,2 ;设置光标位置
        INT 10H
        MOV AL,CHA ;要显示的字符送 AL
        MOV CX,NUM ;要显示字符的个数送 CX
        MOV BL,ATR ;要显示字符的颜色属性
        MOV AH,9
        INT 10H
        ENDM
CODE SEGMENT
    ASSUME CS:CODE
MAIN PROC FAR
CAR:    MOV AH,0
        MOV AL,3 ;设置屏幕显示模式为 80*25 彩色字符方式
        INT 10H
LOP1:   MOV SI,0A0AH ;定义显示位置(10,10)
LOP2:   MOV DX,SI
        WR 0DBH,0CH,5 ;显示第一行
        INC DH ;调整行坐标
        SUB DL,2 ;调整列坐标
        WR 0DBH,4,9 ;显示第二行
        INC DH ;调整行坐标
        INC DL ;调整列坐标
        WR 09H,8EH,1 ;显示前轮
        ADD DL,6 ;调整列坐标
        WR 9,8EH,1 ;显示后轮
        CALL DELAY ;调用延时
LOP3:
        MOV DX,SI
        WR 0,0,5 ;清除第一行
        INC DH
        SUB DL,2
        WR 0,0,9 ;清除第二行
        INC DH
        WR 0,0,8 ;清除第三行
        MOV AH,1 ;判断是否有按键
        INT 16H
        JZ CONU
        MOV AH,4CH ;返回 DOS
        INT 21H
JLOP2:  JMP LOP2
CONU:   INC SI
        CMP SI,0A50H ;判断车是否跑到头
        JB JLOP2
        JMP LOP1
MAIN ENDP
DELAY   PROC NEAR ;延时子程序
        MOV CX,000FH
        LOOP $
        RET
DELAY ENDP
CODE ENDS
END MAIN