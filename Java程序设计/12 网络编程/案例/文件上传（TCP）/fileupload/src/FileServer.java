
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class FileServer {
	public static void main(String[] args) throws Exception {
         //创建ServerSocket对象
		ServerSocket serverSocket = new ServerSocket(10001);		
         while (true) {
			// 调用accept()方法接收客户端请求，得到Socket对象
			Socket s = serverSocket.accept();
		   // 每当和客户端建立Socket连接后，单独开启一个线程处理和客户端的交互
			new Thread(new ServerThread(s)).start();
		}
	}
}
class ServerThread implements Runnable {
	// 持有一个Socket类型的属性
	private Socket socket; 
	// 构造方法中把Socket对象作为实参传入
	public ServerThread(Socket socket) { 
		this.socket = socket;
	}
	public void run() {
		// 获取客户端的IP地址
		String ip = socket.getInetAddress().getHostAddress(); 
		// 上传图片个数
		int count = 1; 
		try {
			InputStream in = socket.getInputStream();
			// 创建上传图片目录的File对象
			File parentFile = new File("D:\\upload\\"); 
			// 如果不存在，就创建这个目录
			if (!parentFile.exists()) { 
				parentFile.mkdir();
			}
			// 把客户端的IP地址作为上传文件的文件名
			File file = new File(parentFile, ip + "(" + count + 
                                                            ").jpg");
			while (file.exists()) {
				// 如果文件名存在，则把count++
				file = new File(parentFile, ip + "(" + (count++) + 
                                                            ").jpg");
			}
			// 创建FileOutputStream对象
			FileOutputStream fos = new FileOutputStream(file);
			// 定义一个字节数组
			byte[] buf = new byte[1024]; 
			// 定义一个int类型的变量len，初始值为0
			int len = 0; 
			// 循环读取数据
			while ((len = in.read(buf)) != -1) { 
				fos.write(buf, 0, len);
			}
			// 获取服务端的输出流
			OutputStream out = socket.getOutputStream();
			// 上传成功后向客户端写出“上传成功”
			out.write("上传成功".getBytes()); 
			// 关闭输出流对象
			fos.close(); 
			// 关闭Socket对象
			socket.close(); 
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

