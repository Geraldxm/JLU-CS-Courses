package cn.myApp.thread.ex1;

public class Test {
	public static void main(String[] args) {
        //�����̶߳���
		Mythread thread1 = new Mythread();		
		Mythread thread2 = new Mythread();
		//�����߳�
		thread2.start();
                thread1.start();
	}
}
//ʹ�ü̳�Thread��ķ�ʽ�����߳�
class Mythread extends Thread{
	public void run(){
		for(int i=1;i<=20;i++){
			System.out.println(i+".��ã������߳�"+Thread.currentThread().getName());
		}
	}
}
