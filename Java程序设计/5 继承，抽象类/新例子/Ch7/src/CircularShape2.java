
//Project computing geographic objects
//define an abstract class CircularShape2 inherited from Shape

public abstract class CircularShape2 extends Shape {		//Inherits Shape
	protected double radius;
	protected double area;
	protected double volume;

public CircularShape2() {
	x1 = y1 = x2 = y2 = 0.0;
	}
public CircularShape2(double x1, double y1, double x2, double y2) {
		super(x1, y1, x2, y2);
	}
public CircularShape2(double radius) {
	 	this.radius = radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public double getRadius() {
		return radius;
	}
	public void computeRadius() {
		radius = Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2) * (y1 - y2));
	}
	public abstract void computeArea();   	//abstract method
	
	public abstract void computeVolume();  	//abstract method 
	public String toString() {
		return super.toString() + "Radius: " + radius + "\n";
	}
}

