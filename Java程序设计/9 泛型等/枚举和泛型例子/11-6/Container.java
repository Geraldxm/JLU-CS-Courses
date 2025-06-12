package chap11;

public class Container {
	private Object t[]; //容器元素的数组
	private int size;//容器大小
	private int length;//容器中元素个数
	public Container(int size) {
		this.size=size;
		t=new Object[size];
		length=0;
	}
	public Object get(int index) { //得到一个元素
		return t[index];
	}
	public void add(Object value) { //添加一个元素
		t[length]=value;
		length++;
	}
}
