package cn.myApp.thread.ex2;

public class Test {
	public static void main(String[] args) {
        //�����̶߳���
		Thread thread1 = new Thread(new Mythread());		
		Thread thread2 = new Thread(new Mythread());
		//�����߳�
		thread2.start();
        thread1.start();
	}
}
//ʹ��ʵ��Runnable�ӿڵķ�ʽ�����߳�
class Mythread implements Runnable{
	public void run(){
		for(int i=1;i<=20;i++){
			System.out.println(i+".��ã������߳�"+Thread.currentThread().getName());
		}
	}
}
