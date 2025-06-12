package cn.myApp.sleep;
/**
 * 线程休眠
 */
public class ThreadSleepDemo {
	public static void main(String[] args) {
		System.out.println("Wait");
		Wait.bySec(5); // 让主线程等待5秒种再执行
		System.out.println("start");
	}
}

class Wait {
	public static void bySec(long s) {
		for (int i = 0; i < s; i++) {
			System.out.println(i + 1 + "秒");
			try {
				Thread.sleep(1000); // 睡眠1秒
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}
}
