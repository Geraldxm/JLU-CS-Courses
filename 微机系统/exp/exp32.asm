DATA SEGMENT
    DIGIT DB "DIGIT",0AH,0DH
    LETTER DB "LETTER",0AH,0DH
    OTHER DB "OTHER",0AH,0DH
    CAPITAL DB "CAPITAL",0AH,0DH
DATA ENDS

CODE SEGMENT
    ASSUME CS:CODE,DS:DATA

START:
    MOV AX,DATA
    MOV DS,AX

    MOV AH,07       ;接受输入不回显
    INT 21H         ;调用DOS中断21H


    ;疑问，这里小于0的也可以直接跳转到AA3，因为A和a都比0大
    CMP AL, 30H     ;比较AL寄存器中的值是否小于 '0' 的ASCII码
    ;JB AA1          ;如果小于 '0'，跳转到AA1标签
    JB AA3
    CMP AL, 39H     ;比较AL寄存器中的值是否大于 '9' 的ASCII码
    JA AA1          ;如果大于 '9'，跳转到AA1标签

    MOV CX, 7       ;设置循环次数为7，用于显示digit
    LEA SI, DIGIT   ;将DIGIT的地址加载到SI寄存器中
AA0:
    MOV DL, [SI]    ;将SI指向的内存位置（DIGIT）中的字节加载到DL寄存器中
    MOV AH, 2       ;设置中断功能号为2，表示显示字符
    INT 21H         ;调用DOS中断21H显示字符
    INC SI          ;SI指针递增，指向下一个字符
    LOOP AA0        ;循环，直到CX为0
    JMP BB          ;跳转到BB标签

AA1:
    CMP AL, 41H     ;比较AL寄存器中的值是否小于 'A' 的ASCII码
    JB AA3          ;如果小于 'A'，跳转到AA3标签
    CMP AL, 5AH     ;比较AL寄存器中的值是否大于 'Z' 的ASCII码
    JA AA2          ;如果大于 'Z'，跳转到AA2标签

    LEA SI, CAPITAL  ;将LETTER的地址加载到SI寄存器中
    MOV CX, 9       ;设置循环次数为8，用于显示字母
AA4:
    MOV DL, [SI]    ;将SI指向的内存位置（LETTER）中的字节加载到DL寄存器中
    MOV AH, 2       ;设置中断功能号为2，表示显示字符
    INT 21H         ;调用DOS中断21H显示字符
    INC SI          ;SI指针递增，指向下一个字符
    LOOP AA4        ;循环，直到CX为0
    JMP BB          ;跳转到BB标签

AA2:
    CMP AL, 61H     ;比较AL寄存器中的值是否小于 'a' 的ASCII码
    JB AA3          ;如果小于 'a'，跳转到AA3标签
    CMP AL, 7AH     ;比较AL寄存器中的值是否大于 'z' 的ASCII码
    JA AA3          ;如果大于 'z'，跳转到AA3标签

    LEA SI, LETTER  ;将LETTER的地址加载到SI寄存器中
    MOV CX, 8       ;设置循环次数为8，用于显示
AA5:
    MOV DL, [SI]    ;输出大写
    MOV AH, 2
    INT 21H
    INC SI
    LOOP AA5
    JMP BB

AA3:
    LEA SI, OTHER   ;输出其他
    MOV CX, 7
AA6:
    MOV DL, [SI]
    MOV AH, 2
    INT 21H
    INC SI
    LOOP AA6

BB:
    MOV AH, 4CH
    INT 21H

CODE ENDS
END START

;数字->START->AA0->BB
;大写->START->AA1->AA4->BB
;小写->START->AA1->AA2->AA5->BB
;其他->START->AA1->AA3
;    ->START->AA1->AA2->AA3