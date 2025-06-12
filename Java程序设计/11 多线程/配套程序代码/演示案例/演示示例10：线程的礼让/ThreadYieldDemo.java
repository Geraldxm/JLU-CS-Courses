package cn.myApp.thread.yield;

public class ThreadYieldDemo {
	public static void main(String[] args) {
		System.out.println("*****线程的礼让*****");
		MyThread my = new MyThread();
		Thread t1 = new Thread(my,"线程A");		
		Thread t2 = new Thread(my,"线程B");
		t1.start();
		t2.start();
	}
}
