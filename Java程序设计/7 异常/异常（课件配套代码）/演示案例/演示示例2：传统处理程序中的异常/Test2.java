package cn.myApp.exception;

import java.util.Scanner;

/**
 * ��ͳ��������е��쳣��
 * @author administrator
 */
public class Test2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("�����뱻����:");
		int num1 = in.nextInt();
		System.out.print("���������:");
		int num2 = 0;
		if (in.hasNextInt()) { // �������ĳ���������
			num2 = in.nextInt();
			if (0 == num2) { // �������ĳ�����0
				System.err.println("����ĳ�����0�������˳���");
				System.exit(1);
			}
			System.out.println(num1+"/"+ num2 +"="+ num1/ num2);
			System.out.println("��лʹ�ñ�����");
		} else { // �������ĳ�����������
			System.err.println("����ĳ������������������˳���");
			System.exit(1);
		}
		
	}
}
