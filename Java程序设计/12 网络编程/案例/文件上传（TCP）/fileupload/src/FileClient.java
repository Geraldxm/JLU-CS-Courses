
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
public class FileClient {
	public static void main(String[] args) throws Exception {
		// �����ͻ���Socket
		Socket socket = new Socket("127.0.0.1", 10001);
		// ��ȡSocket�����������
		OutputStream out = socket.getOutputStream(); 
		// ����FileInputStream����
		System.out.println("��������Ҫ�ϴ��ļ���·����");
		Scanner sc =new Scanner(System.in);
		String upload = sc.nextLine();
		if(!upload.isEmpty()){
		FileInputStream fis = new FileInputStream(upload);
		// ����һ���ֽ�����
		byte[] buf = new byte[1024];
		// ����һ��int���͵ı���len
		int len; 
		// ѭ����ȡ����
		while ((len = fis.read(buf)) != -1) { 
			out.write(buf, 0, len);
		}
		// �رտͻ��������
		socket.shutdownOutput();
		// ��ȡSocket������������
		InputStream in = socket.getInputStream(); 
		// ����һ���ֽ�����
		byte[] bufMsg = new byte[1024];
		// ���շ���˵���Ϣ
		int num = in.read(bufMsg); 
		String Msg = new String(bufMsg, 0, num);
		System.out.println(Msg);
		// �ؼ�����������
		fis.close(); 
		// �ر�Socket����
		socket.close(); 
		}else {
			System.out.println("�Բ������������ļ�·�������ϴ�������");
		}
	}
}

