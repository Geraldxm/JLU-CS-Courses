package cn.myApp.thread.yield;

public class ThreadYieldDemo {
	public static void main(String[] args) {
		System.out.println("*****�̵߳�����*****");
		MyThread my = new MyThread();
		Thread t1 = new Thread(my,"�߳�A");		
		Thread t2 = new Thread(my,"�߳�B");
		t1.start();
		t2.start();
	}
}
