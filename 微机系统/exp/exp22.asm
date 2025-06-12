DATA SEGMENT
NOTIC DB "Please input the word!",0AH,0DH
;定义了一个名为NOTIC的字符串变量
;0AH,0DH是换行符
DATA ENDS
CODE SEGMENT
    ASSUME CS:CODE,DS:DATA
START:  MOV AX,DATA
        MOV DS,AX       ;DS指向数据段
        MOV CX,19H      ;设置循环次数,输出提示用
        LEA BX,[NOTIC]  ;LEA指令将NOTIC变量的有效地址加载到BX寄存器中
AA0:    MOV DL,[BX]     ;输出提示
        MOV AH,2
        INT 21H
        INC BX
        LOOP AA0
AA1:    MOV AH,1        ;设置功能AH=1，设置中断为输入一个字符并回显在屏幕
        INT 21H         ;输入的值存储在AL
        CMP AL,1BH      ;是ESC则跳转
        JZ AA3          ;判断是小写就转换，否则不变
        CMP AL,61H      ;判断是不是小写      
        JS AA2          ;AL-a,若负跳转
        CMP AL,7BH      
        JNS AA2         ;AL-z,若非负（正数或0）跳转，那Z无法转换？故应改成7BH
        SUB AL,20H      ;将小写转换为大写
AA2:    MOV DL,AL
        MOV AH,2
        INT 21H         ;设置功能码AH=2,中断，输出AL中的值到屏幕
        LOOP AA1
AA3:    MOV AH,4CH      ;设置功能码4CH，表示终止
        INT 21H
CODE ENDS
        END START                                    