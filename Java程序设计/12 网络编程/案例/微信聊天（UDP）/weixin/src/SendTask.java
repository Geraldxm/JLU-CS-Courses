import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
public class SendTask implements Runnable {
	private int sendPort; // �����ݵĶ˿ں�
	// ���췽��
	public SendTask(int sendPort) {
		this.sendPort = sendPort;
	}

	public void run() {
		try {
			// 1. ����DatagramSocket����
			DatagramSocket ds = new DatagramSocket();
			// 2.����Ҫ���͵�����
			Scanner sc = new Scanner(System.in);
			while (true) {
				String data = sc.nextLine();// ��ȡ�������������
				// 3.��װ���ݵ� DatagramPacket������
			byte[] buf = data.getBytes();
			DatagramPacket dp = new DatagramPacket(buf, buf.length,
			InetAddress.getByName("127.0.0.255"),sendPort);
				// 4.��������
				ds.send(dp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
