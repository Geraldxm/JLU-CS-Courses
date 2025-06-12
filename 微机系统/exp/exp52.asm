DATA SEGMENT
    ARRAY DB 13,24,92,42,25,46,75,81,53,01
    MA DB 'MAX= $'
    MI DB 0DH,0AH,'MIN= $'
DATA ENDS

CODE SEGMENT
ASSUME CS:CODE,DS:DATA

MAIN PROC FAR; 主程序定义为FAR类型
    MOV AX,DATA
    MOV DS,AX
    LEA DX,MA
    MOV AH,9; 输出MAX=
    INT 21H ;AH=09,功能为显示字符串
    LEA BX,ARRAY
    CALL MAX; 调用MAX字程序查找最大数值
    CMP DL,10; 是否为两位数
    JC AA6  ;个位数则跳转
    MOV DH,30H  ;DH填入30H
AA5:
    INC DH; 十位数加一，31H，也就是1
    SUB DL,10; 模十，此时还没换成ASCII码
    CMP DL,10
    JNC AA5
    ;以这种方式将十位数字赋到DH
    PUSH DX; 保存两位数
    MOV DL,DH; 显示十位数
    MOV AH,2
    INT 21H
    POP DX; 恢复两位数
AA6:
    ADD DL,30H; 显示个位数，加上30H，用于显示ASCII码
    MOV AH,2
    INT 21H ;AH=02H，输出字符
    ;处理MIN
    LEA DX,MI; 输出“回车MIN=”
    MOV AH,9
    INT 21H
    LEA BX,ARRAY
    CALL MIN; 调用MIN查找最小数值
    CMP DL,10  ;是否为两位数
    JC AA8
    MOV DH,30H  ;和上面的操作一样，输出两位数
AA7:
    INC DH
    SUB DL,10
    CMP DL,10
    JNC AA7
    PUSH DX
    MOV DL,DH
    MOV AH,2; 输出十位数
    INT 21H
    POP DX
AA8:
    ADD DL,30H; 输出个位数
    MOV AH,2
    INT 21H     ;AH=02,显示DL输出
    MOV AH,4CH; 返回DOS
    INT 21H
MAIN ENDP

MAX PROC NEAR; 最大值查找子程序
    MOV CX,10; 查找计数
    MOV DL,[BX]; 初始化DL，以便后面冒泡查找
AA1:
    MOV DH,[BX] ;每次取一个放到DH，和DL比较
    CMP DL,DH
    JC AA2  ;若DL小
    INC BX; 形成下一查找位置
    DEC CX; 查找计数减一
    CMP CX,0
    JZ OUT1
    JMP AA1
AA2:
    MOV DL,DH   ;替换DL
    INC BX
    DEC CX
    CMP CX,0
    JZ OUT1
    JMP AA1
OUT1:
    RET; 返回主函数
MAX ENDP

MIN PROC NEAR; 最小值查找子程序，查找过程与MAX函数类似
    MOV CX,10
    MOV DL,[BX]
AA3:
    MOV DH,[BX]
    CMP DL,DH
    JNC AA4
    INC BX
    DEC CX
    CMP CX,0
    JZ OUT2
    JMP AA3
AA4:
    MOV DL,DH
    INC BX
    DEC CX
    CMP CX,0
    JZ OUT1
    JMP AA3
OUT2:
    RET

MIN ENDP

CODE ENDS
END MAIN
