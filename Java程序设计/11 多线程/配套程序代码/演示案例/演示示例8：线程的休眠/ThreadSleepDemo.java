package cn.myApp.sleep;
/**
 * �߳�����
 */
public class ThreadSleepDemo {
	public static void main(String[] args) {
		System.out.println("Wait");
		Wait.bySec(5); // �����̵߳ȴ�5������ִ��
		System.out.println("start");
	}
}

class Wait {
	public static void bySec(long s) {
		for (int i = 0; i < s; i++) {
			System.out.println(i + 1 + "��");
			try {
				Thread.sleep(1000); // ˯��1��
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}
}
