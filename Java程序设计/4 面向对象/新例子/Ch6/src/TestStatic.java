//Demo:  Operation class Using static methods and data

public class TestStatic{
	private static int objCount = 0;

	public TestStatic() {		//Constructor to increase objCount
		objCount++;
	}

	public static int getObjCount() {  //Static method to return the objCount
		return objCount;
	}
	public static double sqr(double num) {  //Static method to compute square
		return num * num;
	}
}
