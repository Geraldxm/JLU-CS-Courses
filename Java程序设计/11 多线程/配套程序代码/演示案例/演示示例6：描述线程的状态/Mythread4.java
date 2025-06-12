package cn.myApp.thread.state;
/**
 * 描述线程的状态
 */
public class Mythread implements Runnable{
	public void run() {
		try {
			System.out.println("线程t在运行！");
			Thread.sleep(500);
			System.out.println("线程t在短时间睡眠后重新运行！");
		} catch (InterruptedException IE) {
			System.out.println("线程被中断");
		}
	}
	public static void main(String args[]) {
		Thread t=new Thread(new Mythread());
		 System.out.println ("线程 t 为新建！");		
		 t.start();
		 System.out.println ("线程 t 为就绪！");
	}
}
