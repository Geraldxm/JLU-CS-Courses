public class Operate{
	public static void add(){	// ���Ӳ���
		InputData input = new InputData() ;		// ʵ�����������ݶ���
		FileOperate fo = new FileOperate("d:\\test.per") ;
		String name = input.getString("������������") ;
		int age = input.getInt("���������䣺" , "������������֣�") ;
		Person per = new Person(name,age) ;	// ʵ����Person����
		try{
			fo.save(per) ;	// �������
		}catch(Exception e){
			e.printStackTrace() ;
		}
		System.out.println("��Ϣ���ӳɹ���") ;
	}
	public static void delete(){	// ɾ������
		FileOperate fo = new FileOperate("d:\\test.per") ;
		try{
			fo.save(null) ;	// �������
		}catch(Exception e){
			e.printStackTrace() ;
		}
		System.out.println("��Ϣɾ���ɹ���") ;
	}
	public static void update(){	// ���²���
		InputData input = new InputData() ;		// ʵ�����������ݶ���
		FileOperate fo = new FileOperate("d:\\test.per") ;
		Person per = null ;
		try{
			per = (Person)fo.load() ;	// ��ȡ����
		}catch(Exception e){
			e.printStackTrace() ;
		}
		String name = input.getString("������������ԭ������"+per.getName()+"����") ;
		int age = input.getInt("���������䣨ԭ���䣺"+per.getAge()+"����" , "������������֣�") ;
		per = new Person(name,age) ;	// ʵ����Person����
		try{
			fo.save(per) ;	// �������
		}catch(Exception e){
			e.printStackTrace() ;
		}
		System.out.println("��Ϣ�޸ĳɹ���") ;
	}
	public static void find(){	// �鿴����
		FileOperate fo = new FileOperate("d:\\test.per") ;
		Person per = null ;
		try{
			per = (Person)fo.load() ;	// ��ȡ����
		}catch(Exception e){
			e.printStackTrace() ;
		}
		System.out.println(per) ;
	}
};