package cn.myApp.exception;

/**
 * ����throw�׳����쳣��
 * @author administrator
 */
public class Test8 {
	public static void main(String[] args) {
		Person person = new Person();
		try {
			person.setSex("Male");
			person.print();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
}

