package cn.myApp.thread2;

public class Test {
	public static void main(String[] args) {
		Site site = new Site();
		Thread person1= new Thread(site,"������");
		Thread person2= new Thread(site,"��Ʊ����");
		Thread person3= new Thread(site,"��ţ��");
		person1.start();
		person2.start();
		person3.start();
	}
}
