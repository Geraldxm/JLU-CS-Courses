//Demo: use of this
//this means the current object in the execution

class Rectangle {
    private double x, y;
    private double width, height;

    public Rectangle() {
        this(0, 0, 0, 0);		//Call the constructor with 4 arguments
    }
    public Rectangle(int width, int height) {
        this(0, 0, width, height);				//Call the constructor with 4 arguments
    }
   public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
   public String toString() {
	   return ("x: " + x + " y: " + y + " width: " + width + " height: " + height);
   }
}

public class TestThisConstructorApp {
    public static void main(String[] args) {
        Rectangle rec1 = new Rectangle();
        Rectangle rec2 = new Rectangle(20, 30);
        System.out.println(rec1);
        System.out.println(rec2 );
    }
}
