//Demo:  Use of overriding equals() method

public class OverridingEqualsTest {

		public static void main(String[] args) {

			Circle circle1 = new Circle();
			if (circle1.equal("Circle"))
			 	System.out.println("It's a Circle object.");
			 else
			 	System.out.println("It's not a Circle object.");

	 		Circle circle2 = new Circle(10.09);
	 		Circle circle3 = new Circle(10.09);
	 		Circle circle4 = new Circle(0, 0, 1, 1);

	 		if (circle2.equals(circle3))
	 		  System.out.println("Two objects are the same.");
			else
			  System.out.println("Two objects are not the same.");
	 		System.out.println("circle4 data: " + circle4);
		}
}
