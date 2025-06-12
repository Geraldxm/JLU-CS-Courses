//Demo: how to define an abstract class CircleShape2

public abstract class CircleShape2 extends Shape {		//Inherits Shape
	protected double radius;//半径

public CircleShape2() {
	x1 = y1 = x2 = y2 = 0.0;
	}
public CircleShape2(double x1, double y1, double x2, double y2) {
		super(x1, y1, x2, y2);
	}
public CircleShape2(double radius) {
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
	public String toString() {
		return super.toString() + "Radius: " + radius + "\n";
	}
}

