package cn.myApp.exception;

import java.util.Scanner;

/**
 * ��ʾ�����е��쳣��
 * @author administrator
 */
public class Test1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("�����뱻����:");
		int num1 = in.nextInt();
		System.out.print("���������:");
		int num2 = in.nextInt();
		System.out.println(num1+"/"+ num2 +"="+ num1/ num2);
		System.out.println("��лʹ�ñ�����");
	}
}
