DATA SEGMENT
DATA ENDS

CODE SEGMENT
ASSUME CS:CODE, DS:DATA

START:
    MOV AL, 'A' ; Ҫ��ʾ���ַ�
    MOV DX, 10H ; ��ʾ����Ϊ 16
    MOV BL, 0F0H ; ��ʼ����ɫ��ǰ��Ϊ��ɫ������Ϊ��ɫ

A1:
    PUSH DX
    MOV AH, 9 ; ��ʾ�������Ե��ַ�
    MOV BH, 0 ; ҳ��Ϊ 0
    MOV CX, 1 ; ��ʾһ���ַ�
    INT 10H

    MOV AH, 3 ; ��ȡ���λ��
    MOV BH, 0 ; ҳ��Ϊ 0
    INT 10H

    ADD DL, 1 ; ���������к�
    ADD DH, 1 ; ���������к�

    MOV AH, 2 ; ���ù��
    INT 10H

    ADD BL, 1 ; �ַ����Լ� 1���ı�ǰ����ɫ
    POP DX
    DEC DX ; ��ʾ�ַ�����ͳ��
    JNZ A1

    MOV AH, 4CH
    INT 21H

CODE ENDS
END START
