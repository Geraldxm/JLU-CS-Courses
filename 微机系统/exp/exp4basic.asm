DATA SEGMENT
COURSE1 DB 70H,88H,92H,90H,99H          ; ��һ�ſγɼ�
        DB 67H,77H,88H,76H,69H          ; �ڶ��ſγɼ�
        DB 74H,87H,77H,74H,70H          ; �����ſγɼ�
        DB 99H,97H,94H,98H,96H          ; �����ſγɼ�
NUM1 DW 5 DUP(0)                        ; �洢�ֵܷ�����
DATA ENDS

CODE SEGMENT
    ASSUME CS:CODE, DS:DATA
START:
    MOV AX, DATA
    MOV DS, AX
    LEA SI, COURSE1
    LEA DI, NUM1
    SUB SI, 5                       ; COURSE1��ַ��5�Ա�ѭ��
    MOV CL, 5                       ; ���ѭ��������ֵ

AAl:
    MOV BX, SI                      ; �γ�ĳ��ѧ����һ�ſγɼ�����ַ��5
    SUB AX, AX                      ; ��ͼĴ���
    MOV CH, 4                       ; ��ѭ��������ֵ

AA2:
    ADD BX, 5                       ; �γ�ĳ��ѧ��1��2...4�ſγɼ���ַ
    ADD AL, [BX]                    ; ��BCD�����
    DAA                             ; ѹ��BCD��ӵ���
    ADC AH, 0                       ; �ۼӽ�λ
    DEC CH                          ; ��ѭ����������
    JNZ AA2

    MOV [DI], AX                    ; ���ܷ�
    INC SI                          ; �γ��¸�ѧ����һ�ſγɼ�����ַ��5
    ADD DI, 2                       ; �γ��¸�ѧ�����ֵܷ�ַ
    DEC CL                          ; ��ѭ����������
    JNZ AAl

    MOV AH, 4CH
    INT 21H
CODE ENDS

END START
