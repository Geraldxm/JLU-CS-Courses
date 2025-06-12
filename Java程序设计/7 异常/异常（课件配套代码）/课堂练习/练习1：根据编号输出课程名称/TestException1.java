package cn.myApp.exception;

import java.util.Scanner;

/**
 * 测试try-catch-finally的使用，根据编号输出课程名称
 * @author administrator
 */
public class TestException1 {
	public static void main(String[] args) {
		System.out.print("请输入课程代号(1～3之间的数字):");
		Scanner in = new Scanner(System.in);
		try {
			int courseCode = in.nextInt();
			switch (courseCode) {
			case 1:
				System.out.println("C#编程");
				break;
			case 2:
				System.out.println("Java编程");
				break;
			case 3:
				System.out.println("SQL基础");
			}
		} catch (Exception ex) {
			System.out.println("输入不为数字!");
			ex.printStackTrace();
		} finally {
			System.out.println("欢迎提出建议!");
		}
	}
}
