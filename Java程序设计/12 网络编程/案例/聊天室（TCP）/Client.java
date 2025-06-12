import java.net.*;
import java.io.*;
import java.util.*;
public class Client{	
	public static void main(String[] args) {
		Socket s=null;
		try{
			s=new Socket("127.0.0.1",10000);			//���ݷ�������IP��ַ�Ͷ˿ںŴ���Socket����
			InputStream input=s.getInputStream();		//��ȡ�׽����ֽ�������
			OutputStream output=s.getOutputStream();	//��ȡ�׽����ֽ������
			//�ؽ���������ַ�������/����������
			BufferedReader buf=new BufferedReader(new InputStreamReader(input));
			PrintWriter pw=new PrintWriter(new OutputStreamWriter(output));
			//����������ĴӼ���������ַ����������
			BufferedReader cin=new BufferedReader(new InputStreamReader(System.in));
			new ReadThread(s).start();				//����һ���̲߳��ϴӷ�������ȡ��Ϣ����ʾ
			//�����ж����û����֣����͵���������
			pw.println(cin.readLine());
			pw.flush();							//һ��Ҫ��ˢ��
			while(true){							//ѭ������
				String command=cin.readLine();		//�Ӽ��̶�ȡһ��
				pw.println(command+"\n");			//��ӻ��з��������������
				pw.flush();						//ˢ��
			}
		}catch(Exception e){						//�����쳣����ӡ�쳣
			e.printStackTrace();
		}finally{								//�����з��쳣������Ҫ���Ѵ�����s����ر�
			if(s!=null)
				try{s.close();}catch(Exception e){}
		}
	}
}

class ReadThread extends Thread{						//�Զ�����߳���
	private Socket s=null;
	public ReadThread(Socket s){
		this.s=s;
	}
	public void run(){
		try{
			InputStream input=s.getInputStream();		//��ȡ�׽���������
			//�ؽ���������ַ��������������
			BufferedReader buf=new BufferedReader(new InputStreamReader(input));
			while(true){							//ѭ���ӷ������˻�ȡ����ʾ�ڵ�ǰ��Ļ
				String line=buf.readLine();
				if(line.equals("bye"))	break;		//����ӷ������˽��յ�bye�ַ���������ѭ��
           System.out.println(line);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

