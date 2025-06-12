package chap11;

public class SuperWildCardDemo {
	//统计容器中未被使用的空间个数，使用通配符
	public static  int countNullElements1(GenericContainer<?> container) {
		int count=0;
		for(int i=0;i<container.getSize();i++) {
			if(container.get(i)==null)
				count++;
		}
		return count;
	}
	//统计容器中未被使用的空间个数，使用上限通配符
	public static   int countNullElements2(GenericContainer<? extends ChildClass> container) {
			int count=0;
			for(int i=0;i<container.getSize();i++) {
				if(container.get(i)==null)
					count++;
			}
			return count;
		}
	//统计容器中未被使用的空间个数，使用下限通配符
	public static   int countNullElements3(GenericContainer<? super ChildClass> container) {
		int count=0;
		for(int i=0;i<container.getSize();i++) {
			if(container.get(i)==null)
				count++;
		}
		return count;
	}
	public static void main(String[] args) {
	   //定义3个容器对象
		//创建存放父类元素的容器
		GenericContainer<SuperClass> supercontainer=new GenericContainer<SuperClass>(5);
		//创建存放子类元素的容器
		GenericContainer<ChildClass> childcontainer=new GenericContainer<ChildClass>(3);
	 	//创建存放子类的子类元素的容器
		GenericContainer<GrandChildClass> grandchildcontainer=new GenericContainer<GrandChildClass>(2);
	     //调用countNullElements1，统计3个容器的未被使用的空间个数
		System.out.println(countNullElements1(supercontainer));
		System.out.println(countNullElements1(childcontainer));
		System.out.println(countNullElements1(grandchildcontainer));
	    //调用countNullElements2，统计3个容器的未被使用的空间个数
		//	System.out.println(countNullElements2(supercontainer));//语法错误
		System.out.println(countNullElements2(childcontainer));
		System.out.println(countNullElements2(grandchildcontainer));
		//调用countNullElements1，统计3个容器的未被使用的空间个数
		System.out.println(countNullElements3(supercontainer));
		System.out.println(countNullElements3(childcontainer));
		//	System.out.println(countNullElements3(grandchildcontainer));//语法错误
			
		}
}
