//Project computing geographic objects
//operational subclass - Sphere2 inherited from Circle2

public class Sphere2 extends Circle2{

	private double volume;

	public Sphere2() {
		super();			//call super class non-argument constructor
	}
	public Sphere2(double radius) {
		super(radius);		//call super class one-argument constructor
	}
	public Sphere2(double x1, double y1, double x2, double y2) {
		super(x1, y1, x2, y2);//call super class four-argument constructor
	}

	public void computeArea() {		//compute sphere's area
		super.computeArea();		//call super class' method
		area = 4* area;
	}

	public void computeVolume() {	//compute sphere's volume
		super.computeArea();		//code reusable
		volume = 4.0/3 * radius * area;
	}

	public double getArea() {
		return area;
	}

	public double getVolume() {
		return volume;
	}
	
	public String toString() {					//override the toString() method
		 return super.toString() + "Volume: " + volume + "\n";
	}
}

