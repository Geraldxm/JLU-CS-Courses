//Demo: method overload in inheritance - subclass CircleOverload

public class CircleOverload extends CircleShape{
	protected double area;

	public CircleOverload() {
		super();			//call super class non-argument constructor
	}
	public CircleOverload(double radius) {
		super(radius);		//call super class one-argument constructor
	}
	public CircleOverload(double x1, double y1, double x2, double y2) {
		super(x1, y1, x2, y2);//call super class four-argument constructor
	}

	public void computeArea() {
		area = Math.PI * radius * radius;
	}

	public double getArea() {
		return area;
	}

	//methoding overloading with CircleShape class
	public void computeRadius(double x1, double y1, double x2, double y2) {
				radius = Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2) * (y1 - y2));
		}
}
