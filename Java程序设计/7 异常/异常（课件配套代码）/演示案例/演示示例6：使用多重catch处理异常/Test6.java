package cn.myApp.exception;

import java.util.Scanner;
import java.util.InputMismatchException;
/**
 * ����catch��
 * @author administrator
 */
public class Test6 {
  public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      System.out.print("�����뱻����:");
	  	try {
          int num1 = in.nextInt();
          System.out.print("���������:");
          int num2 = in.nextInt();
          System.out.println(num1+"/"+ num2 +"="+ num1/ num2);
      } catch (InputMismatchException e) {
          System.err.println("�������ͳ���������������");
      } catch (ArithmeticException e) {
          System.err.println("��������Ϊ�㡣");
      } catch (Exception e) {  //���쳣��׽��ǰ������
          System.err.println("����δ֪�쳣��");
      } finally {
          System.out.println("��лʹ�ñ�����!");
      }
   }
}
