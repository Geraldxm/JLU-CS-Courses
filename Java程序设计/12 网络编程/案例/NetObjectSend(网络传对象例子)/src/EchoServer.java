import java.net.* ;
import java.io.* ;
public class EchoServer{
	public static void main(String args[]) throws Exception {	// 所有异常抛出
		ServerSocket server = null ;		// 定义ServerSocket类
		Socket client = null ;	// 表示客 户端
		ObjectInputStream buf = null ;	// 接收输入流
		ObjectOutputStream out = null ;		// 打印流输出最方便
		server = new ServerSocket(8888) ;	// 服务器在8888端口上监听
		boolean f = true ;	// 定义个标记位
		while(f){
			System.out.println("服务器运行，等待客户端连接。") ;
			client = server.accept() ;		// 得到连接，程序进入到阻塞状态
			
			buf = new ObjectInputStream(client.getInputStream()) ;
		
			for(int i=0;i<10;i++){
				Person str = (Person)buf.readObject();		// 接收客户端发送的内容
				System.out.println("服务器端收到一个person:"+str);
			}
			client.close() ;
		}
		server.close() ;
	}
};