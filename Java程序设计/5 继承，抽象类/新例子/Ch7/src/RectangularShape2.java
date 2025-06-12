//Project computing geographic shapes
//abstract class RectangulareShape2 inherits Shape

public abstract class RectangularShape2 extends Shape {
	protected double height, length;//height  高度
	protected double area;
	protected double volume;

public RectangularShape2 (double x1, double y1, double x2, double y2) {
		super(x1, y1, x2, y2);		//Code reusable
		computeHeight();
		computeLength();
	}
	public RectangularShape2(double height, double length) {
		this.height = height;
		this.length = length;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getHeight() {
		return height;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getLeangth() {
		return length;
	}
	public void computeHeight() {
		double height = Math.abs(x1 - x2);
		setHeight(height);
	}
	public void computeLength() {
		double length = Math.abs(y1 - y2);
		setLength(length);
	}
	public abstract void computeArea();         //define abstract method
	
	public double getArea() {
		return area;
	}
	public abstract void computeVolume(); 		//define abstract method

	public String toString() {
		String message = super.toString() + "Height: " + height + "\n"
								+ "Length: " + length + "\n";
		return message;
	}
}
