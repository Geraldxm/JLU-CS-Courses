import java.io.Serializable ;
public class Person implements Serializable{
	private String name ;		// ����name����	
	private int age ;			// ����age����
	public Person(String name,int age){
		this.name = name; 
		this.age = age ;
	}
	public String toString(){
		return "������" + this.name + "�����䣺" + this.age ;
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