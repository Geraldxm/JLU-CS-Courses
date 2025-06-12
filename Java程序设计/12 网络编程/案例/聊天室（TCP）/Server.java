import java.io.*;
import java.net.*;
import java.util.*;
public class Server{
	public static ArrayList users=new ArrayList();			//增加一个集合，装所有的用户
	public static void main(String[] args) {
		ServerSocket server=null; 						//服务器端
		try{
			server=new ServerSocket(10000); 			//绑定服务器在10000口
			while(true){								//死循环，无限量接受请求
				//accept方法会阻塞在此处当接到客户请求的时候才会继续执行
				//返回一个跟客户端的连接Socket
				Socket s=server.accept();
				MyThread mt=new MyThread(s); 		//创建线程对象
				mt.start();								//启动线程
				users.add(mt); 						//将此线程加入用户列表
				//服务器端继续循环接受客户请求
			}
		}catch(Exception e){ 							//发现异常，打印异常信息
			e.printStackTrace(); 
		}finally{
			if(server!=null)							//不管正常异常都关闭服务器
				try{server.close();}catch(Exception e){}
		}
	}
}

class MyThread extends Thread {							//自定义多线程类
	private Socket s;
	private String username;
	private InetAddress ip;
	public MyThread(Socket s){
		this.s=s;
	}
	public void run(){
		try{
			//服务器端输出流
			OutputStream output=s.getOutputStream();		//获取客户端套接字输出流
			InputStream input=s.getInputStream();			//获取客户端套接字输入流
			//重建带缓冲的输出/输出字符串流类对象
			PrintWriter pw=new PrintWriter(new OutputStreamWriter(output));
			BufferedReader buf=new BufferedReader(new InputStreamReader(input));
			pw.println("Hello, welcome you ");			//向客户端输出字符串
			pw.println("enter your name : ");
			pw.flush();								//刷新缓冲，立即输出到客户端
			username=buf.readLine();					//获得客户的输入
			pw.println("hi "+username+",nice to see you ");	
			pw.flush();
			ip=s.getInetAddress();						//获取客户端IP地址
			//告诉所有人，谁加入了我们
			sendAll(username+" has joined us from "+ip);
			while(true){  								//无限量跟客户交谈
				String line=buf.readLine();				//从客户端获取一行字符串
				//如果客户输入为空或者空格
				if(line==null||line.trim().length()==0)
					continue;							//循环继续
				//如果客户输入一个bye，跳出循环
				if(line.equals("bye")){					//当客户端输入bye
					sendAll(username+" has leave us ");
					Server.users.remove(this); 			//把在线用户列表里，把自己删掉
					pw.println("bye");					//告诉客户端离开的信号	
					pw.flush(); 
break;							//跳出循环
				}
				if(line.equals(":)")){					//当客户端输入笑脸符
					sendAll(username+" 朝大家微微笑");	//向大家发送消息
					continue;							//循环继续
				}
				sendAll(username+" said : "+line);			//向大家发送消息
			}	
			s.close(); 								//关闭跟此客户的连接
		}catch(Exception e){
			Server.users.remove(this);		//发现异常，在线用户列表里，也要把自己删掉
		}finally{
			if(s!=null)
				try{s.close();}catch(Exception e){}
		}
	}
	public synchronized void sendMessage(String message){
		try{
			//服务器端输出流
			OutputStream output=s.getOutputStream();
			PrintWriter pw=new PrintWriter(new OutputStreamWriter(output));
			pw.println(message);
			pw.flush();
		}catch(Exception e){
			e.printStackTrace();	
		}
	}
	//此方法向所有在线用户发消息
	public static void sendAll(String message){
		//遍历所有的在线用户,并向其发消息
		Iterator it=Server.users.iterator();	
		while(it.hasNext()){					//利用迭代对象中的hasNext()来判断是否还有元素
			MyThread mt=(MyThread)it.next();	//假如还有则取出来还原成MyThread类对象
			mt.sendMessage(message);			//调用sendMessage方法将消息发送出去
		}
	}
	//下面是对username和ip属性的赋值/取值方法
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
