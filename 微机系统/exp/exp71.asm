DATA SEGMENT
    STRING DB 100 DUP(0) ; �������������ַ�������󳤶�Ϊ100�ֽ�
DATA ENDS

CODE SEGMENT
    ASSUME CS:CODE, DS:DATA

MAIN PROC FAR
    MOV AX, DATA
    MOV DS, AX
    LEA BX, STRING ; ���ַ�������ʼ��ַ���ص�BX�Ĵ���
    MOV CL, 0 ; Сд��ĸ������ʼ��Ϊ0
AA1:
    MOV AH, 1 ; �����ַ�
    INT 21H
    ;0DH���ǻس���ASCII��
    CMP AL, 0DH ; ������س�ʱ������Сд��ĸ���������
    JZ AA3
    CMP AL, 41H ; С�� 'A' ���ַ����˳�
    JC AA4
    CMP AL, 5BH ; ���� 'Z' ���ַ����˳�
    JNC AA2
    MOV [BX], AL ; ��������ַ������ڴ�
    INC BX ; �ƶ�����һ���洢λ��
    JMP AA1 ; ���������ַ�
AA2:
    CMP AL, 61H ; С�� 'a' ������ 'Z' ���ַ����˳�
    ; ����a
    JC AA4
    CMP AL, 7BH ; ���� 'z' ���ַ����˳���7BH=123��122��z
    JNC AA4
    MOV [BX], AL ; ��������ַ������ڴ�
    INC BX ; �ƶ�����һ���洢λ��
    INC CL ; Сд��ĸ������һ
    JMP AA1 ; ���������ַ�
AA3:
    PUSH CX ; ����Сд��ĸ������
    ;INT 10H AH=3��ȡ���λ��
    MOV AH, 3 ; ��ȡ��ǰ���λ��
    INT 10H
    MOV AH, 2 ; ���ù��λ��
    LEA DI, STRING ; ���ַ�������ʼ��ַ���ص�DI�Ĵ���
    SUB BX, DI ; ��������λ��
    ;BX�������ַ���������λ�ã�BX=BX-DI��ʹ���������ַ����ĳ���
    MOV DL, BL ; �������λ�ñ��浽DL�Ĵ���
    ;�����ƶ�һλ���������
    INC DL ; �������ƶ�һλ��ʹ���ֺ�ǰ����ַ����ֿ�
    INT 10H
    POP CX ; �ָ�Сд��ĸ������

BB1:
    CMP CX, 100 ; �ȽϽ���� 100
    JC CC1 ; ������С�� 100������ת����ǩ CC1
    MOV DL, 30H ; ��ʮ�������� 0 �� ASCII ֵ���ص� DL �Ĵ���
    INC DL ; ��λ����
    SUB CX, 100 ; ģ 100����ȡʮλ�����µ���ֵ
    CMP CX, 100 ; ����Ƿ���Ȼ���ڵ��� 100
    JNC BB1 ; �����Ȼ���ڵ��� 100�������ѭ��
    MOV AH, 2 ; ���� AH �Ĵ�����ֵΪ 2����ʾҪ��ӡ�ַ�
    INT 21H ; ���� DOS �ж� 21H����ʾ��λ��ֵ
CC1: 
    CMP CX, 10 ; �ȽϽ���� 10
    JC CC2 ; ������С�� 10������ת����ǩ CC2
    MOV DL, 30H ; ��ʮ�������� 0 �� ASCII ֵ���ص� DL �Ĵ���
BB2: 
    INC DL ; ʮλ����
    SUB CX, 10 ; ģ 10����ȡ��λ��ֵ
    CMP CX, 10 ; ����Ƿ���Ȼ���ڵ��� 10
    JNC BB2 ; �����Ȼ���ڵ��� 10�������ѭ��
    MOV AH, 2 ; ���� AH �Ĵ�����ֵΪ 2����ʾҪ��ӡ�ַ�
    INT 21H ; ���� DOS �ж� 21H����ʾʮλ��ֵ
CC2: 
    MOV DL, CL ; �� CX ��ֵ������λ��ֵ�����ص� DL �Ĵ���
    ADD DL, 30H ; ����λ��ֵת��Ϊ ASCII �ַ�
    MOV AH, 2 ; ���� AH �Ĵ�����ֵΪ 2����ʾҪ��ӡ�ַ�
    INT 21H ; ���� DOS �ж� 21H����ʾ��λ��ֵ

AA4:
    MOV AH, 4CH ; �������������DOS
    INT 21H
CODE ENDS

END MAIN ; �������
