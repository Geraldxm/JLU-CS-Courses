package cn.myApp.ex5;
/**
 * ģ���������
 *
 */
public class Test {
	public static void main(String[] args) {
		RunThread run = new RunThread();
		for(int i=0;i<3;i++){
			new Thread(run,(i+1)+"��ѡ��").start();
		}
	}
}
