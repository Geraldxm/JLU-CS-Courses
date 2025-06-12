//Demo: Driver code to test object instantiation referencing

public class ObjectCreationReferenceTest { 		 //example showing object creation referencing
    public static void main(String args[]) {

    	SomeClass myObj = SomeClass.getSomeClassInstance();
    	myObj.setX(90);
    	System.out.println(myObj.getX());
	}
}