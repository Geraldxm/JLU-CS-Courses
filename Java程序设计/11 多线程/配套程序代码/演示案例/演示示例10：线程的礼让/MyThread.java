package cn.myApp.thread.yield;

public class MyThread implements Runnable{
     public void run(){
    	 for(int i=0;i<5;i++){
    		 System.out.println(Thread.currentThread().getName()+"正在运行："+i);
    		 if(i==3){
    			 System.out.print("线程礼让：");
				Thread.yield();
    		 }
    	 }
     }
}
