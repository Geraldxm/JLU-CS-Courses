package cn.myApp.thread.sale;
/**
 * �̲߳���ȫ��������Ʊ
 *
 */
public class Site implements Runnable{
	private int count=10;  //��¼ʣ��Ʊ��	
	private int num = 0;   //��¼�򵽵ڼ���Ʊ
	public void  run(){
		while(true){
			//û����Ʊʱ������ѭ��
			if(count<=0){
				break;
			}
			//��һ�����޸�����
			num++;
			count--;
			try {
				Thread.sleep(500); //ģ��������ʱ
			} catch (InterruptedException e) {			
				e.printStackTrace();
			}			
			//�ڶ�������ʾ��Ϣ
			System.out.println(Thread.currentThread().getName()+"������"+num+"��Ʊ��ʣ��"+count+"��Ʊ��");			
		}
	}
}
