package cn.myApp.sale3;

public class Test {
	public static void main(String[] args) {
		Site site = new Site();
		Thread person1= new Thread(site,"ÌÒÅÜÅÜ");
		Thread person2= new Thread(site,"ÇÀÆ±´úÀí");
		Thread person3= new Thread(site,"»ÆÅ£µ³");
		System.out.println("********¿ªÊ¼ÇÀÆ±********");
		person1.start();
		person2.start();
		person3.start();
	}
}
