package cn.myApp.thread2;

public class Test {
	public static void main(String[] args) {
		MyThread thread = new MyThread();
		MyThread thread2 = new MyThread(); //�������߳�thread2
		thread.start();
		thread2.start(); //����thread2
	}
}

