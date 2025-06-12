//Demo: How to define a subclass class Circle

public class Circle extends CircleShape{
	protected double area;

	public Circle() {
		super();			//call super class non-argument constructor
	}
	public Circle(double radius) {
		super(radius);		//call super class one-argument constructor
	}
	public Circle(double x1, double y1, double x2, double y2) {
		super(x1, y1, x2, y2);//call super class four-argument constructor
		super.computeRadius();
	}

	public void computeArea() {
		area = Math.PI * radius * radius;
	}

	public double getArea() {
		return area;
	}
	//added method for ObjectClassTest to see if the object belongs to the class
	public boolean equal(String className) {
    	if (this.getClass().getName().equals(className))
    	  return true;
	 	else
    		return false;
	}
	 public boolean equals(Object object) {	//override the equals() method
		 
		
		 if (object instanceof Circle) {	//if two objects are in the same class
			 Circle circle6 = (Circle) object;
			 if (radius == circle6.getRadius()) 	//two objects are the same
				return true;
		 }
		 return false;
	}
	 public String toString() {	//override the toString() method
		 if (x1 == 0.0 && y1 == 0.0 && x2 == 0.0 && y2 == 0.0 && radius != 0.0) //only for radius
		  		return ("radius: " + radius + "\n");
		  else {
			 String message = "(" + x1 + "," + y1 + "), ("
			 					  + x2 + "," + y2 + ")\t"
			 					  + "radius: " + radius + "\n";
			 return message;
	 	  }
	 }
}