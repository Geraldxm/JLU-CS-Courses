package cn.myApp.thread3;

class MyRunnable implements Runnable{
	//实现run()方法
	public void run(){
		for(int i=1;i<100;i++){	
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}
}


