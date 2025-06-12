package cn.myApp.exception;

import java.util.Scanner;

/**
 * 传统处理程序中的异常。
 * @author administrator
 */
public class Test2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("请输入被除数:");
		int num1 = in.nextInt();
		System.out.print("请输入除数:");
		int num2 = 0;
		if (in.hasNextInt()) { // 如果输入的除数是整数
			num2 = in.nextInt();
			if (0 == num2) { // 如果输入的除数是0
				System.err.println("输入的除数是0，程序退出。");
				System.exit(1);
			}
			System.out.println(num1+"/"+ num2 +"="+ num1/ num2);
			System.out.println("感谢使用本程序！");
		} else { // 如果输入的除数不是整数
			System.err.println("输入的除数不是整数，程序退出。");
			System.exit(1);
		}
		
	}
}
