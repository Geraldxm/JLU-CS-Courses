WR      MACRO CHA,ATR,NUM ;�궨��,��ʾ����
        MOV AH,2 ;���ù��λ��
        INT 10H
        MOV AL,CHA ;Ҫ��ʾ���ַ��� AL
        MOV CX,NUM ;Ҫ��ʾ�ַ��ĸ����� CX
        MOV BL,ATR ;Ҫ��ʾ�ַ�����ɫ����
        MOV AH,9
        INT 10H
        ENDM
CODE SEGMENT
    ASSUME CS:CODE
MAIN PROC FAR
CAR:    MOV AH,0
        MOV AL,3 ;������Ļ��ʾģʽΪ 80*25 ��ɫ�ַ���ʽ
        INT 10H
LOP1:   MOV SI,0A0AH ;������ʾλ��(10,10)
LOP2:   MOV DX,SI
        WR 0DBH,0CH,5 ;��ʾ��һ��
        INC DH ;����������
        SUB DL,2 ;����������
        WR 0DBH,4,9 ;��ʾ�ڶ���
        INC DH ;����������
        INC DL ;����������
        WR 09H,8EH,1 ;��ʾǰ��
        ADD DL,6 ;����������
        WR 9,8EH,1 ;��ʾ����
        CALL DELAY ;������ʱ
LOP3:
        MOV DX,SI
        WR 0,0,5 ;�����һ��
        INC DH
        SUB DL,2
        WR 0,0,9 ;����ڶ���
        INC DH
        WR 0,0,8 ;���������
        MOV AH,1 ;�ж��Ƿ��а���
        INT 16H
        JZ CONU
        MOV AH,4CH ;���� DOS
        INT 21H
JLOP2:  JMP LOP2
CONU:   INC SI
        CMP SI,0A50H ;�жϳ��Ƿ��ܵ�ͷ
        JB JLOP2
        JMP LOP1
MAIN ENDP
DELAY   PROC NEAR ;��ʱ�ӳ���
        MOV CX,000FH
        LOOP $
        RET
DELAY ENDP
CODE ENDS
END MAIN