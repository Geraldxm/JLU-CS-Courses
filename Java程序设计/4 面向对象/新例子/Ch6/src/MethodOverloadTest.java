//Demo: driver code to test the MethodOverloadTest

import java.util.*;

public class MethodOverloadTest {
	public static void main(String[] args) {

 	MethodOverload test = new MethodOverload();	//Create object

 	test.printFormattedCurrency(19.722345);				//Method call with one argument

 	test.printFormattedCurrency(19.722345, Locale.US);	//with two arguemtns

 	test.printFormattedCurrency(19.722345, Locale.FRANCE, 4);	//with three arguments


	}
}

