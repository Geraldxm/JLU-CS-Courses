package cn.myApp.ex5;
/**
 * ģ���������
 */
public class Test {
	public static void main(String[] args) {
		RunThread run = new RunThread();
		//5�˲μӽ�������
		for(int i=0;i<5;i++){
			new Thread(run,(i+1)+"��ѡ��").start();
		}
	}
}
