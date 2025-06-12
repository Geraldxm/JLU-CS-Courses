import java.net.*;
import java.io.*;
import java.util.*;
public class Client{	
	public static void main(String[] args) {
		Socket s=null;
		try{
			s=new Socket("127.0.0.1",10000);			//根据服务器端IP地址和端口号创建Socket对象
			InputStream input=s.getInputStream();		//获取套接字字节输入流
			OutputStream output=s.getOutputStream();	//获取套接字字节输出流
			//重建带缓冲的字符串输入/输出流类对象
			BufferedReader buf=new BufferedReader(new InputStreamReader(input));
			PrintWriter pw=new PrintWriter(new OutputStreamWriter(output));
			//构建带缓冲的从键盘输入的字符串流类对象
			BufferedReader cin=new BufferedReader(new InputStreamReader(System.in));
			new ReadThread(s).start();				//创建一个线程不断从服务器获取信息并显示
			//命令行读入用户名字，发送到服务器端
			pw.println(cin.readLine());
			pw.flush();							//一定要有刷新
			while(true){							//循环处理
				String command=cin.readLine();		//从键盘读取一行
				pw.println(command+"\n");			//添加换行符后向服务器发送
				pw.flush();						//刷新
			}
		}catch(Exception e){						//发生异常，打印异常
			e.printStackTrace();
		}finally{								//不管有否异常，都需要将已创建的s对象关闭
			if(s!=null)
				try{s.close();}catch(Exception e){}
		}
	}
}

class ReadThread extends Thread{						//自定义读线程类
	private Socket s=null;
	public ReadThread(Socket s){
		this.s=s;
	}
	public void run(){
		try{
			InputStream input=s.getInputStream();		//获取套接字输入流
			//重建带缓冲的字符串输入流类对象
			BufferedReader buf=new BufferedReader(new InputStreamReader(input));
			while(true){							//循环从服务器端获取，显示在当前屏幕
				String line=buf.readLine();
				if(line.equals("bye"))	break;		//假如从服务器端接收到bye字符串，跳出循环
           System.out.println(line);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

