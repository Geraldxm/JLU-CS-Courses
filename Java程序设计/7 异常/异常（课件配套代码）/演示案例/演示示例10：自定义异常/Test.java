package cn.com.myApp;

class Person{
	private String name="";			//����
	private int age=0;				//����
	private String sex="��";		//�Ա�
	 //�����Ա�
	public void setSex(String sex) throws GenderException {
		  if ("��".equals(sex) || "Ů".equals(sex))
		    this.sex = sex;
		  else {
		    throw new GenderException("�Ա�����ǡ��С����ߡ�Ů����");
		  }
	}

	//��ӡ������Ϣ
	public void print(){
		System.out.println(this.name+"��"+this.sex+"��"+this.age+"�꣩");
	}
}
public class Test{
	public static void main(String[] args){
		Person person = new Person();
		  try {
		    person.setSex("Male");
		    person.print();
		  } catch (GenderException e) {
		    e.printStackTrace();
		  }

	}
}








