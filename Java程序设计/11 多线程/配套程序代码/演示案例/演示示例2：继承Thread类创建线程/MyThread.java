package cn.myApp.thread2;

public class MyThread extends Thread{
    //��дrun()����
	public void run(){
		for(int i=1;i<100;i++){
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}
}

