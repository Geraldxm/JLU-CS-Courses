; ��������
CSEG SEGMENT
ASSUME CS:CSEG

START PROC FAR
    PUSH CS
    POP DS

    ; ��ȡԭ 1CH �ŵ��ж�����
    MOV AX, 351CH
    INT 21H
    MOV CS:WORD PTR OLD1C, BX
    MOV CS:WORD PTR OLD1C+2, ES ; ����ԭ 1CH �ŵ��ж�����

    ; �����µ� 1CH �ŵ��ж�����
    MOV DX, OFFSET INT1C
    MOV AX, 251CH
    INT 21H

    ; �˺�ÿ 55 ����ͽ���һ���µ� 1CH �ŵ��жϴ������
WAITN:
    MOV AH, 1
    INT 16H ; �����޼�����
    JZ WAITN ; ת�ȴ�������

    MOV AH, 0
    INT 16H ; ������

    LDS DX, CS:OLD1C
    MOV AX, 251CH
    INT 21H ; �ָ�ԭ 1CH �ж�����

    MOV AH, 4CH
    INT 21H ; ���� DOS
START ENDP

; �������ݿռ�
OLD1C DD ? ; ����ԭ�ж�����
COUNT DW 0 ; ���� 1CH �жϳ���Ĵ���
HHH DB ?, ?, ':' ; "ʱ"
MMM DB ?, ?, ':' ; "��"
SSS DB ?, ?, '$' ; "��"

; �����µ� 1CH �ŵ��жϴ������
INT1C PROC FAR
    CMP COUNT, 0 ; ���ô���Ϊ "0" ʱ��1 �뵽��
    JZ NEXT
    DEC COUNT ; ��ʾ�����ݼ�����һ�� 18-1��
    IRET

NEXT:
    MOV COUNT, 18 ; ����ʾ������ֵ
    STI
    PUSH DS ; �����ֳ�
    PUSH ES
    PUSH AX
    PUSH BX
    PUSH CX
    PUSH DX
    PUSH SI
    PUSH DI

    MOV AH, 2
    INT 1AH ; ��ʵʱʱ��

    MOV AL, CH ; ʱ�� AL
    CALL TTASC ; ת���� ASCII ��
    MOV WORD PTR HHH, AX ; ����ʱ

    MOV AL, CL ; ��ת��
    CALL TTASC
    MOV WORD PTR MMM, AX

    MOV AL, DH ; ��ת��
    CALL TTASC
    MOV WORD PTR SSS, AX

    CALL CLS ; ����

    MOV BH, 0
    MOV DX, 0140H   ;DH=01 DL=40
    MOV AH, 2
    INT 10H ; ���ù�꣨1��65��

    PUSH CS
    POP DS
    MOV DX, OFFSET HHH
    MOV AH, 9
    INT 21H ; ��ʾʵʱʱ��

    POP DI
    POP SI
    POP DX
    POP CX
    POP BX
    POP AX
    POP ES
    POP DS
    IRET ; �жϷ���
INT1C ENDP

; �� AL �е� BCD ����ת���� ASCII ����� AX ��
TTASC PROC
    MOV AH, AL
    AND AL, 0FH
    SHR AH, 1
    SHR AH, 1
    SHR AH, 1
    SHR AH, 1
    ADD AX, 3030H
    XCHG AH, AL
    RET
TTASC ENDP

; �����ӳ���
CLS PROC
    MOV AX, 0600H
    MOV CX, 0
    MOV DX, 184FH
    MOV BH, 7
    INT 10H
    RET
CLS ENDP

CSEG ENDS
END START
