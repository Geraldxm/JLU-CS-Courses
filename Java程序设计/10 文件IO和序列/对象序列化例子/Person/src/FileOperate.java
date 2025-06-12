import java.io.File ;
import java.io.FileInputStream ;
import java.io.FileOutputStream ;
import java.io.ObjectInputStream ;
import java.io.ObjectOutputStream ;
public class FileOperate{	// 此类专门用于保存和读取
	private File file = null ;	// 定义文件对象
	public FileOperate(String pathName){	// 通过	构造传递文件路径
		this.file = new File(pathName) ;
	}
	public boolean save(Object obj) throws Exception{	// 保存对象
		ObjectOutputStream oos = null ;		// 对象输出流
		boolean flag = false ;	// 定义操作标志位
		try{
			oos = new ObjectOutputStream(new FileOutputStream(this.file)) ;	// 实例化对象输出流
			oos.writeObject(obj) ;	// 写入对象
			flag = true ;
		}catch(Exception e){
			throw e ;	// 有异常交给被调用处处理
		}finally{
			if(oos!=null){
				oos.close() ;
			}
		}
		return flag ;
	}
	public Object load() throws Exception{	// 读取对象
		Object obj = null ;	// 接收读取的内容
		ObjectInputStream ois = null ;	
		try{	
			ois = new ObjectInputStream(new FileInputStream(this.file)) ;	// 实例化对象输入流
			obj = ois.readObject() ;	// 读取对象
		}catch(Exception e){
			throw e ;
		}finally{
			if(ois!=null){
				ois.close() ;	// 关闭
			}
		}
		return obj ;
	}
};