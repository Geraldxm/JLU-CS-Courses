import java.net.* ;
import java.io.* ;
public class EchoClient{
	public static void main(String args[]) throws Exception {	// 所有异常抛出
		Socket client = null ;	// 表示客 户端
		client = new Socket("localhost",8888) ;
		
		ObjectOutputStream out = null ;	// 发送数据
		
		out = new ObjectOutputStream(client.getOutputStream()) ;
		
		
		for(int i=0;i<10;i++){
		  out.writeObject(new Person("张三",i));
		}
		
	  out.close() ;
	  client.close() ;
	}
};