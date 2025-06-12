public class Operate{
	public static void add(){	// 增加操作
		InputData input = new InputData() ;		// 实例化输入数据对象
		FileOperate fo = new FileOperate("d:\\test.per") ;
		String name = input.getString("请输入姓名：") ;
		int age = input.getInt("请输入年龄：" , "年龄必须是数字！") ;
		Person per = new Person(name,age) ;	// 实例化Person对象
		try{
			fo.save(per) ;	// 保存对象
		}catch(Exception e){
			e.printStackTrace() ;
		}
		System.out.println("信息增加成功！") ;
	}
	public static void delete(){	// 删除操作
		FileOperate fo = new FileOperate("d:\\test.per") ;
		try{
			fo.save(null) ;	// 保存对象
		}catch(Exception e){
			e.printStackTrace() ;
		}
		System.out.println("信息删除成功！") ;
	}
	public static void update(){	// 更新操作
		InputData input = new InputData() ;		// 实例化输入数据对象
		FileOperate fo = new FileOperate("d:\\test.per") ;
		Person per = null ;
		try{
			per = (Person)fo.load() ;	// 读取对象
		}catch(Exception e){
			e.printStackTrace() ;
		}
		String name = input.getString("请输入姓名（原姓名："+per.getName()+"）：") ;
		int age = input.getInt("请输入年龄（原年龄："+per.getAge()+"）：" , "年龄必须是数字！") ;
		per = new Person(name,age) ;	// 实例化Person对象
		try{
			fo.save(per) ;	// 保存对象
		}catch(Exception e){
			e.printStackTrace() ;
		}
		System.out.println("信息修改成功！") ;
	}
	public static void find(){	// 查看操作
		FileOperate fo = new FileOperate("d:\\test.per") ;
		Person per = null ;
		try{
			per = (Person)fo.load() ;	// 读取对象
		}catch(Exception e){
			e.printStackTrace() ;
		}
		System.out.println(per) ;
	}
};