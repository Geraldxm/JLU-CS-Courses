package cn.myApp.ex4;
/**
 * �β�
 *
 */
public class CureThread implements Runnable {
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("����ţ�"+(i+1)+"�Ų����ڿ�����");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
