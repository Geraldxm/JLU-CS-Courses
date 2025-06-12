package chap11;

public class ContainerTest {
	public static void main(String[] args) {
		 Container c=new Container(10);
		 c.add("zhangsan"); //添加一个String类型的元素
		 c.add(1);//添加一个Integer类型的元素
		 c.add(3.5); //添加一个Double类型的元素
		 String s1=(String)c.get(0);
		 System.out.println(s1.contains("zhang"));
	}
}
