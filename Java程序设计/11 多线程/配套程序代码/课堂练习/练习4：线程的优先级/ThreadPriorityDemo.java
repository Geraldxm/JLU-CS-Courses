package cn.myApp.priority2;

public class ThreadPriorityDemo {
	public static void main(String[] args) {
		Thread mainThread = Thread.currentThread();  //获取主线程
		Thread myThread = new Thread(new MyThread());  //创建子线程
		System.out.println("*******显示默认优先级*******");		
		System.out.println("主线程名："+mainThread.getName()+",优先级："+mainThread.getPriority());				
		System.out.println("子线程名："+myThread.getName()+",优先级："+myThread.getPriority());
		System.out.println("*******修改默认优先级后*******");
		mainThread.setPriority(Thread.MAX_PRIORITY);  //修改主线程默认优先级
		myThread.setPriority(Thread.MIN_PRIORITY);  //修改子线程默认优先级
	    System.out.println("主线程名："+mainThread.getName()+",优先级："+mainThread.getPriority());
		System.out.println("子线程名："+myThread.getName()+",优先级："+myThread.getPriority());
	}
}

class MyThread implements Runnable {
	public void run() {
	}
}
