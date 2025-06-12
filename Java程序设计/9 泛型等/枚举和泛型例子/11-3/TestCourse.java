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
	public void showLevel() {
		//使用swtich语句实现
		switch(level) {
			case EASY:System.out.println("适合于初学者"); break;
			case MIDDLE:System.out.println("适合于有一定基础的学生");break;
			case HARD:System.out.println("适合于高级水平的学生");break;
		}
		//使用==实现
		if(level==Level.EASY)
			System.out.println("适合与初学者");
		else if(level==Level.MIDDLE)
			System.out.println("适合于有一定基础的学生");
		else if(level==level.HARD)
		System.out.println("适合于高级水平的学生");
	}
}
public class TestCourse {
	public static void main(String args[]) {
		Course c=new Course();
		c.setName("java基础");
		c.setLevel(Level.EASY);
	}
}
