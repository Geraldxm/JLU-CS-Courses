DSEG SEGMENT PARA PUBLIC'DSEG' ;����ɶ�д����ִ�й����Ķ�"DSEG"
M	DB	80H,81H,82H,83H,84H,90H,91H,92H,93H,94H,70H,71H,72H,73H,74H,40H,41H,42H,43H,44H;����M��ÿ��Ԫ��1�ֽ�
P	DB	20 DUP(?) ;����P������20
N	DB	20 DUP(?) ;����N������20
DSEG ENDS

CODE SEGMENT
ASSUME CS:CODE,DS:DSEG

START:	MOV AX,DSEG
		MOV DS,AX
		LEA SI,P 	;���������׵�ַ
		LEA DI,N 	;���������׵�ַ
		LEA BX,M 	;���������׵�ַ
		XOR AX,AX
		XOR DX,DX	;����
		MOV CX,20	;����ѭ������
L1:		MOV AL,[BX] ;��������ȡ��һ����
		TEST AL,80H ;AL�Ĵ��������λ��1���������
		JZ L2 		;���Ϊ0��Ҳ����AL���λ��Ϊ1��˵��ALΪ��������תL2
		MOV [DI],AL ;����ALΪ���������븺��
		INC BX
		INC DI
		INC DH 		;BX++,DI++,DH++
		JMP L3
L2:		MOV [SI],AL ;��������
		INC BX		;BX++��SI++��DL++
		INC SI
		INC DL 		;��������+1
L3:		LOOP L1		;ѭ��ִ��L1
		MOV CX,2	;DH����Ǹ���������DL�������������
L5:		MOV BL,DL 	;DL�����ݣ�Ҳ���������������ȸ��Ƶ�BL��Ȼ��ÿ4λ���
		SHR DL,1
		SHR DL,1
		SHR DL,1
		SHR DL,1	;SHRָ�DL�Ĵ�������4λ���൱�ڳ���16�����ڻ�ȡDL�ĸ�4λ��
		AND DL,0FH	;ANDָ����0FH��������������ڱ���DL�ĵ�4λ��
		;CMP DL,10	;CMPָ�����ڽ�DL��10���бȽϣ����ж��Ƿ����9
		;ע�⣡�������10������10H��
		SUB DL,10

		;���DL���ڵ���10�������ֳ���9����Ҫ����Ϊ��ȷ��ASCII�ַ�ֵ��
		;ADDָ�DL����7������Ϊ��Ӧ�Ĵ�д��ĸ��ASCIIֵ
		;�磬10��Ϊ'A'��11��Ϊ'B'

		JB L4		;JB(С������ת)��δ����9����תL4
		ADD DL,7 	;������������ִ���9����7���������
L4:		ADD DL,30H 	;��DL����30H������ת��ΪASCII��

		ADD DL,10
		;��DL����30H����48����Ŀ���ǽ�DL�д洢������ת��Ϊ��Ӧ��ASCII�ַ�

		MOV AH,2
		INT 21H		;Ȼ��ͨ��INT 21H����DOS����ʾ�ַ����ܣ����DL�е��ַ���
		MOV DL,BL	;�Ըոձ����ĵ���λ��BL�У�����ͬ�Ĳ��������
		AND DL,0FH
		CMP DL,10
		JB L6
		ADD DL,7
L6:		ADD DL,30H
		MOV AH,2
		INT 21H
		MOV DL,0AH ;����
		MOV AH,2
		INT 21H
		MOV DL,0FH ;�س�
		MOV AH,2
		INT 21H
		MOV DL,DH ;�������������
		LOOP L5
		MOV AH,4CH
		INT 21H

CODE ENDS
END START
		