package cn.myApp.thread.yield;

public class MyThread implements Runnable{
     public void run(){
    	 for(int i=0;i<5;i++){
    		 System.out.println(Thread.currentThread().getName()+"�������У�"+i);
    		 if(i==3){
    			 System.out.print("�߳����ã�");
				Thread.yield();
    		 }
    	 }
     }
}
