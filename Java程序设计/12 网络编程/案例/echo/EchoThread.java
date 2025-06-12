import java.net.* ;
import java.io.* ;
public class EchoThread implements Runnable{
	private Socket client = null ;
	public EchoThread(Socket client){
		this.client = client ;
	}
	public void run(){
		BufferedReader buf = null ;	// ����������
		PrintStream out = null ;		// ��ӡ��������
		try{
			out = new PrintStream(client.getOutputStream()) ;
			// ׼�����տͻ��˵�������Ϣ
			buf = new BufferedReader(new InputStreamReader(client.getInputStream())) ;
			boolean flag = true ;	// ��־λ����ʾ����һֱ���ղ���Ӧ��Ϣ
			while(flag){
				String str = buf.readLine() ;		// ���տͻ��˷��͵�����
				if(str==null||"".equals(str)){	// ��ʾû������
					flag = false ;	// �˳�ѭ��
				}else{
					if("bye".equals(str)){	// ������������Ϊbye��ʾ����
						flag = false ;
					}else{
						System.out.println(Thread.currentThread().getName()+"�յ��ͻ��ˣ�"+str);
						out.println("ECHO : " + str) ;	// ��Ӧ��Ϣ
					}
				}
			}
			System.out.println(Thread.currentThread().getName()+"�յ��ͻ���bye�˳�!");
			client.close() ;
		}catch(Exception e){}
		
	}
};