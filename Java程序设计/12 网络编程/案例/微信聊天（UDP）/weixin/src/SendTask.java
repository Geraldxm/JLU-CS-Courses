import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
public class SendTask implements Runnable {
	private int sendPort; // 发数据的端口号
	// 构造方法
	public SendTask(int sendPort) {
		this.sendPort = sendPort;
	}

	public void run() {
		try {
			// 1. 创建DatagramSocket对象
			DatagramSocket ds = new DatagramSocket();
			// 2.输入要发送的数据
			Scanner sc = new Scanner(System.in);
			while (true) {
				String data = sc.nextLine();// 获取键盘输入的数据
				// 3.封装数据到 DatagramPacket对象中
			byte[] buf = data.getBytes();
			DatagramPacket dp = new DatagramPacket(buf, buf.length,
			InetAddress.getByName("127.0.0.255"),sendPort);
				// 4.发送数据
				ds.send(dp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
