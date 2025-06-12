; .exe文件结构
DATA SEGMENT
 ;
DATA ENDS

STACK SEGMENT
    DW 128 DUP(0)
STACK ENDS

CODE SEGMENT
MAIN PROC FAR
    ASSUME CS:CODE, DS:DATA, SS:STACK
START:
    PUSH DS
    MOV AX, 0
    PUSH AX
    MOV AX, DATA
    MOV DS, AX
    MOV ES, AX

    ...
    MOV AH, 4CH
    INT 21H
MAIN ENDP
CODE ENDS
    END START

; .com文件结构

CODE SEGMENT    ;智能指定一个段
    ORG 100H    ;设置起始偏移量
    ASSUME CS:CODE, DS:CODE
START:
    MOV AX, CS
    MOV DS, AX
CODE ENDS
    END START

;统计AX中1、0个数的源程序，1的个数存入CH，0的个数存入CL
;注意，被移出的一位会进入CF
ORG 100H
    MOV AX, 0FFH
    MOV DL, 16
    MOV CH, 0
    MOV CL, 0
LP: SHR AX, 1
    JC A1
    INC CL
    JMP NXT
A1: INC CH
NXT:
    DEC DL
    JNZ LP
    RET

;从键盘输入十进制数，输入的十进制数*2后保存到X中，以十进制输出
DATA SEGMENT
    X DB ?
    S1 DB "please input:$"
    S2 DB "the ans is:$"
DATA ENDS
STACK SEGMENT
    DW 128 DUP(0)
STACK ENDS
CODE SEGEMENT
    ASSUEME CS:CODE, DS:DATA, SS:STACK
MAIN PROC FAR
START:
    PUSH DS
    MOV AX, 0
    PUSH AX
    MOV AX, DATA
    MOV DS, AX
    MOV ES, AX
    ;显示字符串
    MOV AH, 09H
    LEA DX, S1
    INT 21H
    ;键入第一个数字，十位，需要乘10
    MOV AH, 01H ;读入到AL
    INT 21H
    ADD BL, AL
    ADD BL, AL
    SHL AL, 3 ;*8
    ADD BL, AL  ;*10完成，保存在BL中
    MOV X, AL   ;把结果保存到X中
    ;键入第二个数组，个位
    MOV AH, 01H
    INT 21H
    ADD AL, BL ;整个数读入到AL完成
    SHL AL, 1 ;乘2
    ;换行和输出
    MOV DL, 0AH
    MOV AH, 2
    INT 21H
    MOV DL, 0DH
    MOV AH, 2
    INT 21H
    MOV AH, 09H
    LEA DX, S2
    INT 21H
    ;显示百位，十位，个位
    MOV CL, X
    MOV DL, 0   ;注意用DL来存每次输出的位的值
A100:
    CMP CL, 100
    JB DIS100
    SUB CL, 100
    INC DL
    JMP A100
DIS100:
    OR DL, 30H  ;相当于+48
    MOV AH, 2
    INT 21H
    MOV DL, 0   ;清空DLL
A10:
    CMP CL, 10
    JB DIS10
    SUB CL, 10
    INC DL
    JMP A10
DIS10:
    OR DL, 30H
    MOV AH, 2
    INT 21H
DIS10:
    MOV DL, CL
    OR DL, 30H
    MOV AH, 2
    INT 21H
    ;结束
    MOV AH, 4CH
    INT 21H
MAIN ENDP
CODE ENDS
    END START




;从键盘上读取一个4位十六进制数
;在显示器上显示等值二进制
DATA SEGMENT
    RES DB 4 DUP(?)
DATA ENDS
STACK SEGMENT STACK
    DW 100H DUP(?)
STACK ENDS
CODE segment
    ASSUME CS:CODE, DS:DATA, SS:STACK
MAIN PROC FAR
START:
    PUSH DS
    MOV AX, 0
    PUSH AX
    MOV AX, DATA
    MOV DS, AX
    MOV ES, AX
re:
    MOV CX, 4 ;计数次数
    LEA SI, RES
    ;输入4个十六进制数，保存到RES里
input:
    MOV AH, 1
    INT 21H
    CMP AL, 30H ;是否是数字，否则重新输入
    JB input
    CMP AL, 39H ;是否大于9，也就是ABCDEF
    JA H2N
    AND AL, 0FH ;0~9
    JMP STORE
H2N:    ;认为输入的是大写，若要处理小写，再比一下
    CMP AL, 41H ;是否小于65='A'
    JB input
    CMP AL, 46H ;是否大于F
    JA h2n
    SUB AL, 37H ;-55,此时A=10
h2n:
    CMP AL,61H
    JB input
    SUB AL, 57H
;处理完了，把大小写和数字ascii转换成了数字
STORE:
    MOV [SI], AL
    INC SI
    LOOP input
;换行
    MOV DL, OAH
    MOV AH, 2
    INT 21H
    MOV DL, 0DH
    MOV AH, 2
    INT 21H
;以二进制显示输出
;思路是把这个数（4位）先移动到高位
;然后一次左移一位，输出
    MOV BL, 4
    LEA SI, RES
BB0:
    MOV BH, [SI]
    SHL BH, 4
    MOV CX, 4
BB1:
    SHL BH, 1
    MOV DL, 30H
    JNC BB2
    MOV DL, 31H
BB2:
    MOV AH, 2
    INT 21H
    LOOP BB1    ;一个数结束
    INC SI
    DEC BL
    JNZ BB0
    MOV DL, 'B' ;整个结束
    MOV AH, 2
    INT 21H
    MOV AH, 4CH
    INT 21H
MAIN ENDP
CODE ENDS
    END START


;读取一个字符串，使其按照ASCII码的顺序由大到小输出到屏幕
    ;读入字符串，得到长度
    ;两重循环，进行排序
    ;逐个输出即可
data segment
    s db 100 dup(1)
    n dw 0
data ENDS
stack segement
    dw 128 dup(1)
stack ENDS
code segment
assume ds:data, ss:stack, cs:code
main proc far
start:
    push ds;
    mov ax,0
    push ax;
    mov ax,data
    mov ds,ax
    mov es,ax
;准备读入
    mov cx,0
    mov bx,s
in:
    mov ah,1
    int 21H
    cmp al,0DH
    jz sort
    mov [bx],AL
    int bx
    inc cx
    jmp in
sort:
    mov n,cx
    ;需要一个两重循环
    mov dh,n
    mov dl,n
outer:
    dec dh
    cmp dh,0
    jz print
    lea si,s
inner:
    dec dl
    cmp dl,0
    jz outer
    mov al,[si]
    cmp al,[si+1]
    jae nxt;
    xchg al,[si+1]
    mov [si],al
nxt:
    inc si;
    jmp inner;
print:
    mov cl,n
    lea bx,s
disnxt:
    mov al,[bx]
    mov ah,2
    int 21H
    dec cl
    jz/loop disnxt
    MOV AH, 4CH
    INT 21H
MAIN ENDP
CODE ENDS
    END START

;从BUFFER开始存放一些带符号字节数据，将其中的正数从大到小放到内存中的PLUS中
data segemnt
    bf db 0h,1h,2h,3h,f1h,f2h,f3h,f4h
    plus db 20 dup(0)
    n dw 0
data ends
stack segment
    dw 128 dup(0)
stack ends
code segement
assume ds:data,ss:stack,cs:code
main proc far
    push ds
    mov ax,0
    push ax
    mov ax,data
    mov ds,ax
    mov es,ax
    call calc
    call sort
    call print
    mov ah,4CH
    int 21H
mian ENDP
calc proc NEAR
    push ax
    push dx
    push cx
    mov cx, 0
    mov ax, 0
    lea si, plus
    lea bx, bf
nxt:
    mov al,[bf]
    cmp al,'$'  ;24h
    jz a2
    test al,80H ;做逻辑与运算，1000 0000 B
    jnz A1  ;结果非零，也就是负数
    mov [si],al
    inc si
    inc bx
    inc cx
    jmp aa1
a1:
    inc bx
    jmp nxt
a2:
    mov n,cx
    pop cx
    pop dx
    pop ax
    RET
calc ENDP
sort proc NEAR
    push ax
    push bx
    push cx
    push dx
    mov dx, n
outer:
    mov cx, dx
inner:
    mov al, [bx]
    cmp al, [bx+1]
    jae X
    xchg al, [bx+1]
    mov [bx], al
x:
    inc bx
    loop inner
    dec dx
    jnz outer

    pop dx
    pop cx
    pop bx
    pop ax
    RET
sort ENDP
print proc NEAR
    ;不能直接输出，高低四位单独输出
    push ax
    push bx
    push dx
    mov cx,n
    lea bx,plus
    mov al,[bx]
high:
    mov dl,AL
    shr dl,4
    add dl,30H
    mov ah,2
    int 21h
low:
    mov dl,al
    and dl,0FH
    add dl,30H
    cmp dl,3AH
    JB low2
    add dl,7
low2:
    mov ah,2
    int 21H
    dec cx
    int si
    jnz high
    RET
print endp

; 初始化8255
init_8255 PROC NEAR
    MOV DX, PORT_8255
    MOV AL, 10000000B
    OUT DX, AL
    RET
init_8255 ENDP
