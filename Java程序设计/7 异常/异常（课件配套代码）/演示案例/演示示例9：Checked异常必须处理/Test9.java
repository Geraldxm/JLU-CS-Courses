package cn.myApp.exception;

import java.io.*;
/**
 * 不处理Checked异常。
 * @author administrator
 */
public class Test9 {
	public static void main(String[] args) {
		FileInputStream fis = null;
		// 创建指定文件的流。
		fis = new FileInputStream(new File("accp.txt"));
		// 创建指定文件的流。
		fis.close();
	}
}
