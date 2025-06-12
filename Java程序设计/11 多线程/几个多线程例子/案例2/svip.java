
public class svip {
	public static void main(String[]args) throws InterruptedException
	{
	    new Thread(new normal()).start();
	}
	}
	class special extends Thread{
	public void run()
	{
	    System.out.println("svip客户开始办理业务");
	    System.out.println("svip客户办理业务的倒计时");
	    for(int i=10;i>=0;i--)
	    {
	        System.out.println(i+"秒");
	        try {
	            Thread.sleep(1000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	    System.out.println("svip客户办理完毕");
	}
	}
	class normal extends Thread{
	public void run(){
	    System.out.println("业务办理窗口在正常排队中");
		  System.out.println("此时来了一位svip客户");
		    Thread t=new Thread(new special());
             //各走各的逻辑错误，再加入join先执行完special，再执行normal剩下的
		    t.start(); 
		    try {
		        t.join();
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		    System.out.println("业务办理窗口又恢复正常排队");
	}
	}
