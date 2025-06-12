package chap11;

public class TestEnum {
	public static void main(String[] args) {
		//输出Level中所有的成员
		for(Level item:Level.values())
			System.out.println(item.ordinal()+","+item);
		Level level1=Level.EASY;	
		Level level2=Level.valueOf("HARD");
		int compare=level1.compareTo(level2);
		System.out.println(compare);
		Level level3=Level.valueOf("HELLO");//IllegalArgumentException异常
	}
}
