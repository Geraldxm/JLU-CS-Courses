package cn.myApp.exception;

import java.util.Scanner;

/**
 * ʹ��try-catch�����쳣����
 * @author administrator
 */
public class Test3 {
    public static void main(String[] args) {
	    Scanner in = new Scanner(System.in);
            System.out.print("�����뱻����:");        
	    try {
            int num1 = in.nextInt();
            System.out.print("���������:");
            int num2 = in.nextInt();
            System.out.println(num1+"/"+ num2 +"="+ num1/ num2);          
        } catch (Exception e) {
            System.err.println("���ִ���:�������ͳ�������������,��������Ϊ�㡣");
            e.printStackTrace();
        }
       System.out.println("��лʹ�ñ�����!");
    }
}