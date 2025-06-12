import java.io.File ;
import java.io.FileOutputStream ;
import java.io.OutputStream ;
import java.io.ObjectOutputStream ;
import java.util.ArrayList;

public class SerDemo01{
	public static void main(String args[]) throws Exception {
		File f = new File("D:" + File.separator + "test.txt") ;	// 定义保存路径
		ArrayList<Person> a=new ArrayList<Person>();
		for(int i=0;i<10;i++){
			a.add(new Person("张"+i,i));
		}
		
		ObjectOutputStream oos = null ;	// 声明对象输出流
		OutputStream out = new FileOutputStream(f) ;	// 文件输出流
		
		oos = new ObjectOutputStream(out) ;
		oos.writeObject(a) ;	// 保存对象
		oos.close() ;	// 关闭
	}
};