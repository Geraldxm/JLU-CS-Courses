package cn.myApp.thread.state;
/**
 * �����̵߳�״̬
 */
public class Mythread implements Runnable{
	public void run() {
		try {
			System.out.println("�߳�t�����У�");
			Thread.sleep(500);
			System.out.println("�߳�t�ڶ�ʱ��˯�ߺ��������У�");
		} catch (InterruptedException IE) {
			System.out.println("�̱߳��ж�");
		}
	}
	public static void main(String args[]) {
		Thread t=new Thread(new Mythread());
		 System.out.println ("�߳� t Ϊ�½���");		
		 t.start();
		 System.out.println ("�߳� t Ϊ������");
	}
}
