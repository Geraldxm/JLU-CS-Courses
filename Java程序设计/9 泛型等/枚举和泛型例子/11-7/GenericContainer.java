package chap11;

public class GenericContainer<T> {
	private Object t[];
	private int size;//容器大小
	private int length;//容器中元素个数
	public GenericContainer(int size) {
		this.size=size;
		t=new Object[size];
		length=0;
	}
	public T get(int index) {
		return (T)t[index];
	}
	public void add(T value) {
		t[length]=value;
		length++;
	}
	public int getSize() {
		return size;
	}
}
class SuperClass{
	void f1() {System.out.println("f1");}
}
class ChildClass extends SuperClass{
	void f2() {System.out.println("f2");}
}
class GrandChildClass extends ChildClass{
	void f3() {System.out.println("f3");}
}