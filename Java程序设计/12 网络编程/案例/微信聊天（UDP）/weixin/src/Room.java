
import java.util.Scanner;
public class Room {
	public static void main(String[] args) {
		System.out.println("΢�����컶ӭ��!");
		Scanner sc = new Scanner(System.in);
		System.out.print("����������΢�źŵ�¼��");
		int sendPort = sc.nextInt();
		System.out.print("��������Ҫ������Ϣ��΢�źţ�");
		int receivePort = sc.nextInt();
		System.out.println("΢������ϵͳ��������");		
         //���Ͳ���
		new Thread(new SendTask(sendPort), "���Ͷ�����").start();
         //���ղ���
		new Thread(new ReceiveTask(receivePort), "���ն�����").start();
	}
}
