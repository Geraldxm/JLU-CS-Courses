//Demo: object name reuse

public class ObjectNameReuseTest {
    public static void main(String[] args) {

	  int count = 1;
	  SomeClass obj;							//declare an object of SomeClass
	  while(count <=3)
	   {
        obj = new SomeClass(5);		//obj of SomeClass is created and reused inside the loop

        obj.setX(100*count);
        System.out.println("otherObj's x = " + obj.getX());
        count++;
   		}

		obj = new SomeClass(20);	//obj name is reused

		obj.setX(99);

        System.out.println("obj's x = " + obj.getX());

    }
}

