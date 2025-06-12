DATA SEGMENT
    MATRIX1 DB 01H,02H,03H,04H
            DB 04H,03H,02H,01H
            DB 02H,04H,06H,08H
            DB 08H,06H,04H,02H
    MATRIX2 DB 01H,01H,01H,01H
    UNIT    DW 4 DUP(0)
DATA ENDS
CODE SEGMENT
    ASSUME CS:CODE, DS:DATA

START:
    MOV AX,DATA
    MOV DS,AX   ;��DS����ΪDATA
    LEA SI,MATRIX1
    LEA DI,MATRIX2
    LEA BX,UNIT
    MOV CL,4H   ;CL���ƴ�ѭ��
AA0:
    SUB AX,AX   ;AX����
    MOV CH,4H   ;CH����Сѭ��
AA1:
    MOV DX,AX
    MOV AL,[DI] ;��m2����ȡ��AL
    MUL BYTE PTR[SI]    
    ;MUL�˷���8λʱ��һ����Ĭ����AL��16λʱ��һ����Ĭ����AX
    ;8λ�˷����������AX��16λ�������λ��DX����λ��AX��
    ;�ʴ˴�8λ�˷��������AX
    ADD AX,DX   ;DX����м�����AX����ܽ��
    INC SI  ;matric1ָ����һ��
    INC DI  ;matric2ָ����һ��
    DEC CH  
    JNZ AA1 ;����Сѭ��   
    LEA DI,MATRIX2  ;��DI����ָ��ͷ
    MOV [BX],AX ;�Ѻͷŵ�[BX] 
    INC BX  ;UNITָ����һ��
    INC BX  ;DW���ü�����
    DEC CL
    JNZ AA0
    MOV AH,4CH
    INT 21H
CODE ENDS
END START
