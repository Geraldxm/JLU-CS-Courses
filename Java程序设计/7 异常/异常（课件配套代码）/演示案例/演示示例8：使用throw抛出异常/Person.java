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
	/**
	 * ��ӡ������Ϣ��
	 */
	public void print() {
		System.out.println(this.name + "��" + this.sex 
				+ "��" + this.age + "�꣩");
	}
}