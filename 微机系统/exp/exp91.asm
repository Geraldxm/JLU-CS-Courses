DATA SEGMENT
    STRING DB 'ERROR!DIVIDE BY ZERO!',0AH,0DH,'$'
DATA ENDS

CODE SEGMENT
    ASSUME CS:CODE, DS:DATA

MAIN PROC FAR
    LEA DX, INT0 ;�жϷ������ƫ�Ƶ�ַ
    MOV AX, CS
    MOV DS, AX
    MOV AL, 32 ;ѡ��32�����ж�
    MOV AH, 25H ;���������ж������ж�
    INT 21H
    MOV AX, DATA
    MOV DS, AX
    MOV CX, 10 ;9/BL��10��
    MOV BL, 6 ;BL��ֵ��Ϊ6.
A1:
    MOV AX, 9
    DIV BL
    ADD AL, 30H ;��ʮ������ת����ʮ��������
    MOV DL, AL ;��ʾ�������
    MOV AH, 2
    INT 21H ;��ʾ�ַ�
    MOV DL, 0DH
    MOV AH, 2
    INT 21H
    MOV DL, 0AH
    MOV AH, 2
    INT 21H
    CMP CX,0
    JZ A3 
    DEC BL
    CMP BL, 0   ;������Ϊ0ʱ
    JZ A2
    LOOP A1
A3: 
    MOV AH,4CH
    INT 21H
A2:
    INT 32
    SUB CX,1
    ;MOV AH, 4CH
    ;INT 21
    JMP A1
MAIN ENDP

INT0 PROC FAR ;�жϷ������
    LEA DX, STRING
    MOV AH, 9 ;�����ַ�������ж�
    INT 21H
    MOV BL, 3 ;BL��Ϊ3
    IRET ;����������
INT0 ENDP
CODE ENDS
    END MAIN

CODE ENDS

END MAIN
