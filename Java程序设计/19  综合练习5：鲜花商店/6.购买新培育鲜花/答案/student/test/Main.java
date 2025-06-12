package com.huawei.classroom.student.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.huawei.classroom.student.dao.FlowerDao;
import com.huawei.classroom.student.dao.FlowerOwnerDao;
import com.huawei.classroom.student.dao.FlowerStoreDao;
import com.huawei.classroom.student.dao.impl.FlowerDaoImpl;
import com.huawei.classroom.student.dao.impl.FlowerOwnerDaoImpl;
import com.huawei.classroom.student.dao.impl.FlowerStoreDaoImpl;
import com.huawei.classroom.student.entity.Account;
import com.huawei.classroom.student.entity.Flower;
import com.huawei.classroom.student.entity.FlowerOwner;
import com.huawei.classroom.student.entity.FlowerStore;
import com.huawei.classroom.student.service.FlowerOwnerService;
import com.huawei.classroom.student.service.FlowerStoreFactory;
import com.huawei.classroom.student.service.FlowerStoreService;
import com.huawei.classroom.student.service.impl.FlowerOwnerServiceImpl;
import com.huawei.classroom.student.service.impl.FlowerStoreFactoryImpl;
import com.huawei.classroom.student.service.impl.FlowerStoreServiceImpl;

/**
 *  入口类
 * 
 */
public class Main {
	/**
	 * 系统入口方法
	 * @param args
	 */
	public static void main(String[] args) {
		Main.startFlowerShop();
	}

	private static void startFlowerShop() {
		System.out.println("----------------------鲜花商店启动-----------------");
		System.out.println("鲜花信息");
		System.out.println("****************************************************");
		FlowerDao flowerDao = new FlowerDaoImpl();
		List<Flower> flowerList = flowerDao.getAllFlower();
		System.out.println("序号\t" + "鲜花名称\t"+"鲜花品种\t"+"鲜花售价\t"+"");
		for (int i = 0; i < flowerList.size(); i++) {
		Flower flower = flowerList.get(i);
			System.out.println((i + 1)+"\t"+ flower.getName()+"\t"+ flower.getTypeName()+"\t"+ flower.getPrice()+"\t");
		   }
		System.out.println("****************************************************");
        System.out.print("\n");
        System.out.println("顾客信息");
        FlowerOwnerDao ownerDao = new FlowerOwnerDaoImpl();
		List<FlowerOwner> ownerList = ownerDao.getAllOwner();
		System.out.println("****************************************************");
		System.out.println("序号\t" + "顾客姓名\t" );
		for (int i = 0; i < ownerList.size(); i++) {
			FlowerOwner owner = ownerList.get(i);
			System.out.println((i + 1) +"\t"+ owner.getName()+"\t");
		}
		System.out
				.println("****************************************************");
		System.out.print("\n");
		System.out.println("鲜花商店信息");
        System.out.println("****************************************************");
        FlowerStoreDao storeDao = new FlowerStoreDaoImpl();
        List<FlowerStore> storeList = storeDao.getAllStore();
        System.out.println("序号\t" + "鲜花商店名称\t");
        for (int i=0;i<storeList.size();i++) {
	    FlowerStore store = storeList.get(i);
	    System.out.println((i + 1) +"\t"+ store.getName()+"\t");
}
        System.out.println("****************************************************");
        System.out.print("\n");
        Scanner input = new Scanner(System.in);
        System.out.println("请选择输入登录模式，输入1为顾客登录，输入2为鲜花商店登录");
    	boolean type = true;
		String num;
		while (type) {
			num = input.next();
			if ("1".equals(num)) {
				Main.ownerLogin();
				type = false;
			} else if ("2".equals(num)) {
				Main.storeLogin();
				type = false;
			} else {
				System.out.println("输入有误，请按照指定规则输入");
				System.out.println("请选择登录模式，输入1为顾客登录，输入2为鲜花商店登录");
				type = true;
			}
	}
}

	//鲜花商店登录
	private static FlowerStore storeLogin() {
		Scanner input = new Scanner(System.in);
		FlowerStoreService flowerStore = new FlowerStoreServiceImpl();
		FlowerStore store = flowerStore.login();
			System.out.println("您已登录成功，可以进行如下操作");
			StoreChoose(store);
			
		return store;
		
	}

	//创建鲜花商店
	private static FlowerStore createFlowerStore(FlowerStore store) {
		FlowerStoreFactory storeFactory = new FlowerStoreFactoryImpl();
		storeFactory.createFlowerStore();
		IsStoreLogOut(store);
		
		return store;//返回的是鲜花商店创建者
	}

	private static void IsStoreLogOut(FlowerStore store) {
		System.out.println("您是否继续其它操作若是请输入y,退出请按任意键");
		Scanner input = new Scanner(System.in);
		String code=input.next();
		if(code.equals("y"))
		{
			
			StoreChoose( store);
		}
		else
		{
			System.out.println("您已成功退出系统");
		}
	}

	private static void StoreChoose(FlowerStore store) {
		System.out.println("1：购买鲜花");
		System.out.println("2：卖出鲜花");
		System.out.println("3：培育鲜花");
		System.out.println("4：查询待售鲜花");
		System.out.println("5：查看商店结余");
		System.out.println("6：查看商店账目");
		System.out.println("7：开鲜花商店");
		System.out.println("请根据需要执行的操作，选择序号输入，退出请输入0");
		Scanner input = new Scanner(System.in);
		boolean type = true;
		while (type) {
			int num = input.nextInt();
			switch (num) {
			case 0:
				System.out.println("退出成功");
				type = false;
				break;
			case 1:
				Main.storeBuy(store);
				type = false;
				break;
			case 2:
				Main.storeSell(store);
				type = false;
				break;
			case 3:
				Main.storeBread(store);//传入登录的商店
				type = false;
				break;
			case 4:
				Main.queryFlowerStock(store.getId());
				type = false;
				break;
			case 5:
				Main.queryStoreBalance(store);
				type = false;
				break;
			case 6:
				Main.getAccount(store.getId());
				type = false;
				break;
			case 7:
				Main.createFlowerStore(store);
				type = false;
				break;
			default:
				System.out.println("输入有误,请重新输入");
				type = true;
				break;
			}
			}
	}
	

	//获得鲜花商店的资金1代表商店顾客，2顾客卖给商店，3代表顾客之间的交易
	private static FlowerStore getAccount(long id) {
		FlowerStoreService storeService = new FlowerStoreServiceImpl();
		FlowerStore store=storeService.getFlowerStore(id);
		List<Account> list = storeService.account(id);
		for (int i = 0; i < list.size(); i++) {
			Account account = list.get(i);
			String type = null;
			if (1 == account.getDealType()) {
				type = "商店卖给顾客";
			} else if (2 == account.getDealType()) {
				type = "顾客卖给商店";
			} else {
				type = "顾客之间交易";
			}
			System.out.println("第" + (i + 1) + "笔交易,交易类型为：" + type + "，交易金额是:"
					+ account.getPrice());
		}
		IsStoreLogOut(store);
		return store;
	}

	//查询商店余额
	private static FlowerStore queryStoreBalance(FlowerStore store) {
		double balance =store.getBalance();
		System.out.println(store.getName() + "鲜花商店的结余为:" + balance);
		IsStoreLogOut(store);
		return store;
	}

	//查询待售鲜花
	private static FlowerStore queryFlowerStock(long storeId) {
		FlowerStoreService flowerStoreService = new FlowerStoreServiceImpl();
		FlowerStore store=flowerStoreService.getFlowerStore(storeId);
		Flower flower = null;
		List<Flower> flowerList = flowerStoreService.getFlowersInstock(storeId);
		System.out.println("序号\t" + "鲜花名称\t" + "鲜花类型\t" + "鲜花价格\t" );
		for (int i = 0; i < flowerList.size(); i++) {
			flower = flowerList.get(i);
			System.out.println((i + 1) +"\t"+ flower.getName()+"\t"+ flower.getTypeName()+"\t"+ flower.getPrice()+"\t");
		}
		IsStoreLogOut(store);
		return store;
	}

	//鲜花商店培育鲜花
	private static FlowerStore storeBread(FlowerStore store) {
		FlowerStoreService flowerStore = new FlowerStoreServiceImpl();
		Scanner input = new Scanner(System.in);
		System.out.println("请输入要培育鲜花的品种(例如:玫瑰))");
		String flowerType = input.next();
		flowerStore.bread(flowerType,store);
		IsStoreLogOut(store);
		return store;
	}

	//以商店的身份登录，商店向顾客出售鲜花
	private static void storeSell(FlowerStore store) {
		Scanner input = new Scanner(System.in);
		FlowerStoreService flowerStore = new FlowerStoreServiceImpl();
		Flower flower = null;
		List<Flower> flowerList = flowerStore.getFlowersInstock(store.getId());
		System.out.println("-------以下是鲜花商店正在出售的鲜花-------");
		System.out.println("序号\t" + "鲜花名称\t" +  "鲜花类型\t"+"鲜花价格\t");
		for (int i = 0; i < flowerList.size(); i++) {
			flower = flowerList.get(i);
			System.out.println((i + 1) +"\t"+ flower.getName() +"\t"+ flower.getTypeName()+"\t"+ flower.getPrice()+"\t");
		}
		System.out.println("---------请选择要购买的鲜花序号--------");
		boolean type = true;
		while (type) {
			int num = input.nextInt();
			if ((num - 1) < flowerList.size() && (num - 1) >= 0) {
				flower = flowerList.get(num - 1);
				System.out.println("------要卖出的鲜花信息如下------");
				System.out.println("鲜花名称为：" + flower.getName() + " 鲜花类别是："+ flower.getTypeName()+"鲜花价格是:"+flower.getPrice());
				System.out.println("请确认是否卖出，y代表卖出，n代表不卖");
				String code = input.next();
				if (null != code) {
					if ("y".equals(code)) {
						System.out.println("------下面是现有顾客买家，请选择您要卖给买家序号------");
						List<FlowerOwner> ownerList = new ArrayList<FlowerOwner>();
						FlowerOwnerDao ownerDao = new FlowerOwnerDaoImpl();
						ownerList = ownerDao.getAllOwner();
						FlowerOwner flowerOwner = null;
						System.out.println("序号\t" +  "顾客姓名\t");
						for (int i = 0; i < ownerList.size(); i++) {
							flowerOwner = ownerList.get(i);
							System.out.println((i + 1) +"\t"+ flowerOwner.getName()+"\t");
						}
						num = input.nextInt();
						if ((num - 1) < ownerList.size() && (num - 1) >= 0) {
							flowerOwner = ownerList.get(num - 1);
						}
						flower.setOwnerId(flowerOwner.getId());
						flowerStore.sell(flower);
					} else if ("n".equals(code)) {
						System.out
								.println("--------您选择放弃本次交易，希望您再次光顾----------");

					} else {
						System.out.println("--------您的输入有误----------");
					}
				}
				type = true;
			} else {
				System.out.println("输入有误，请按照序号重新输入");
				type = false;
			}
			type = false;// 标识符更改为false，退出系统
		}
		IsStoreLogOut(store);
	}

	//以商店的身份登录，商店找顾客购买鲜花
	private static void storeBuy(FlowerStore store) {
		Scanner input = new Scanner(System.in);
		FlowerStoreService flowerStore = new FlowerStoreServiceImpl();
		Flower flower = null;
		List<Flower> flowerList = flowerStore.getFlowerSelling();
		System.out.println("-------以下是顾客正在出售的鲜花-------");
		System.out.println("序号\t" + "鲜花名称\t" + "鲜花类型\t" + "鲜花价格\t");
		for (int i = 0; i < flowerList.size(); i++) {
			flower = flowerList.get(i);
	        System.out.println((i + 1)+"\t"+ flower.getName()+"\t"+ flower.getTypeName() +"\t"+ flower.getPrice()+"\t" );
		}
		System.out.println("-------请选择要购买哪一种鲜花，并输入选择项的序号-------");
		int num = input.nextInt();
		flower= flowerList.get(num - 1);
		//System.out.println("选中的鲜花信息"+flower.getName()+"id"+flower.getId());
		flowerStore.buy(flower,store);
		IsStoreLogOut(store);
	}

	//顾客登录
	private static FlowerOwner ownerLogin() {
		Scanner input = new Scanner(System.in);
		FlowerOwnerServiceImpl flowerOwner = new FlowerOwnerServiceImpl();
		FlowerOwner Owner = flowerOwner.login();
		boolean reg = true;
		while (reg) {
			if (null == Owner) {
				System.out.println("登录失败，请确认您的用户名和密码后重新输入");
				Owner = flowerOwner.login();
				reg = true;
			} else {
				reg = false;
				System.out.println("登录成功，您可以购买和卖出鲜花，如果您想购买鲜花请输入1，如果想卖出鲜花请输入2");
				System.out.println("1：购买鲜花");
				System.out.println("2：卖出鲜花");
				boolean type = true;
				//----------------------
				 FlowerCirculate(type,input,Owner);
				}
			}
		return Owner;
		}

	 private static boolean FlowerCirculate(boolean type, Scanner input, FlowerOwner owner)
    {
    	while (type) {
			int num = input.nextInt();
			if (1 == num) {
				
				Main.ownerBuy(owner);
				type = false;
			} else if (2 == num) {
				Main.ownerSell(owner);
				type = false;
			} else {
				System.out.println("输入有误,请重新输入");
				type = true;
			}
    	}
    	return type;
    }
	//顾客向商店卖鲜花
	private static void ownerSell(FlowerOwner flowerowner) {
		
	
		}
		

    //顾客找鲜花商店买鲜花
	private static void ownerBuy(FlowerOwner flowerowner) {
		Scanner input = new Scanner(System.in);
		System.out.println("-------请输入选择要购买范围：只需要按照下文要求输入选择项的序号即可--------");
		System.out.println("1：购买库存鲜花");
		System.out.println("2：购买新培育鲜花");
		FlowerStoreService flowerStore = new FlowerStoreServiceImpl();
		FlowerOwnerService flowerOwner = new FlowerOwnerServiceImpl();
		Flower flower = null;
		int num = input.nextInt();
		List<Flower> flowerList = null;

		// num为1时购买库存鲜花
		boolean type = true;
		while (type) {
			if (num == 1) {
				System.out.println(num+"库存鲜花:   -------以下是库存鲜花-------");
				flowerList = flowerStore.getFlowersInstock(0);
			
				System.out.println("序号\t" + "鲜花名称\t" + "鲜花类型\t"+ "鲜花价格\t");
				for (int i = 0; i < flowerList.size(); i++) {
					flower = flowerList.get(i);
				//	double price = flowerStore.charge(flower);// 获得鲜花的价格
					System.out.println((i + 1) +"\t"+ flower.getName() +"\t"+ flower.getTypeName() +"\t"+flower.getPrice()+"\t");
				}
				System.out.println("-------请选择要购买哪一种鲜花，并输入选择项的序号-------");
				num = input.nextInt();
				flower = flowerList.get(num - 1);
				flower.setOwnerId(flowerowner.getId());
				flowerOwner.FlowerOwnerbuy(flower);
				type = false;
				
				// num为2时购买新培育鲜花
			} else if (num == 2) {
				System.out.println(num+"培育鲜花:   -------以下是库存鲜花-------");
				System.out.println("序号\t" + "鲜花名称\t"+ "鲜花类型\t" + "鲜花价格\t");
				flowerList = flowerStore.getFlowersBread();
				for (int i = 0; i < flowerList.size(); i++) {
					flower = flowerList.get(i);
				
					System.out.println((i + 1)+"\t"+ flower.getName() +"\t"+ flower.getTypeName() +"\t"+ flower.getPrice()+"\t");
				}
				System.out.println("-------请选择要购买哪一种鲜花，并输入选择项的序号-------");
				String count = input.next();
				if (count.matches("[0-9]*")) {
					num = Integer.parseInt(count);
					flower = flowerList.get(num - 1);
					flower.setOwnerId(flowerowner.getId());
					flowerOwner.FlowerOwnerbuy(flower);
				}
				type = false;
			} else {
				System.out.println("您的输入有误，请按照上诉提示输入");
				type = true;
			}
		
		}
		boolean isLogOut=true;
			System.out.println("您是否要继续进行其它操作，若是请输入Y,否则输入任意字母退出系统");
			String flag=input.next();
			if(flag.equals("Y"))
			{
				System.out.println("您可以购买和卖出鲜花，如果您想购买鲜花请输入1，如果想卖出鲜花请输入2");
				System.out.println("1：购买鲜花");
				System.out.println("2：卖出鲜花");
				//循环
				FlowerCirculate(isLogOut,input,flowerowner);
			}
			else
			{
				System.out.println("您已成功退出系统");
				
			}
		
		}
	}