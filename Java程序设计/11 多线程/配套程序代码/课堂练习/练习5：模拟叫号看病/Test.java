package cn.myApp.ex4;

public class Test {
	public static void main(String[] args) {
		Thread thread = new Thread(new CureThread());
		thread.setPriority(Thread.MAX_PRIORITY);
		thread.start();
		//���߳�ģ��ҽԺ�к�
		for(int i=0;i<20;i++){
			System.out.println("��ͨ��:"+(i+1)+"�Ų����ڿ�����");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {				
				e1.printStackTrace();
			}
			if(i==9){				
				try {
					thread.join();
				} catch (InterruptedException e) {					
					e.printStackTrace();
				}
			}
		}
	}
}
