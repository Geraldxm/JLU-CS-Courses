import java.io.File ;
import java.io.FileOutputStream ;
import java.io.OutputStream ;
import java.io.ObjectOutputStream ;
import java.util.ArrayList;

public class SerDemo01{
	public static void main(String args[]) throws Exception {
		File f = new File("D:" + File.separator + "test.txt") ;	// ���屣��·��
		ArrayList<Person> a=new ArrayList<Person>();
		for(int i=0;i<10;i++){
			a.add(new Person("��"+i,i));
		}
		
		ObjectOutputStream oos = null ;	// �������������
		OutputStream out = new FileOutputStream(f) ;	// �ļ������
		
		oos = new ObjectOutputStream(out) ;
		oos.writeObject(a) ;	// �������
		oos.close() ;	// �ر�
	}
};