package cn.myApp.exception;

import java.util.Scanner;

/**
 * ����try-catch-finally��ʹ�ã����ݱ������γ�����
 * @author administrator
 */
public class TestException1 {
	public static void main(String[] args) {
		System.out.print("������γ̴���(1��3֮�������):");
		Scanner in = new Scanner(System.in);
		try {
			int courseCode = in.nextInt();
			switch (courseCode) {
			case 1:
				System.out.println("C#���");
				break;
			case 2:
				System.out.println("Java���");
				break;
			case 3:
				System.out.println("SQL����");
			}
		} catch (Exception ex) {
			System.out.println("���벻Ϊ����!");
			ex.printStackTrace();
		} finally {
			System.out.println("��ӭ�������!");
		}
	}
}
