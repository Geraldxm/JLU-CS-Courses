
import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class ReceiveTask implements Runnable{
	private int receivePort;// �������ݵĶ˿ں�
	public ReceiveTask(int receivePort) {
		this.receivePort = receivePort;
	}

	public void run() {
		try {
			// 1.DatagramSocket����
			DatagramSocket ds = new DatagramSocket(receivePort);
			// 2.����DatagramPacket����
			byte[] buf = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			// 3.��������
			while (true) {
				ds.receive(dp);
				// 4.��ʾ���յ�������
				String str = new String(dp.getData(), 0, 
                                               dp.getLength());
				System.out.println("�յ�" + 
                   dp.getAddress().getHostAddress()
			     + "--���͵�����--" + str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

