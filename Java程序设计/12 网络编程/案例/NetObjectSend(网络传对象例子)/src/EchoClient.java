import java.net.* ;
import java.io.* ;
public class EchoClient{
	public static void main(String args[]) throws Exception {	// �����쳣�׳�
		Socket client = null ;	// ��ʾ�� ����
		client = new Socket("localhost",8888) ;
		
		ObjectOutputStream out = null ;	// ��������
		
		out = new ObjectOutputStream(client.getOutputStream()) ;
		
		
		for(int i=0;i<10;i++){
		  out.writeObject(new Person("����",i));
		}
		
	  out.close() ;
	  client.close() ;
	}
};