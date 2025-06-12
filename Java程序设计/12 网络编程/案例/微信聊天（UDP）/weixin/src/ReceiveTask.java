
import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class ReceiveTask implements Runnable{
	private int receivePort;// 接收数据的端口号
	public ReceiveTask(int receivePort) {
		this.receivePort = receivePort;
	}

	public void run() {
		try {
			// 1.DatagramSocket对象
			DatagramSocket ds = new DatagramSocket(receivePort);
			// 2.创建DatagramPacket对象
			byte[] buf = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			// 3.接收数据
			while (true) {
				ds.receive(dp);
				// 4.显示接收到的数据
				String str = new String(dp.getData(), 0, 
                                               dp.getLength());
				System.out.println("收到" + 
                   dp.getAddress().getHostAddress()
			     + "--发送的数据--" + str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

