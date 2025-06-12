package cn.myApp.thread.ex1;

public class Test {
	public static void main(String[] args) {
        //创建线程对象
		Mythread thread1 = new Mythread();		
		Mythread thread2 = new Mythread();
		//启动线程
		thread2.start();
                thread1.start();
	}
}
//使用继承Thread类的方式创建线程
class Mythread extends Thread{
	public void run(){
		for(int i=1;i<=20;i++){
			System.out.println(i+".你好，来自线程"+Thread.currentThread().getName());
		}
	}
}
