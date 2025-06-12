package cn.myApp.priority;
/**
 * �����߳����ȼ�
 *
 */
public class MyThread implements Runnable{
	public void run(){	
		for(int i=1;i<100;i++){			
			System.out.println(Thread.currentThread().getName()+"��������:"+i);
		}
	}
	public static void main(String[] args) {
		Thread t1 = new Thread(new MyThread(),"�߳�A");//ͨ�����췽��ָ���߳���
		Thread t2 = new Thread(new MyThread(),"�߳�B");
		//�����̵߳����ȼ����Ա���ʾ����ǰ�����ú�Ľ����
		t1.setPriority(Thread.MAX_PRIORITY);
		t2.setPriority(Thread.MIN_PRIORITY);
		System.out.println("****�̵߳����ȼ�****");
		System.out.println("�߳�A�����ȼ���"+t1.getPriority());
		System.out.println("�߳�B�����ȼ���"+t2.getPriority());
		System.out.println("****************");
		t1.start();	
		t2.start();		
	}
}
