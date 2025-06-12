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

    MOV AH,07       ;�������벻����
    INT 21H         ;����DOS�ж�21H


    ;���ʣ�����С��0��Ҳ����ֱ����ת��AA3����ΪA��a����0��
    CMP AL, 30H     ;�Ƚ�AL�Ĵ����е�ֵ�Ƿ�С�� '0' ��ASCII��
    ;JB AA1          ;���С�� '0'����ת��AA1��ǩ
    JB AA3
    CMP AL, 39H     ;�Ƚ�AL�Ĵ����е�ֵ�Ƿ���� '9' ��ASCII��
    JA AA1          ;������� '9'����ת��AA1��ǩ

    MOV CX, 7       ;����ѭ������Ϊ7��������ʾdigit
    LEA SI, DIGIT   ;��DIGIT�ĵ�ַ���ص�SI�Ĵ�����
AA0:
    MOV DL, [SI]    ;��SIָ����ڴ�λ�ã�DIGIT���е��ֽڼ��ص�DL�Ĵ�����
    MOV AH, 2       ;�����жϹ��ܺ�Ϊ2����ʾ��ʾ�ַ�
    INT 21H         ;����DOS�ж�21H��ʾ�ַ�
    INC SI          ;SIָ�������ָ����һ���ַ�
    LOOP AA0        ;ѭ����ֱ��CXΪ0
    JMP BB          ;��ת��BB��ǩ

AA1:
    CMP AL, 41H     ;�Ƚ�AL�Ĵ����е�ֵ�Ƿ�С�� 'A' ��ASCII��
    JB AA3          ;���С�� 'A'����ת��AA3��ǩ
    CMP AL, 5AH     ;�Ƚ�AL�Ĵ����е�ֵ�Ƿ���� 'Z' ��ASCII��
    JA AA2          ;������� 'Z'����ת��AA2��ǩ

    LEA SI, CAPITAL  ;��LETTER�ĵ�ַ���ص�SI�Ĵ�����
    MOV CX, 9       ;����ѭ������Ϊ8��������ʾ��ĸ
AA4:
    MOV DL, [SI]    ;��SIָ����ڴ�λ�ã�LETTER���е��ֽڼ��ص�DL�Ĵ�����
    MOV AH, 2       ;�����жϹ��ܺ�Ϊ2����ʾ��ʾ�ַ�
    INT 21H         ;����DOS�ж�21H��ʾ�ַ�
    INC SI          ;SIָ�������ָ����һ���ַ�
    LOOP AA4        ;ѭ����ֱ��CXΪ0
    JMP BB          ;��ת��BB��ǩ

AA2:
    CMP AL, 61H     ;�Ƚ�AL�Ĵ����е�ֵ�Ƿ�С�� 'a' ��ASCII��
    JB AA3          ;���С�� 'a'����ת��AA3��ǩ
    CMP AL, 7AH     ;�Ƚ�AL�Ĵ����е�ֵ�Ƿ���� 'z' ��ASCII��
    JA AA3          ;������� 'z'����ת��AA3��ǩ

    LEA SI, LETTER  ;��LETTER�ĵ�ַ���ص�SI�Ĵ�����
    MOV CX, 8       ;����ѭ������Ϊ8��������ʾ
AA5:
    MOV DL, [SI]    ;�����д
    MOV AH, 2
    INT 21H
    INC SI
    LOOP AA5
    JMP BB

AA3:
    LEA SI, OTHER   ;�������
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

;����->START->AA0->BB
;��д->START->AA1->AA4->BB
;Сд->START->AA1->AA2->AA5->BB
;����->START->AA1->AA3
;    ->START->AA1->AA2->AA3