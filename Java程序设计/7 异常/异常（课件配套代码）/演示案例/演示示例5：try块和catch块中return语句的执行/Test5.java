package cn.myApp.exception;

import java.util.Scanner;

/**
 * 测试try块和catch块中return语句的执行。
 * @author administrator
 */
public class Test5 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        System.out.print("请输入被除数:");
        try {
            int num1 = in.nextInt();
            System.out.print("请输入除数:");
            int num2 = in.nextInt();
            System.out.println(num1+"/"+ num2 +"="+ num1/ num2);
            return; //finally语句块仍旧会执行
        } catch (Exception e) {
            System.err.println("出现错误:被除数和除数必须是整数,除数不能为零");               
            return; //finally语句块仍旧会执行
        } finally {
            System.out.println("感谢使用本程序!");
        }
	}
}
