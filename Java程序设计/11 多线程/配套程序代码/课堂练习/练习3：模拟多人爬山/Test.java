package cn.myApp.ex2;
/**
 * 模拟多人爬山
 */
public class Test {
	public static void main(String[] args) {
		ClimbThread youngMan = new ClimbThread("年轻人",500,1);
		ClimbThread oldMan = new ClimbThread("老年人",1500,1);
		System.out.println("********开始爬山*********");
		youngMan.start();
		oldMan.start();
	}
}
