package cn.myApp.exception;

import java.io.*;
/**
 * ������Checked�쳣��
 * @author administrator
 */
public class Test9 {
	public static void main(String[] args) {
		FileInputStream fis = null;
		// ����ָ���ļ�������
		fis = new FileInputStream(new File("accp.txt"));
		// ����ָ���ļ�������
		fis.close();
	}
}
