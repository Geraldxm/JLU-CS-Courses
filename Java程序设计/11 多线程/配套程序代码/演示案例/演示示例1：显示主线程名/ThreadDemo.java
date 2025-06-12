package cn.myApp.thread;

public class ThreadDemo {
	public static void main(String args[]) {
		Thread t= Thread.currentThread(); 
		System.out.println("当前线程是: "+t.getName()); 
		t.setName("MyJavaThread"); 
		System.out.println("当前线程名是: "+t.getName()); 
	}
}
