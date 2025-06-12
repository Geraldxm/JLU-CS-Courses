package cn.myApp.ex5;
/**
 * 模拟接力赛跑
 */
public class Test {
	public static void main(String[] args) {
		RunThread run = new RunThread();
		//5人参加接力赛跑
		for(int i=0;i<5;i++){
			new Thread(run,(i+1)+"号选手").start();
		}
	}
}
