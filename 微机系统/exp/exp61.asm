DATA SEGMENT
ARRAY1 DW 5 DUP(212FH,5A5DH,1234H,7A65H)) ;����������
EOD DW 0 ;������ǣ����ڼ����������
DATA ENDS

CODE SEGMENT
ASSUME CS:CODE,DS:DATA

MAIN PROC FAR ;����������ΪԶ�̵���
    MOV AX,DATA
    MOV DS,AX ;��DS�Ĵ�������Ϊ���ݶεĶε�ַ
    LEA SI,ARRAY1 ;���������ʼ��ַ���ص�SI�Ĵ���
    MOV CX,(EOD-ARRAY1)/2 
    ;����������Ԫ���ܸ�����/2����Ϊ����ÿ��Ԫ��ռ�����ֽ�
    CALL SUB3 ;���ò����ӳ��� SUB3�����������е����Ԫ�ؼ���λ��
        ;���Ԫ�ض��ڵ�ַͨ��SI����
        ;���Ԫ�ص�ֵͨ��CX����
    MOV DI,CX ;���Ԫ�����ڵĶε�ַ������DI�Ĵ�����
    MOV BX,DATA
    CALL SUB4 ;������ʾ�ӳ�����ʾ���Ԫ�صĶε�ַ
    MOV DL,':'
    MOV AH,2
    INT 21H ;��ʾð���ַ�
    MOV BX,SI ;���Ԫ�صĶ���ƫ�Ʊ�����BX�Ĵ�����
    CALL SUB4 ;������ʾ�ӳ�����ʾ���Ԫ�صĶ���ƫ��
    MOV DL,20H ;��ʾ�ո�
    MOV AH,2
    INT 21H
    MOV BX,DI ;���������е����Ԫ�ص�ֵ������BX�Ĵ�����
    CALL SUB4 ;��ʾ���ҵ������ֵ
    MOV AH,4CH
    INT 21H ;�������������DOS

SUB3 PROC NEAR ;�����ӳ��򣬲������������Ԫ�ؼ���λ��
    PUSH AX ;��ؼĴ�������
    PUSH DI
    MOV DI,SI ;DI�б������ֵ�Ķ��ڵ�ַ���ȷ���ʼ��ַ
    MOV AX,[SI] ;AX�б������ֵ���ȷ���ʼֵ
AA1: CMP AX,[SI] ;�Ƚϵ�ǰԪ�������ֵ
    JNC AA2 ;�����ǰԪ�ش��ڵ������ֵ����ת��AA2
    MOV AX,[SI] ;�������ֵ
    MOV DI,SI ;�������ֵ�Ķ��ڵ�ַ
AA2: ADD SI,2 ;�ƶ�����һ��Ԫ��
    LOOP AA1 ;ѭ��ֱ������������Ԫ��
    MOV SI,DI ;���Ԫ�ض��ڵ�ַͨ��SI����
    MOV CX,AX ;���Ԫ�ص�ֵͨ��CX����
    POP DI ;�ָ������ļĴ���
    POP AX
    RET ;���ص����ӳ����λ��
SUB3 ENDP

SUB4 PROC NEAR ;ʮ����������ʾ�ӳ���
    MOV DL,BH ;����BX�и�8λ
    MOV CL,4
    ;�����Ȱ�BH�ĵ�4���ֽ�����ȥ������λ4���ֽ�����ʾ
    SHR DL,CL ;����8λ����4λ����ȡ��λ��ʮ��������
    CALL SUB5 ;����ʮ����������ʾ�ӳ���
    MOV DL,BH
    ;��BX��8λ�еĸ�λ����Ϊ0����ȡ��λ��ʮ��������
    AND DL,0FH 
    CALL SUB5 ;����ʮ����������ʾ�ӳ���
    ;���洦��BX�ĵ�8λ�����Ʋ���
    MOV DL,BL ;����BX�е�8λ
    SHR DL,CL
    CALL SUB5
    MOV DL,BL
    AND DL,0FH
    CALL SUB5
    RET
SUB4 ENDP

SUB5 PROC NEAR ;ʮ����������ʾ�ӳ���
    OR DL,30H ;��DL�е���ֵת��ΪASCII���ʾ���ַ�
    CMP DL,3AH ;�Ƚ�DL��ASCII���ʾ���ַ�':'
    JC AA3 ;���С��':'����ת��AA3
    ;�������ڴ�������>9�����
    ADD DL,7 ;����DL�е���ֵ����7���õ���д��ĸ��ASCII��
AA3: MOV AH,2
    INT 21H ;��ʾDL�е��ַ�
    RET ;���ص����ӳ����λ��
SUB5 ENDP

CODE ENDS

END MAIN ;�������
