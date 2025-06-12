package cn.myApp.thread.sale;
/**
 * 线程不安全的网络抢票
 *
 */
public class Site implements Runnable{
	private int count=10;  //记录剩余票数	
	private int num = 0;   //记录买到第几张票
	public void  run(){
		while(true){
			//没有余票时，跳出循环
			if(count<=0){
				break;
			}
			//第一步：修改数据
			num++;
			count--;
			try {
				Thread.sleep(500); //模拟网络延时
			} catch (InterruptedException e) {			
				e.printStackTrace();
			}			
			//第二步：显示信息
			System.out.println(Thread.currentThread().getName()+"抢到第"+num+"张票，剩余"+count+"张票！");			
		}
	}
}
