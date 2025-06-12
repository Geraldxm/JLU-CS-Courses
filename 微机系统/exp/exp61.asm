DATA SEGMENT
ARRAY1 DW 5 DUP(212FH,5A5DH,1234H,7A65H)) ;待查找数组
EOD DW 0 ;结束标记，用于计算数组个数
DATA ENDS

CODE SEGMENT
ASSUME CS:CODE,DS:DATA

MAIN PROC FAR ;主函数定义为远程调用
    MOV AX,DATA
    MOV DS,AX ;将DS寄存器设置为数据段的段地址
    LEA SI,ARRAY1 ;将数组的起始地址加载到SI寄存器
    MOV CX,(EOD-ARRAY1)/2 
    ;计算数组中元素总个数，/2是因为其中每个元素占两个字节
    CALL SUB3 ;调用查找子程序 SUB3，查找数组中的最大元素及其位置
        ;最大元素段内地址通过SI传出
        ;最大元素的值通过CX传出
    MOV DI,CX ;最大元素所在的段地址保存在DI寄存器中
    MOV BX,DATA
    CALL SUB4 ;调用显示子程序，显示最大元素的段地址
    MOV DL,':'
    MOV AH,2
    INT 21H ;显示冒号字符
    MOV BX,SI ;最大元素的段内偏移保存在BX寄存器中
    CALL SUB4 ;调用显示子程序，显示最大元素的段内偏移
    MOV DL,20H ;显示空格
    MOV AH,2
    INT 21H
    MOV BX,DI ;传出数组中的最大元素的值保存在BX寄存器中
    CALL SUB4 ;显示查找到的最大值
    MOV AH,4CH
    INT 21H ;程序结束，返回DOS

SUB3 PROC NEAR ;查找子程序，查找数组中最大元素及其位置
    PUSH AX ;相关寄存器保护
    PUSH DI
    MOV DI,SI ;DI中保存最大值的段内地址，先放起始地址
    MOV AX,[SI] ;AX中保存最大值，先放起始值
AA1: CMP AX,[SI] ;比较当前元素与最大值
    JNC AA2 ;如果当前元素大于等于最大值，跳转到AA2
    MOV AX,[SI] ;更新最大值
    MOV DI,SI ;更新最大值的段内地址
AA2: ADD SI,2 ;移动到下一个元素
    LOOP AA1 ;循环直到处理完所有元素
    MOV SI,DI ;最大元素段内地址通过SI传出
    MOV CX,AX ;最大元素的值通过CX传出
    POP DI ;恢复保护的寄存器
    POP AX
    RET ;返回调用子程序的位置
SUB3 ENDP

SUB4 PROC NEAR ;十六进制数显示子程序
    MOV DL,BH ;处理BX中高8位
    MOV CL,4
    ;这里先把BH的低4个字节右移去除，高位4个字节先显示
    SHR DL,CL ;将高8位右移4位，获取高位的十六进制数
    CALL SUB5 ;调用十六进制数显示子程序
    MOV DL,BH
    ;将BX高8位中的高位掩码为0，获取低位的十六进制数
    AND DL,0FH 
    CALL SUB5 ;调用十六进制数显示子程序
    ;后面处理BX的低8位，类似操作
    MOV DL,BL ;处理BX中低8位
    SHR DL,CL
    CALL SUB5
    MOV DL,BL
    AND DL,0FH
    CALL SUB5
    RET
SUB4 ENDP

SUB5 PROC NEAR ;十六进制数显示子程序
    OR DL,30H ;将DL中的数值转换为ASCII码表示的字符
    CMP DL,3AH ;比较DL与ASCII码表示的字符':'
    JC AA3 ;如果小于':'，跳转到AA3
    ;这里是在处理数字>9的情况
    ADD DL,7 ;否则将DL中的数值加上7，得到大写字母的ASCII码
AA3: MOV AH,2
    INT 21H ;显示DL中的字符
    RET ;返回调用子程序的位置
SUB5 ENDP

CODE ENDS

END MAIN ;程序结束
