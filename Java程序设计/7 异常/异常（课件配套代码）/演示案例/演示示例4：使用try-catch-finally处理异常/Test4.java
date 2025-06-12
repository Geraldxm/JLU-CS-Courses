package cn.myApp.exception;

import java.util.Scanner;
/**
 * 使用try-catch-finally进行异常处理。
 * @author administrator
 */
public class Test4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("请输入被除数:");
        try {
            int num1 = in.nextInt();
            System.out.print("请输入除数:");
            int num2 = in.nextInt();
            System.out.println(num1+"/"+ num2 +"="+ num1/ num2);
        } catch (Exception e) {
            System.err.println("出现错误:被除数和除数必须是整数,除数不能为零。");
            //System.exit(1); // finally语句块不执行的唯一情况
        } finally {
            System.out.println("感谢使用本程序!");
        }
    }
}
