package cn.myApp.exception;

import java.util.Scanner;
/**
 * ʹ��throws�����쳣��
 * @author administrator
 */
public class Test7 {
    /**
     * ͨ��try-catch���񲢴����쳣��
     * @param args
     */
    public static void main(String[] args) {
        try {
            divide();
        } catch (Exception e) {
            System.err.println("���ִ���:�������ͳ�������������,��������Ϊ��");
            e.printStackTrace();
        }
    }
//  /**
//   * ͨ��throws���������쳣��
//   */
//  public static void main(String[] args) throws Exception {
//      divide();
//  }

    /**
     * ���뱻�����ͳ���,�����̲������
     * @throws Exception
     */
    public static void divide() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("�����뱻����:");
        int num1 = in.nextInt();
        System.out.print("���������:");
        int num2 = in.nextInt();
        System.out.println(num1+"/"+ num2 +"="+ num1/ num2);
    }
}

