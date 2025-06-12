
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
public class FileClient {
	public static void main(String[] args) throws Exception {
		// 创建客户端Socket
		Socket socket = new Socket("127.0.0.1", 10001);
		// 获取Socket的输出流对象
		OutputStream out = socket.getOutputStream(); 
		// 创建FileInputStream对象
		System.out.println("请输入你要上传文件的路径：");
		Scanner sc =new Scanner(System.in);
		String upload = sc.nextLine();
		if(!upload.isEmpty()){
		FileInputStream fis = new FileInputStream(upload);
		// 定义一个字节数组
		byte[] buf = new byte[1024];
		// 定义一个int类型的变量len
		int len; 
		// 循环读取数据
		while ((len = fis.read(buf)) != -1) { 
			out.write(buf, 0, len);
		}
		// 关闭客户端输出流
		socket.shutdownOutput();
		// 获取Socket的输入流对象
		InputStream in = socket.getInputStream(); 
		// 定义一个字节数组
		byte[] bufMsg = new byte[1024];
		// 接收服务端的信息
		int num = in.read(bufMsg); 
		String Msg = new String(bufMsg, 0, num);
		System.out.println(Msg);
		// 关键输入流对象
		fis.close(); 
		// 关闭Socket对象
		socket.close(); 
		}else {
			System.out.println("对不起请您输入文件路径后再上传！！！");
		}
	}
}

