package cn.com.myApp;

class Person{
	private String name="";			//姓名
	private int age=0;				//年龄
	private String sex="男";		//性别
	 //设置性别
	public void setSex(String sex) throws GenderException {
		  if ("男".equals(sex) || "女".equals(sex))
		    this.sex = sex;
		  else {
		    throw new GenderException("性别必须是“男”或者“女”！");
		  }
	}

	//打印基本信息
	public void print(){
		System.out.println(this.name+"（"+this.sex+"，"+this.age+"岁）");
	}
}
public class Test{
	public static void main(String[] args){
		Person person = new Person();
		  try {
		    person.setSex("Male");
		    person.print();
		  } catch (GenderException e) {
		    e.printStackTrace();
		  }

	}
}








