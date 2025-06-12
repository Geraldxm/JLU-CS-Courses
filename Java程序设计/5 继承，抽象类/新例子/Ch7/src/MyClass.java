//Demo: supporting class for use of Object class and Class class

public class MyClass {

    public static void printClassName(Object object) {
    	System.out.println("The class of " + object +
	                   " is " + object.getClass().getName());
     }

    public static String getInheritanceTree(Class aClass){  //Reference to generic type Class<T> will be discussed in late
    StringBuilder superclasses = new StringBuilder();
    superclasses.append( "\n");
    Class theClass = aClass;
    while ( theClass != null ) {
       superclasses.append( theClass );
       superclasses.append( "\n" );
       theClass = theClass.getSuperclass();
    }
    superclasses.append( "\n" );
    return superclasses.toString();
  }
}
