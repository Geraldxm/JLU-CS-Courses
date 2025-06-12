package cn.myApp.exception;

import java.io.*;

/**
 * 处理Checked异常。
 * @author administrator
 */
public class Test10 {
	public static void main(String[] args) {
		FileInputStream fis = null;
		try {
			// 创建指定文件的流。
			fis = new FileInputStream(new File("accp.txt"));
		} catch (FileNotFoundException e) {
			System.err.println("无法找到指定文件！");
			e.printStackTrace();
		}
		try {
			// 关闭指定文件的流。
			fis.close();
		} catch (IOException e) {
			System.err.println("关闭指定文件输入流时出现异常！");
			e.printStackTrace();
		}
	}
}

