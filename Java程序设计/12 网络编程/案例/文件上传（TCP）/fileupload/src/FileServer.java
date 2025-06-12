
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class FileServer {
	public static void main(String[] args) throws Exception {
         //����ServerSocket����
		ServerSocket serverSocket = new ServerSocket(10001);		
         while (true) {
			// ����accept()�������տͻ������󣬵õ�Socket����
			Socket s = serverSocket.accept();
		   // ÿ���Ϳͻ��˽���Socket���Ӻ󣬵�������һ���̴߳���Ϳͻ��˵Ľ���
			new Thread(new ServerThread(s)).start();
		}
	}
}
class ServerThread implements Runnable {
	// ����һ��Socket���͵�����
	private Socket socket; 
	// ���췽���а�Socket������Ϊʵ�δ���
	public ServerThread(Socket socket) { 
		this.socket = socket;
	}
	public void run() {
		// ��ȡ�ͻ��˵�IP��ַ
		String ip = socket.getInetAddress().getHostAddress(); 
		// �ϴ�ͼƬ����
		int count = 1; 
		try {
			InputStream in = socket.getInputStream();
			// �����ϴ�ͼƬĿ¼��File����
			File parentFile = new File("D:\\upload\\"); 
			// ��������ڣ��ʹ������Ŀ¼
			if (!parentFile.exists()) { 
				parentFile.mkdir();
			}
			// �ѿͻ��˵�IP��ַ��Ϊ�ϴ��ļ����ļ���
			File file = new File(parentFile, ip + "(" + count + 
                                                            ").jpg");
			while (file.exists()) {
				// ����ļ������ڣ����count++
				file = new File(parentFile, ip + "(" + (count++) + 
                                                            ").jpg");
			}
			// ����FileOutputStream����
			FileOutputStream fos = new FileOutputStream(file);
			// ����һ���ֽ�����
			byte[] buf = new byte[1024]; 
			// ����һ��int���͵ı���len����ʼֵΪ0
			int len = 0; 
			// ѭ����ȡ����
			while ((len = in.read(buf)) != -1) { 
				fos.write(buf, 0, len);
			}
			// ��ȡ����˵������
			OutputStream out = socket.getOutputStream();
			// �ϴ��ɹ�����ͻ���д�����ϴ��ɹ���
			out.write("�ϴ��ɹ�".getBytes()); 
			// �ر����������
			fos.close(); 
			// �ر�Socket����
			socket.close(); 
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

