package cn.myApp.exception;

import java.io.*;

/**
 * ����Checked�쳣��
 * @author administrator
 */
public class Test10 {
	public static void main(String[] args) {
		FileInputStream fis = null;
		try {
			// ����ָ���ļ�������
			fis = new FileInputStream(new File("accp.txt"));
		} catch (FileNotFoundException e) {
			System.err.println("�޷��ҵ�ָ���ļ���");
			e.printStackTrace();
		}
		try {
			// �ر�ָ���ļ�������
			fis.close();
		} catch (IOException e) {
			System.err.println("�ر�ָ���ļ�������ʱ�����쳣��");
			e.printStackTrace();
		}
	}
}

