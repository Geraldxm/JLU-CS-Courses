DATA SEGMENT
    ARRAY DB 13,24,92,42,25,46,75,81,53,01
    MA DB 'MAX= $'
    MI DB 0DH,0AH,'MIN= $'
DATA ENDS

CODE SEGMENT
ASSUME CS:CODE,DS:DATA

MAIN PROC FAR; ��������ΪFAR����
    MOV AX,DATA
    MOV DS,AX
    LEA DX,MA
    MOV AH,9; ���MAX=
    INT 21H ;AH=09,����Ϊ��ʾ�ַ���
    LEA BX,ARRAY
    CALL MAX; ����MAX�ֳ�����������ֵ
    CMP DL,10; �Ƿ�Ϊ��λ��
    JC AA6  ;��λ������ת
    MOV DH,30H  ;DH����30H
AA5:
    INC DH; ʮλ����һ��31H��Ҳ����1
    SUB DL,10; ģʮ����ʱ��û����ASCII��
    CMP DL,10
    JNC AA5
    ;�����ַ�ʽ��ʮλ���ָ���DH
    PUSH DX; ������λ��
    MOV DL,DH; ��ʾʮλ��
    MOV AH,2
    INT 21H
    POP DX; �ָ���λ��
AA6:
    ADD DL,30H; ��ʾ��λ��������30H��������ʾASCII��
    MOV AH,2
    INT 21H ;AH=02H������ַ�
    ;����MIN
    LEA DX,MI; ������س�MIN=��
    MOV AH,9
    INT 21H
    LEA BX,ARRAY
    CALL MIN; ����MIN������С��ֵ
    CMP DL,10  ;�Ƿ�Ϊ��λ��
    JC AA8
    MOV DH,30H  ;������Ĳ���һ���������λ��
AA7:
    INC DH
    SUB DL,10
    CMP DL,10
    JNC AA7
    PUSH DX
    MOV DL,DH
    MOV AH,2; ���ʮλ��
    INT 21H
    POP DX
AA8:
    ADD DL,30H; �����λ��
    MOV AH,2
    INT 21H     ;AH=02,��ʾDL���
    MOV AH,4CH; ����DOS
    INT 21H
MAIN ENDP

MAX PROC NEAR; ���ֵ�����ӳ���
    MOV CX,10; ���Ҽ���
    MOV DL,[BX]; ��ʼ��DL���Ա����ð�ݲ���
AA1:
    MOV DH,[BX] ;ÿ��ȡһ���ŵ�DH����DL�Ƚ�
    CMP DL,DH
    JC AA2  ;��DLС
    INC BX; �γ���һ����λ��
    DEC CX; ���Ҽ�����һ
    CMP CX,0
    JZ OUT1
    JMP AA1
AA2:
    MOV DL,DH   ;�滻DL
    INC BX
    DEC CX
    CMP CX,0
    JZ OUT1
    JMP AA1
OUT1:
    RET; ����������
MAX ENDP

MIN PROC NEAR; ��Сֵ�����ӳ��򣬲��ҹ�����MAX��������
    MOV CX,10
    MOV DL,[BX]
AA3:
    MOV DH,[BX]
    CMP DL,DH
    JNC AA4
    INC BX
    DEC CX
    CMP CX,0
    JZ OUT2
    JMP AA3
AA4:
    MOV DL,DH
    INC BX
    DEC CX
    CMP CX,0
    JZ OUT1
    JMP AA3
OUT2:
    RET

MIN ENDP

CODE ENDS
END MAIN
