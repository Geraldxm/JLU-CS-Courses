package cn.myApp.ex4;
/**
 * 治病
 *
 */
public class CureThread implements Runnable {
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("特需号："+(i+1)+"号病人在看病！");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
