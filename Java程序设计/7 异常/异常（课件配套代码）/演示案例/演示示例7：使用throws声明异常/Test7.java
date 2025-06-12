package cn.myApp.exception;

import java.util.Scanner;
/**
 * 使用throws声明异常。
 * @author administrator
 */
public class Test7 {
    /**
     * 通过try-catch捕获并处理异常。
     * @param args
     */
    public static void main(String[] args) {
        try {
            divide();
        } catch (Exception e) {
            System.err.println("出现错误:被除数和除数必须是整数,除数不能为零");
            e.printStackTrace();
        }
    }
//  /**
//   * 通过throws继续声明异常。
//   */
//  public static void main(String[] args) throws Exception {
//      divide();
//  }

    /**
     * 输入被除数和除数,计算商并输出。
     * @throws Exception
     */
    public static void divide() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("请输入被除数:");
        int num1 = in.nextInt();
        System.out.print("请输入除数:");
        int num2 = in.nextInt();
        System.out.println(num1+"/"+ num2 +"="+ num1/ num2);
    }
}

