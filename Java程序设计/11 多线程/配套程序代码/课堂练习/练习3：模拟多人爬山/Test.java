package cn.myApp.ex2;
/**
 * ģ�������ɽ
 */
public class Test {
	public static void main(String[] args) {
		ClimbThread youngMan = new ClimbThread("������",500,1);
		ClimbThread oldMan = new ClimbThread("������",1500,1);
		System.out.println("********��ʼ��ɽ*********");
		youngMan.start();
		oldMan.start();
	}
}
