package cn.myApp.thread.join;
/**
 * �߳�ǿ��ִ��
 */
public class ThreadJoinDemo {
	public static void main(String[] args) {
		System.out.println("*****�߳�ǿ��ִ��******");
		//�������̲߳�����
		Thread temp = new Thread(new MyThread());
		temp.start();
		for(int i=0;i<20;i++){
			if(i==5){				
				try {
					//�������̣߳����߳�ǿ��ִ��
					temp.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"���У�"+i);
		}
	}	
}
