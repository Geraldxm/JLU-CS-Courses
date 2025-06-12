package cn.myApp.thread.ex2;

public class Test {
	public static void main(String[] args) {
        //创建线程对象
		Thread thread1 = new Thread(new Mythread());		
		Thread thread2 = new Thread(new Mythread());
		//启动线程
		thread2.start();
        thread1.start();
	}
}
//使用实现Runnable接口的方式创建线程
class Mythread implements Runnable{
	public void run(){
		for(int i=1;i<=20;i++){
			System.out.println(i+".你好，来自线程"+Thread.currentThread().getName());
		}
	}
}
