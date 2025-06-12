
public class race {
    private int toristDistance;//乌龟跑过的距离
    private int rabbitDistance;//兔子跑过的距离
    /**
     * 乌龟线程内部类
     */
    class Torist extends Thread{
        @Override
        public void run() {
            //分析编程代码
            for(int i=1;i<=800;i++){
                //判断兔子是否到达终点
                if(rabbitDistance==800){
       //当兔子先800的时候 兔子就已经赢了
       System.out.println("兔子赢得了比赛，此时乌龟才跑了"+toristDistance+"米");
                    break;
                }else{
                    //乌龟开始跑
                    toristDistance+=1;
                    //判断距离是否是100的倍数
                    if(toristDistance%100==0){
                        try {
                            if(rabbitDistance==700){
            System.out.println("乌龟跑了"+toristDistance+"米，此时兔子在睡觉");
                            }else{
            System.out.println("乌龟跑了"+toristDistance+"米，此时兔子跑过段距离  是"+rabbitDistance);
                            }
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    /**
     * 兔子线程内部类
     */
    class Rabbit extends Thread{
        @Override
        public void run() {
            //分析编程代码
            for(int i=1;i<=800/5;i++){
                //判断兔子是否到达终点
                if(toristDistance==800){
      //当兔子先1000的时候 兔子就已经赢了
      System.out.println("乌龟赢得了比赛，此时兔子跑了"+rabbitDistance+"米");
                    break;
                }else{
                    //乌龟开始跑
                    rabbitDistance+=5;
                    //判断距离是否是100的倍数
                    if(rabbitDistance%100==0){
                        try {
         System.out.println("兔子跑了"+rabbitDistance+"米，乌龟跑过了"
                                 +toristDistance);
                            if (rabbitDistance==700) {
        System.out.println("兔子觉得自己怎么能可以赢得比赛，所以选择睡一会");
                            	 Thread.sleep(10000);
							}
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    //测试
    public static void main(String[] args) {
        //1 外部类实例构建
        race outer=new race();
        //2兔子 乌龟线程实例构建
        Rabbit rabbit=outer.new Rabbit();
        Torist torist=outer.new Torist();
        //3 依次启动
        //在现实中 也不可能两个同时跑 这样也是很公平的
        rabbit.start();
        torist.start();
    }
}
