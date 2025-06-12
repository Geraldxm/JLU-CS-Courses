package cn.myApp.exception;

import java.util.Scanner;
import java.util.InputMismatchException;
/**
 * 多重catch块
 * @author administrator
 */
public class Test6 {
  public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      System.out.print("请输入被除数:");
	  	try {
          int num1 = in.nextInt();
          System.out.print("请输入除数:");
          int num2 = in.nextInt();
          System.out.println(num1+"/"+ num2 +"="+ num1/ num2);
      } catch (InputMismatchException e) {
          System.err.println("被除数和除数必须是整数。");
      } catch (ArithmeticException e) {
          System.err.println("除数不能为零。");
      } catch (Exception e) {  //该异常捕捉在前，报错
          System.err.println("其他未知异常。");
      } finally {
          System.out.println("感谢使用本程序!");
      }
   }
}
