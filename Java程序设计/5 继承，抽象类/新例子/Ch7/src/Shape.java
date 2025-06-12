//Demo: how to define an abstract superclass Shape

public abstract class Shape {
	protected double x1, y1, x2, y2;
	public Shape() {
		x1 = y1 = x2 = y2 = 0.0;
	}
	public Shape(double x1, double y1, double x2, double y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

	public String toString() {
		String message = "(" + x1 + "," + y1 + "), ("
		 		+ x2 + "," + y2 + ")\n";
		 return message;
		 }

	public abstract void computeArea();		//abstract methods
	public abstract void computeVolume();
}
