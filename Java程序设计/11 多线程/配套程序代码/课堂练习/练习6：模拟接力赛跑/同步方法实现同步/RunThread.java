package cn.myApp.ex5;
/**
 * �ܲ��߳���
 */
public class RunThread implements Runnable {
	private int meters = 1000; //����1000��
	public  void run() {
		while (true) {
			if(meters<=100){
				break;
			}
			go();
		}
	}
	//ͬ���������ܲ���ÿ����100�ף�
	public synchronized void go(){		
		System.out.println(Thread.currentThread().getName()+"�õ���������");
		for (int i = 0; i < 100; i += 10) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {					
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "����" + (i + 10)
					+ "�ף�");
		}
		meters-=100;
	
	}
}
