public class Menu{
	public Menu(){
		while(true){
			this.show() ;		// 无限制调用菜单的显示
		}
	}
	public void show(){
		System.out.println("===== Xxx系统 =====") ;
		System.out.println("    [1]、增加数据") ;
		System.out.println("    [2]、删除数据") ;
		System.out.println("    [3]、修改数据") ;
		System.out.println("    [4]、查看数据") ;
		System.out.println("    [0]、系统退出\n") ;
		InputData input = new InputData() ;
		int i = input.getInt("请选择：","请输入正确的选项！") ;
		switch(i){
			case 1:{
				Operate.add() ;		// 调用增加操作
				break ;
			}
			case 2:{
				Operate.delete() ;	// 调用删除操作
				break ;
			}
			case 3:{
				Operate.update() ;	// 调用更新操作
				break ;
			}
			case 4:{
				Operate.find() ;		// 调用查看操作
				break ;
			}
			case 0:{
				System.exit(1) ;		// 系统退出
				break ;
			}
			default:{
				System.out.println("请选择正确的操作！") ;
			}
		}
	}
};