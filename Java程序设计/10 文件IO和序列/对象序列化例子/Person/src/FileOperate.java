import java.io.File ;
import java.io.FileInputStream ;
import java.io.FileOutputStream ;
import java.io.ObjectInputStream ;
import java.io.ObjectOutputStream ;
public class FileOperate{	// ����ר�����ڱ���Ͷ�ȡ
	private File file = null ;	// �����ļ�����
	public FileOperate(String pathName){	// ͨ��	���촫���ļ�·��
		this.file = new File(pathName) ;
	}
	public boolean save(Object obj) throws Exception{	// �������
		ObjectOutputStream oos = null ;		// ���������
		boolean flag = false ;	// ���������־λ
		try{
			oos = new ObjectOutputStream(new FileOutputStream(this.file)) ;	// ʵ�������������
			oos.writeObject(obj) ;	// д�����
			flag = true ;
		}catch(Exception e){
			throw e ;	// ���쳣���������ô�����
		}finally{
			if(oos!=null){
				oos.close() ;
			}
		}
		return flag ;
	}
	public Object load() throws Exception{	// ��ȡ����
		Object obj = null ;	// ���ն�ȡ������
		ObjectInputStream ois = null ;	
		try{	
			ois = new ObjectInputStream(new FileInputStream(this.file)) ;	// ʵ��������������
			obj = ois.readObject() ;	// ��ȡ����
		}catch(Exception e){
			throw e ;
		}finally{
			if(ois!=null){
				ois.close() ;	// �ر�
			}
		}
		return obj ;
	}
};