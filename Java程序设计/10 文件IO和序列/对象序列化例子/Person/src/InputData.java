import java.io.* ;
import java.util.* ;
import java.text.* ;
public class InputData{
	private BufferedReader buf = null ;
	public InputData(){// ֻҪ�������ݾ�Ҫʹ�ô����
		this.buf = new BufferedReader(new InputStreamReader(System.in)) ;
	}
	public String getString(String info){	// �õ��ַ�����Ϣ
		String temp = null ;
		System.out.print(info) ;	// ��ӡ��ʾ��Ϣ
		try{
			temp = this.buf.readLine() ;	// ��������
		}catch(IOException e){
			e.printStackTrace() ;
		}
		return temp ;
	}
	public int getInt(String info,String err){	// �õ�һ����������������
		int temp = 0 ;
		String str = null ;
		boolean flag = true ;	// ����һ�����λ
		while(flag){
			str = this.getString(info) ;	// ��������
			if(str.matches("^\\d+$")){	// �ж��Ƿ����������
				temp = Integer.parseInt(str) ;	// ת��
				flag = false ;	// ����ѭ��
			}else{
				System.out.println(err) ;	// ��ӡ������Ϣ
			}
		}
		return temp ;
	}
	public float getFloat(String info,String err){	// �õ�һ��С������������
		float temp = 0 ;
		String str = null ;
		boolean flag = true ;	// ����һ�����λ
		while(flag){
			str = this.getString(info) ;	// ��������
			if(str.matches("^\\d+.?\\d+$")){	// �ж��Ƿ����������
				temp = Float.parseFloat(str) ;	// ת��
				flag = false ;	// ����ѭ��
			}else{
				System.out.println(err) ;	// ��ӡ������Ϣ
			}
		}
		return temp ;
	}
	public Date getDate(String info,String err){	// �õ�һ��С������������
		Date temp = null ;
		String str = null ;
		boolean flag = true ;	// ����һ�����λ
		while(flag){
			str = this.getString(info) ;	// ��������
			if(str.matches("^\\d{4}-\\d{2}-\\d{2}$")){	// �ж��Ƿ����������
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;
				try{
					temp = sdf.parse(str) ;	// ���ַ�����ΪDate������
				}catch(Exception e){}
				flag = false ;	// ����ѭ��
			}else{
				System.out.println(err) ;	// ��ӡ������Ϣ
			}
		}
		return temp ;
	}
};