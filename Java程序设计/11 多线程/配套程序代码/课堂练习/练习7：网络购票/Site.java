package cn.myApp.thread2;

/**
 * ģ��������ʱ�̲߳���ȫ 
 */
public class Site implements Runnable {
	private int count = 10; // ��¼ʣ��Ʊ��
	private int num = 0; // ��¼�򵽵ڼ���Ʊ
	private boolean flag = false;  //��¼�Ƿ�����

	public void run() {
		while (true) {
			if(!sale()){
			break;
			}
		}		
	}
	// ͬ����������Ʊ
	public synchronized boolean sale() {	
		if (count <= 0) {
			return false;
		}
		// ��һ�����޸�����
		num++;
		count--;
		try {
			Thread.sleep(500); // ģ��������ʱ
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// �ڶ�������ʾ��Ϣ
		System.out.println(Thread.currentThread().getName() + "������" + num
				+ "��Ʊ��ʣ��" + count + "��Ʊ��");
       if(Thread.currentThread().getName().equals("��ţ��")){
    	   return false;
       }
       return true;
        
	}
}
