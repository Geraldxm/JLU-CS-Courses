package cn.myApp.exception;


/**
 * ����throw�׳����쳣��
 * @author administrator
 */
public class TestException2{
	public static void main(String[] args) {
		Person person = new Person();
		try {
			person.setAge(110);
			person.print();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
}
