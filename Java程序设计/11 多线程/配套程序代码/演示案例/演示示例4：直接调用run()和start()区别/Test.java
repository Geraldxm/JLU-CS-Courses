package cn.myApp.thread2;

public class Test {
	public static void main(String[] args) {
		MyThread thread = new MyThread();
		MyThread thread2 = new MyThread(); 
		//直接调用run()方法
		thread.run();
		thread2.run(); 
	}
}

