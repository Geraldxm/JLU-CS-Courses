import java.io.Serializable ;
public class Person implements Serializable{
	private String name ;	// ����name���ԣ����Ǵ����Բ������л�
	private int age ;		// ����age����
	public Person(String name,int age){	// ͨ��������������
		this.name = name ;
		this.age = age ;
	}
	public String toString(){	// ��дtoString()����
		return "������" + this.name + "�����䣺" + this.age ;
	}
};
