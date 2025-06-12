import java.io.*;
import java.net.*;
import java.util.*;
public class Server{
	public static ArrayList users=new ArrayList();			//����һ�����ϣ�װ���е��û�
	public static void main(String[] args) {
		ServerSocket server=null; 						//��������
		try{
			server=new ServerSocket(10000); 			//�󶨷�������10000��
			while(true){								//��ѭ������������������
				//accept�����������ڴ˴����ӵ��ͻ������ʱ��Ż����ִ��
				//����һ�����ͻ��˵�����Socket
				Socket s=server.accept();
				MyThread mt=new MyThread(s); 		//�����̶߳���
				mt.start();								//�����߳�
				users.add(mt); 						//�����̼߳����û��б�
				//�������˼���ѭ�����ܿͻ�����
			}
		}catch(Exception e){ 							//�����쳣����ӡ�쳣��Ϣ
			e.printStackTrace(); 
		}finally{
			if(server!=null)							//���������쳣���رշ�����
				try{server.close();}catch(Exception e){}
		}
	}
}

class MyThread extends Thread {							//�Զ�����߳���
	private Socket s;
	private String username;
	private InetAddress ip;
	public MyThread(Socket s){
		this.s=s;
	}
	public void run(){
		try{
			//�������������
			OutputStream output=s.getOutputStream();		//��ȡ�ͻ����׽��������
			InputStream input=s.getInputStream();			//��ȡ�ͻ����׽���������
			//�ؽ�����������/����ַ����������
			PrintWriter pw=new PrintWriter(new OutputStreamWriter(output));
			BufferedReader buf=new BufferedReader(new InputStreamReader(input));
			pw.println("Hello, welcome you ");			//��ͻ�������ַ���
			pw.println("enter your name : ");
			pw.flush();								//ˢ�»��壬����������ͻ���
			username=buf.readLine();					//��ÿͻ�������
			pw.println("hi "+username+",nice to see you ");	
			pw.flush();
			ip=s.getInetAddress();						//��ȡ�ͻ���IP��ַ
			//���������ˣ�˭����������
			sendAll(username+" has joined us from "+ip);
			while(true){  								//���������ͻ���̸
				String line=buf.readLine();				//�ӿͻ��˻�ȡһ���ַ���
				//����ͻ�����Ϊ�ջ��߿ո�
				if(line==null||line.trim().length()==0)
					continue;							//ѭ������
				//����ͻ�����һ��bye������ѭ��
				if(line.equals("bye")){					//���ͻ�������bye
					sendAll(username+" has leave us ");
					Server.users.remove(this); 			//�������û��б�����Լ�ɾ��
					pw.println("bye");					//���߿ͻ����뿪���ź�	
					pw.flush(); 
break;							//����ѭ��
				}
				if(line.equals(":)")){					//���ͻ�������Ц����
					sendAll(username+" �����΢΢Ц");	//���ҷ�����Ϣ
					continue;							//ѭ������
				}
				sendAll(username+" said : "+line);			//���ҷ�����Ϣ
			}	
			s.close(); 								//�رո��˿ͻ�������
		}catch(Exception e){
			Server.users.remove(this);		//�����쳣�������û��б��ҲҪ���Լ�ɾ��
		}finally{
			if(s!=null)
				try{s.close();}catch(Exception e){}
		}
	}
	public synchronized void sendMessage(String message){
		try{
			//�������������
			OutputStream output=s.getOutputStream();
			PrintWriter pw=new PrintWriter(new OutputStreamWriter(output));
			pw.println(message);
			pw.flush();
		}catch(Exception e){
			e.printStackTrace();	
		}
	}
	//�˷��������������û�����Ϣ
	public static void sendAll(String message){
		//�������е������û�,�����䷢��Ϣ
		Iterator it=Server.users.iterator();	
		while(it.hasNext()){					//���õ��������е�hasNext()���ж��Ƿ���Ԫ��
			MyThread mt=(MyThread)it.next();	//���绹����ȡ������ԭ��MyThread�����
			mt.sendMessage(message);			//����sendMessage��������Ϣ���ͳ�ȥ
		}
	}
	//�����Ƕ�username��ip���Եĸ�ֵ/ȡֵ����
	public InetAddress getIp() {
		return ip;
	}
	public void setIp(InetAddress ip) {
		this.ip = ip;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}	
}
