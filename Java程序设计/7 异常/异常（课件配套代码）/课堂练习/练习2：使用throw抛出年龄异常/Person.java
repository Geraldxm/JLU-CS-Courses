package cn.myApp.exception;

/**
 * ʹ��throw�ڷ������׳��쳣��
 * @author administrator
 */
public class Person {
	private String name = "";// ����
	private int age = 0;// ����
	private String sex = "��";// �Ա�
	/**
	 * �����Ա�
	 * @param sex �Ա�
	 * @throws Exception
	 */
	public void setSex(String sex) throws Exception {
		if ("��".equals(sex) || "Ů".equals(sex))
			this.sex = sex;
		else {
			throw new Exception("�Ա�����ǡ��С����ߡ�Ů����");
		}
	}
	
	public void setAge(int age) throws Exception {
		if (age>=1 && age<=100)
			this.age = age;
		else {
			throw new Exception("���������1��100֮�䣡");
		}
	}

	/**
	 * ��ӡ������Ϣ��
	 */
	public void print() {
		System.out.println(this.name + "��" + this.sex 
				+ "��" + this.age + "�꣩");
	}
}