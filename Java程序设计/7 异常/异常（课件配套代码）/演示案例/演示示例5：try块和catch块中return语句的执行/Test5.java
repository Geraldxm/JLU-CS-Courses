package cn.myApp.exception;

import java.util.Scanner;

/**
 * ����try���catch����return����ִ�С�
 * @author administrator
 */
public class Test5 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        System.out.print("�����뱻����:");
        try {
            int num1 = in.nextInt();
            System.out.print("���������:");
            int num2 = in.nextInt();
            System.out.println(num1+"/"+ num2 +"="+ num1/ num2);
            return; //finally�����Ծɻ�ִ��
        } catch (Exception e) {
            System.err.println("���ִ���:�������ͳ�������������,��������Ϊ��");               
            return; //finally�����Ծɻ�ִ��
        } finally {
            System.out.println("��лʹ�ñ�����!");
        }
	}
}
