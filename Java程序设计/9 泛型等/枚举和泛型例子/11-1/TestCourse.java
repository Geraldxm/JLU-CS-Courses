package chap11;
enum Level{
	  EASY,MIDDLE,HARD;
}
class Course{
	private String name;
	private Level  level;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}	
}
public class TestCourse {
	public static void main(String args[]) {
		Course c=new Course();
		c.setName("java基础");
		c.setLevel(Level.EASY);
	}
}
