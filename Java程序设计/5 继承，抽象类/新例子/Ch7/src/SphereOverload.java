//Demo: method overload in inheritance - subclass Sphere inherits Circle

public class SphereOverload extends Circle{

	private double volume;

	public SphereOverload() {
		super();			//call super class non-argument constructor
	}
	public SphereOverload(double radius) {
		super(radius);		//call super class one-argument constructor
	}
	public SphereOverload(double x1, double y1, double x2, double y2) {
		super(x1, y1, x2, y2);//call super class four-argument constructor
	}

	public void computeArea() {		//compute sphere's area
		super.computeArea();		//call super class' method
		area = 4* area;
	}

	public void computeArea(double radius) {		//method overloading
			setRadius(radius);
			super.computeArea();
			area = 4 * area;
	}

	public void computeVolume() {	//compute sphere's valume
		super.computeArea();
		volume = 4.0/3 * radius * area;
	}

	public double getArea() {
		return area;
	}

	public double getVolume() {
		return volume;
	}
}
