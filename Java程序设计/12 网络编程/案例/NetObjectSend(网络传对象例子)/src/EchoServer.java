import java.net.* ;
import java.io.* ;
public class EchoServer{
	public static void main(String args[]) throws Exception {	// �����쳣�׳�
		ServerSocket server = null ;		// ����ServerSocket��
		Socket client = null ;	// ��ʾ�� ����
		ObjectInputStream buf = null ;	// ����������
		ObjectOutputStream out = null ;		// ��ӡ��������
		server = new ServerSocket(8888) ;	// ��������8888�˿��ϼ���
		boolean f = true ;	// ��������λ
		while(f){
			System.out.println("���������У��ȴ��ͻ������ӡ�") ;
			client = server.accept() ;		// �õ����ӣ�������뵽����״̬
			
			buf = new ObjectInputStream(client.getInputStream()) ;
		
			for(int i=0;i<10;i++){
				Person str = (Person)buf.readObject();		// ���տͻ��˷��͵�����
				System.out.println("���������յ�һ��person:"+str);
			}
			client.close() ;
		}
		server.close() ;
	}
};