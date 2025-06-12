//operational subclass Circle2 inherited from CircularShape2

public class Circle2 extends CircularShape2{
	protected double area;

	public Circle2() {
		super();			//call super class non-argument constructor
	}
	public Circle2(double radius) {
		super(radius);		//call super class one-argument constructor
	}
	public Circle2(double x1, double y1, double x2, double y2) {
		super(x1, y1, x2, y2);//call super class four-argument constructor
	}

	public void computeArea() {
		area = Math.PI * radius * radius;
	}

	public double getArea() {
		return area;
	}

	public void computeVolume() {
		volume = 0.0;				//Circle doesn't have volume
	} 					 


  	public String toString() {					//override the toString() method
		 return super.toString() + "Area: " + area + "\n";
 	}
}
