package cn.myApp.ex5;

public class RunThread implements Runnable {
	private int meters = 1000; //共1000米
	public  void run() {
		while (true) {
			synchronized (this) {			
			if(meters<=100){
				break;
			}
			System.out.println(Thread.currentThread().getName()+"拿到接力棒！");
			for (int i = 0; i < 100; i += 10) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {					
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "跑了" + (i + 10)
						+ "米！");
			}
			meters-=100;
			}
		}
	}
}
