DATA SEGMENT
    NUMBER1 DB 8 DUP(0) ; �����������б�
    NUMBER2 DB 8 DUP(0) ; ���������б�
    NUMBER3 DB 9 DUP(0) ; �Ͳ����б�
DATA ENDS

CODE SEGMENT
ASSUME CS:CODE, DS:DATA
MAIN PROC FAR ; ��������ΪFAR����
    MOV AX, DATA
    MOV DS, AX
    CALL SUB1 ; ����SUB1��ѹ��BCD������ӳ���
    POP CX ; ȡ������λ����Ҳ���ڿ���ѭ��
    LEA BX, NUMBER1 ; ȡ�������������ַ
AA3:
    POP AX ; ȡ��������λ��ʮλ����λ...
    MOV [BX], AL ; ���������������
    INC BX ; �γ���λ��ַ
    LOOP AA3
    MOV AH, 3 ; ���ҵ�ǰ���λ��
    ;���ò���BH=ҳ�ţ�����CH=��꿪ʼ�У�CL=��������
    ;DH=�У�DL=��
    INT 10H
    MOV AH, 2 ; ���ù��λ��
    ;���ù��λ�ã�BH=ҳ�ţ�DH=�У�DL=��
    MOV DL, 8   ;�ڵ�8�����һ��+��                              
    INT 10H
    MOV DL, 2BH ; ���+��
    MOV AH, 2
    INT 21H
    CALL SUB1 ; ����SUB1��ѹ��BCD������ӳ���
    POP CX ; ȡ����λ��
    LEA BX, NUMBER2 ; ȡ�����������ַ
AA4:
    POP AX ; ȡ������λ��ʮλ����λ..
    MOV [BX], AL ; �������������
    INC BX ; �γ���λ��ַ
    LOOP AA4
    MOV AH, 3 ; ���ҵ�ǰ���λ��
    INT 10H
    MOV AH, 2 ; ���ù��λ��
    MOV DL, 17
    INT 10H
    MOV DL, 3DH ; ���=��
    MOV AH, 2
    INT 21H
    LEA SI, NUMBER1 ; ȡ�������������ַ
    LEA DI, NUMBER2 ; ȡ�����������ַ
    LEA BX, NUMBER3 ; ȡ�Ͳ������ַ
    SUB CX, CX ; 0 �� CF
    MOV CX, 8 ; ��λ����������ֵ
AA5:
    MOV AL, [SI] ; ȡ������
    ADC AL, [DI] ; ��ѹ��BCD���
    AAA
    MOV [BX], AX ; ���ʹ浽NUMBER3��
    INC DI ; �γ���һ��ַ
    INC SI ; �γ���һ��ַ
    INC BX ; �γ���һ��ַ
    LOOP AA5
    ADC CL, CL ; ���λ��CL
    MOV [BX], CL ; �����λ
    LEA AX, NUMBER3+8 ; ȡ�Ͳ��������λ��ַ
    PUSH AX ; ���ӳ����ṩ�Ͳ��������λ��ַ
    CALL SUB2 ; ��ѹ��BCD����ʾ�ӳ���
    MOV CX, 16
    LEA BX, NUMBER1 ; ȡ�������������ַ
    XOR AL, AL
QQQ2:
    MOV [BX], AL
    INC BX
    LOOP QQQ2
    MOV AH, 4CH ; ����DOS
    INT 21H
MAIN ENDP 

SUB1 PROC NEAR ; ��ѹ��BCD�����������ӳ���
    POP BX ; ���淵�ص�ַ
    SUB CX, CX ; ����λ����������0
AA1:
    MOV AH, 1
    INT 21H
    CMP AL, 30H ; ��С��0����������
    JC AA2  ;��λ��ת
    CMP AL, 3AH ; �д���9����������
    ;����CF=0�Ǵ��ڵ��ڵ������������3AH������39H
    ;Ҳ���ǡ�10
    JNC AA2
    INC CX ; ��������λ��
    PUSH AX ; ��ѹ��BCD��ѹջ
    JMP AA1
AA2:
    PUSH CX ; ��������λ��ѹջ
    PUSH BX ; ���ص�ַѹջ
    RET ; ����������
SUB1 ENDP

SUB2 PROC NEAR ; ��ѹ��BCD����ʾ�ӳ���
    POP AX ; ���淵�ص�ַ
    POP BX ; ȡ�Ͳ��������λ��ַ
    PUSH AX ; ���ص�ַѹջ
    MOV CX, 9
AA7:
    MOV AL, [BX] ; ����ͷ������Ч����0
    CMP AL, 0
    JNZ AA6
    DEC CX
    DEC BX
    JMP AA7
AA6:
    MOV DL, [BX] ; ȡ�����λ���θ�λ..��λ
    ADD DL, 30H ; �γ�ASCII��
    MOV AH, 2
    INT 21H
    DEC BX
    LOOP AA6
    RET
SUB2 ENDP

CODE ENDS
END MAIN
