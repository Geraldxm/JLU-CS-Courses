import java.net.* ;
import java.io.* ;
public class EchoThreadServer{
	public static void main(String args[]) throws Exception {	// �����쳣�׳�
		ServerSocket server = null ;		// ����ServerSocket��
		Socket client = null ;	// ��ʾ�� ����
		server = new ServerSocket(8888) ;	// ��������8888�˿��ϼ���
		boolean f = true ;	// ��������λ
		while(f){
			System.out.println("���������У��ȴ��ͻ������ӡ�") ;
			client = server.accept() ;		// �õ����ӣ�������뵽����״̬
			new Thread(new EchoThread(client)).start() ;	// ÿһ���ͻ��˱�ʾһ���߳�
		}
		server.close() ;
	}
};