package cn.myApp.exception;

/**
 * 捕获throw抛出的异常。
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

