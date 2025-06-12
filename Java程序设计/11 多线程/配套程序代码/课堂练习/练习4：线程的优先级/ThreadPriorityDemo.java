package cn.myApp.priority2;

public class ThreadPriorityDemo {
	public static void main(String[] args) {
		Thread mainThread = Thread.currentThread();  //��ȡ���߳�
		Thread myThread = new Thread(new MyThread());  //�������߳�
		System.out.println("*******��ʾĬ�����ȼ�*******");		
		System.out.println("���߳�����"+mainThread.getName()+",���ȼ���"+mainThread.getPriority());				
		System.out.println("���߳�����"+myThread.getName()+",���ȼ���"+myThread.getPriority());
		System.out.println("*******�޸�Ĭ�����ȼ���*******");
		mainThread.setPriority(Thread.MAX_PRIORITY);  //�޸����߳�Ĭ�����ȼ�
		myThread.setPriority(Thread.MIN_PRIORITY);  //�޸����߳�Ĭ�����ȼ�
	    System.out.println("���߳�����"+mainThread.getName()+",���ȼ���"+mainThread.getPriority());
		System.out.println("���߳�����"+myThread.getName()+",���ȼ���"+myThread.getPriority());
	}
}

class MyThread implements Runnable {
	public void run() {
	}
}
