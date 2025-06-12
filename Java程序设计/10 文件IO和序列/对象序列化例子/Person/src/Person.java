import java.io.Serializable ;
public class Person implements Serializable{
	private String name ;		// 定义name属性	
	private int age ;			// 定义age属性
	public Person(String name,int age){
		this.name = name; 
		this.age = age ;
	}
	public String toString(){
		return "姓名：" + this.name + "；年龄：" + this.age ;
	}
	public void setName(String name){
		this.name = name ;
	}
	public void setAge(int age){
		this.age = age ;
	}
	public String getName(){
		return this.name ;
	}
	public int getAge(){
		return this.age ;
	}
};