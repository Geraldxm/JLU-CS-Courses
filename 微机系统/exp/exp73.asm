DATA SEGMENT
STRING1 DB 'H' ;��Ŵ�ƥ���ַ���
STRING2 DB 'ABCDEFG'
YES DB 'YES$'
NO DB 'NO$'
DATA ENDS
CODE SEGMENT
ASSUME CS:CODE,DS:DATA
MAIN PROC FAR
    MOV AX,DATA        ; �����ݶε��׵�ַװ��AX�Ĵ���
    MOV DS,AX          ; ��DS�Ĵ�������Ϊ���ݶεĵ�ַ
    MOV CX,YES-STRING2  ; �����ַ���2���ȣ�����BX�Ĵ���
    LEA BX,STRING1     ; ��STRING1�ĵ�ַװ��BX�Ĵ���
    LEA SI,STRING2     ; ��STRING2�ĵ�ַװ��SI�Ĵ���
    MOV AL,[BX]
AA1:               
    MOV AH,[SI]
    CMP AL,AH          ; �Ƚ��ַ���1���ַ���2��Ӧλ�õ��ַ�
    JZ AA3
    JNZ AA2            ; �������ȣ���ת�����AA2�������"NO"             
    INC SI             
    LOOP AA1           ; ����ѭ����ֱ��CX�Ĵ�����ֵΪ0�����ַ����ȽϽ���
AA2:               ; �ַ������Ȳ���Ȼ��ַ��Ƚ�ʧ��ʱ�ı��
    MOV AH,9           ; ����AH�Ĵ�����ֵΪ9����ʾ��ӡ�ַ���
    LEA DX,NO          ; ��NO�ĵ�ַװ��DX�Ĵ�����׼����ӡ"No"
    INT 21H            ; ����21H�жϣ�ִ�д�ӡ����
    MOV AH,4CH         ; ����AH�Ĵ�����ֵΪ4CH����ʾ������ֹ
    INT 21H            ; ����21H�жϣ�����������ֹ
AA3:
    MOV AH,9           ; INT21H��AH=9��ӡ�ַ���
    LEA DX,YES         ; ��YES�ĵ�ַװ��DX�Ĵ�����׼����ӡ"Yes"
    INT 21H            ; ����21H�жϣ�ִ�д�ӡ����
    MOV AH,4CH         ; ����AH�Ĵ�����ֵΪ4CH����ʾ������ֹ
    INT 21H            ; ����21H�жϣ�����������ֹ
MAIN ENDP
CODE ENDS
END MAIN
