//Demo£º driver code to test Object and Class class

public class ObjectClassTest{

	public static void main(String[] args) {
 		Circle circle1 = new Circle();
 		Sphere sphere = new Sphere();

 		Circle circle2 = circle1;		//referencing
 		Circle circle3 = new Circle();	//creating another object of Circle

 		if (circle1.equals(circle2))
 			System.out.println("Two objects are equal\n" + circle1 + " equals " + circle2);
 		else
 			System.out.println("Two objects are not equal\n" + circle1 + " not equal " + circle2);

 		if (circle1.equals(circle3))
		 	System.out.println("Two objects are equal\n" + circle1 + " equals " + circle3);
		else
 			System.out.println("Two objects are not equal\n" + circle1 + " not equal " + circle3);

 		if (circle1.equal("Circle"))
 			System.out.println("it's a Circle object");
 		else
 			System.out.println("it's not a Circle object");

 		MyClass.printClassName(circle1);

 		Class theClass = null;

 		theClass = circle1.getClass();		//return the class
 		System.out.println("Class name of circle: " + theClass.getName());

 		if (circle1.getClass().getName().equals("Sphere"))
 			System.out.println("it's a Sphere object");
 		else
 			System.out.println("it's not a Sphere object");

		try {
		      theClass = Class.forName("Sphere");
		      System.out.println(MyClass.getInheritanceTree(theClass));
		    }

		 catch(ClassNotFoundException e){
		       System.out.println(e);
	     }

	}
}

