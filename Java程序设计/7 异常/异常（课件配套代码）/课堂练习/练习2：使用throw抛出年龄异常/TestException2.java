package cn.myApp.exception;


/**
 * 捕获throw抛出的异常。
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
