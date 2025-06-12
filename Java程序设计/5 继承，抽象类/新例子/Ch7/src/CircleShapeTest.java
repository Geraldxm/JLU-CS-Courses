//Demo: the driver code to test Circle and Sphere 

public class CircleShapeTest {

	public static void main(String[] args) {

 		Circle circle = new Circle(12.98);
 		Sphere sphere = new Sphere(1);

 		Circle myCircle = new Circle(0, 0, 1, 1);
 		System.out.println(myCircle);

 		circle.computeArea();
 		System.out.println("circle area: " + circle.getArea());

 		myCircle.computeRadius();
 		myCircle.computeArea();
 		System.out.println("my circle area: " + myCircle.getArea());

 		sphere.computeArea();
 		System.out.println("Sphere area: " + sphere.getArea());

 		sphere.computeVolume();
 		System.out.println("Sphere volumn: " + sphere.getVolume());
 	}

}