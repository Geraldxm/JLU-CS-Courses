//Demo£º How to define a super class CircleShape

public class CircleShape {
	protected double radius,
					x1, y1, x2, y2;
	public CircleShape() {
		radius = 0.0;
		x1 = y1 = x2 = y2 = 0.0;
	}
	public CircleShape(double radius) {
			this.radius = radius;
		}
		public CircleShape(double x1, double y1, double x2, double y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
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
}
