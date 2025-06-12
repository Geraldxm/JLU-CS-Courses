package cn.myApp.thread2;

/**
 * 模拟网络延时线程不安全 
 */
public class Site implements Runnable {
	private int count = 10; // 记录剩余票数
	private int num = 0; // 记录买到第几张票
	private boolean flag = false;  //记录是否售完

	public void run() {
		while (true) {
			if(!sale()){
			break;
			}
		}		
	}
	// 同步方法：卖票
	public synchronized boolean sale() {	
		if (count <= 0) {
			return false;
		}
		// 第一步：修改数据
		num++;
		count--;
		try {
			Thread.sleep(500); // 模拟网络延时
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 第二步：显示信息
		System.out.println(Thread.currentThread().getName() + "抢到第" + num
				+ "张票，剩余" + count + "张票！");
       if(Thread.currentThread().getName().equals("黄牛党")){
    	   return false;
       }
       return true;
        
	}
}
