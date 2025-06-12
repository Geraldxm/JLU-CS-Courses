
import java.util.Scanner;
public class Room {
	public static void main(String[] args) {
		System.out.println("微信聊天欢迎您!");
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入您的微信号登录：");
		int sendPort = sc.nextInt();
		System.out.print("请输入您要发送消息的微信号：");
		int receivePort = sc.nextInt();
		System.out.println("微信聊天系统启动！！");		
         //发送操作
		new Thread(new SendTask(sendPort), "发送端任务").start();
         //接收操作
		new Thread(new ReceiveTask(receivePort), "接收端任务").start();
	}
}
