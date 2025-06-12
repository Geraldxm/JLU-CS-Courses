DSEG SEGMENT PARA PUBLIC'DSEG' ;定义可读写、可执行公开的段"DSEG"
M	DB	80H,81H,82H,83H,84H,90H,91H,92H,93H,94H,70H,71H,72H,73H,74H,40H,41H,42H,43H,44H;数组M，每个元素1字节
P	DB	20 DUP(?) ;数组P，长度20
N	DB	20 DUP(?) ;数组N，长度20
DSEG ENDS

CODE SEGMENT
ASSUME CS:CODE,DS:DSEG

START:	MOV AX,DSEG
		MOV DS,AX
		LEA SI,P 	;正数数组首地址
		LEA DI,N 	;负数数组首地址
		LEA BX,M 	;输入数组首地址
		XOR AX,AX
		XOR DX,DX	;清零
		MOV CX,20	;设置循环次数
L1:		MOV AL,[BX] ;从数组中取出一个数
		TEST AL,80H ;AL寄存器的最高位与1进行与操作
		JZ L2 		;结果为0，也就是AL最高位不为1，说明AL为正，则跳转L2
		MOV [DI],AL ;否则AL为负数，放入负数
		INC BX
		INC DI
		INC DH 		;BX++,DI++,DH++
		JMP L3
L2:		MOV [SI],AL ;放入正数
		INC BX		;BX++，SI++，DL++
		INC SI
		INC DL 		;正数个数+1
L3:		LOOP L1		;循环执行L1
		MOV CX,2	;DH存的是负数个数，DL存的是正数个数
L5:		MOV BL,DL 	;DL的内容，也就是正数个数，先复制到BL，然后每4位输出
		SHR DL,1
		SHR DL,1
		SHR DL,1
		SHR DL,1	;SHR指令将DL寄存器右移4位，相当于除以16，用于获取DL的高4位数
		AND DL,0FH	;AND指令与0FH进行与操作，用于保留DL的低4位数
		;CMP DL,10	;CMP指令用于将DL与10进行比较，以判断是否大于9
		;注意！这里就是10，不是10H！
		SUB DL,10

		;如果DL大于等于10，即数字超过9，需要调整为正确的ASCII字符值。
		;ADD指令将DL加上7，调整为对应的大写字母的ASCII值
		;如，10变为'A'，11变为'B'

		JB L4		;JB(小与则跳转)，未超过9，跳转L4
		ADD DL,7 	;若待输出的数字大于9，加7调整后输出
L4:		ADD DL,30H 	;将DL加上30H，将其转换为ASCII码

		ADD DL,10
		;将DL加上30H（即48）的目的是将DL中存储的数字转换为相应的ASCII字符

		MOV AH,2
		INT 21H		;然后通过INT 21H调用DOS的显示字符功能，输出DL中的字符。
		MOV DL,BL	;对刚刚保留的低四位（BL中）做相同的操作，输出
		AND DL,0FH
		CMP DL,10
		JB L6
		ADD DL,7
L6:		ADD DL,30H
		MOV AH,2
		INT 21H
		MOV DL,0AH ;换行
		MOV AH,2
		INT 21H
		MOV DL,0FH ;回车
		MOV AH,2
		INT 21H
		MOV DL,DH ;再输出负数个数
		LOOP L5
		MOV AH,4CH
		INT 21H

CODE ENDS
END START
		