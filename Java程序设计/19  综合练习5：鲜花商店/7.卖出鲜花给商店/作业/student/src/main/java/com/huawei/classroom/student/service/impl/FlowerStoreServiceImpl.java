package com.huawei.classroom.student.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.huawei.classroom.student.dao.AccountDao;
import com.huawei.classroom.student.dao.FlowerDao;
import com.huawei.classroom.student.dao.FlowerOwnerDao;
import com.huawei.classroom.student.dao.FlowerStoreDao;
import com.huawei.classroom.student.dao.impl.AccountDaoImpl;
import com.huawei.classroom.student.dao.impl.FlowerDaoImpl;
import com.huawei.classroom.student.dao.impl.FlowerOwnerDaoImpl;
import com.huawei.classroom.student.dao.impl.FlowerStoreDaoImpl;
import com.huawei.classroom.student.entity.Account;
import com.huawei.classroom.student.entity.Flower;
import com.huawei.classroom.student.entity.FlowerOwner;
import com.huawei.classroom.student.entity.FlowerStore;
import com.huawei.classroom.student.service.FlowerStoreService;

/**
 * 鲜花商店实现类
 */
public class FlowerStoreServiceImpl implements FlowerStoreService{

	/**
	 * 查询鲜花商店账目 其中的1代表鲜花商店卖给顾客,2代表顾客卖给商店，代表鲜花商店的各种交易
	 */
	@Override
	public List<Account> account(long storeId) {
		String sql = "select * from account where deal_type=? and seller_id=? union select * from account where deal_type=? and buyer_id=?";
		String[] param = { "1", String.valueOf(storeId), "2",
				String.valueOf(storeId) };
		AccountDao accountDao = new AccountDaoImpl();
		List<Account> list = accountDao.getFlowerStoreAccount(sql, param);
		return list;
	}
	/**
	 * 修改鲜花商店资金信息
	 */
	@Override
	public int modifyAccount(Flower flower, FlowerOwner owner) {
		String insertsql = "insert into account(deal_type,flower_id,seller_id,buyer_id,price,deal_time) values (?, ?, ?, ?, ?,?)";
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Object[] accountParam = { 1, flower.getId(), flower.getStoreId(),owner.getId(), flower.getPrice(), date };
		AccountDao accountDao = new AccountDaoImpl();
		int insertAccount = accountDao.updateAccount(insertsql, accountParam);
		return insertAccount;
	}
	/**
	 * 培育鲜花
	 */
	@Override
	public Flower bread(String flowerType,FlowerStore store) {
		Scanner input = new Scanner(System.in);
       System.out.println("请输入您期望培育的鲜花名字：");
		String flowerName = input.nextLine();
		System.out.println("请输入您期望的鲜花价格：");
		String flowerprice = input.nextLine();
		String storeId=String.valueOf(store.getId());
	   String[] flowerParam = { flowerName, flowerType, storeId,flowerprice};
		FlowerFactoryImpl flowerFactory = new FlowerFactoryImpl();
		Flower flower = flowerFactory.breadNewFlower(flowerParam);
        String sql = "insert into flower(`name`,typeName,store_id,price) values(?,?,?,?)";
        Object[] param = { flower.getName(), flower.getTypeName(),
				flower.getStoreId(),flower.getPrice() };
		FlowerDao flowerDao = new FlowerDaoImpl();
		int count = flowerDao.updateFlower(sql, param);
		if (count > 0) {
			System.out.println(store.getName()+"成功培育了一种" + flower.getTypeName() + "鲜花");
		}
		return flower;	
	}
	/**
	 * 鲜花商店购买鲜花
	 */
	@Override
	public void buy(Flower flower,FlowerStore oldstore) {
		String sql = "select * from flowerStore where id=?";
		String paramStore[] = { String.valueOf(oldstore.getId()) };
		FlowerStoreDao storeDao = new FlowerStoreDaoImpl();
		FlowerStore store = storeDao.getFlowerStore(sql, paramStore);	
		FlowerOwnerDao ownerDao = new FlowerOwnerDaoImpl();
	sql = "select * from flowerOwner where id = ?";
		String paramOwner[] = { String.valueOf(flower.getOwnerId()) };
		FlowerOwner owner = ownerDao.selectOwner(sql, paramOwner);
		int updateFlower = modifyFlower(flower, null, store);// 更新鲜花信息
		if (updateFlower > 0) {// 更新顾客的信息
			int updateOwner = modifyOwner(owner, flower, 1);
			if (updateOwner > 0) {// 更新鲜花商店的信息
				int updateStore = modifyStore(flower, 1,store);
				if (updateStore > 0) {// 更新鲜花商店信息
					int insertAccount = modifyAccount(flower, owner);
					if (insertAccount > 0) {
						System.out.println(store.getName()+"已成功购买鲜花:"+flower.getName()+"价格为"+flower.getPrice());
					}
				}
			}
		} else {
			System.out.println("修改鲜花信息失败");
		}
	}
	/**
	 * 商店卖鲜花
	 */
	@Override
	public void sell(Flower flower) {
		FlowerDaoImpl FlowerDao = new FlowerDaoImpl();
		FlowerStoreDaoImpl storeDao = new FlowerStoreDaoImpl();
		FlowerOwnerDaoImpl ownerDao = new FlowerOwnerDaoImpl();
		FlowerStoreService FlowerStore = new FlowerStoreServiceImpl();
		String updatesql = "update Flower set store_id = null ,owner_id=? where id=?";
		Object[] param = { flower.getOwnerId(), flower.getId() };
		int updateFlower = FlowerDao.executeSQL(updatesql, param);// 更新鲜花信息
		if (updateFlower > 0) {// 更新顾客的信息
			String ownersql = "select * from Flowerowner where id=?";
			String ownerparam[] = { String.valueOf(flower.getOwnerId()) };

			FlowerOwner owner = ownerDao.selectOwner(ownersql, ownerparam);
			String updateOwnerSql = "update Flowerowner set money=? where id=?";
			double count =flower.getPrice();
			Object[] ownerParam = { (owner.getMoney() - count), owner.getId() };
			int updateOwner = ownerDao.executeSQL(updateOwnerSql, ownerParam);
			if (updateOwner > 0) {// 更新鲜花商店的信息
				FlowerStore store = FlowerStore.getFlowerStore(flower.getStoreId());
				String updateStore = "update Flowerstore set balance=? where id=?";
				Object[] storeParam = { (store.getBalance() + count),
						store.getId() };
				int updatestore = storeDao.executeSQL(updateStore, storeParam);
				if (updatestore > 0) {// 更新鲜花商店账户的信息
					String insertsql = "insert into account(deal_type,Flower_id,seller_id,buyer_id,price,deal_time) values (?, ?, ?, ?, ?, ?)";
					String date = new SimpleDateFormat("yyyy-MM-dd")
							.format(new Date());
					Object[] accountParam = {1, flower.getId(), owner.getId(),flower.getStoreId(),count, date };
					AccountDao accountDao = new AccountDaoImpl();
					int insertAccount = accountDao.updateAccount(insertsql,accountParam);
					if (insertAccount > 0) {
						System.out.println(store.getName()+"已成功卖出价格为"+flower.getPrice()+"的鲜花"+flower.getName());
					}
				}
			}
		}
	}
	/**
	 * 查询出所有库存鲜花
	 */
	@Override
	public List<Flower> getFlowersInstock(long storeId) {
		FlowerDao flowerDao = new FlowerDaoImpl();
		 String[] param = { String.valueOf(storeId) };
		String sql = "";
		// 当storeId不为0时，要执行查询指定商店库存鲜花
		if(storeId!=0){
			 sql = "select * from flower where owner_id is null and store_id=?";			
		}	
		// 当storeId为0时，要执行查询所有商店的库存鲜花
		if (0 == storeId) {
			sql = "select * from flower where owner_id is null";
			param = null;
		}
		List<Flower> flowerList = flowerDao.selectFlower(sql, param);
		return flowerList;
	}

	/**
	 * 查询出所有新培育的鲜花,
	 */
	@Override
	public List<Flower> getFlowersBread() {
		FlowerDao FlowerDao = new FlowerDaoImpl();
		String sql = "SELECT * FROM flower WHERE owner_id IS NULL AND typeName NOT IN (?,?,?) AND store_id IS Not NULL";
		String[] flowerParam = { "香槟玫瑰", "白玫瑰","粉玫瑰" };
		List<Flower> flowerList = FlowerDao.selectFlower(sql, flowerParam);
		return flowerList;
	}

	
	/**
	 * 根据顾客信息修改鲜花信息 根据FlowerOwnerEntity和FlowerStoreEntity的值判断是顾客买鲜花或者鲜花商店买鲜花
	 * FlowerOwnerEntity=null是鲜花商店买鲜花，FlowerStoreEntity=null是顾客买鲜花
	 */
	@Override
	public int modifyFlower(Flower flower, FlowerOwner flowerOwner, FlowerStore store) {
		String updatesql = null;
		long id = 0;
		if (null == store) {
			updatesql = "update flower set owner_id=? where id=?";
			id = flowerOwner.getId();
		} else if (null == flowerOwner) {
			updatesql = "update flower set store_id=?,owner_id=null where id=?";
			id = store.getId();
		}
		
		Object[] param = { id, flower.getId() };
		FlowerDaoImpl flowerDao = new FlowerDaoImpl();
		int updateFlower = flowerDao.executeSQL(updatesql, param);// 更新鲜花信息
		
		return updateFlower;
	}
	/**
	 * 修改顾客信息 type=0是顾客买鲜花，type=1是鲜花商店买鲜花，价钱的变动
	 */
	@Override
	public int modifyOwner(FlowerOwner owner, Flower flower, int type) {
		FlowerOwnerDaoImpl ownerDao = new FlowerOwnerDaoImpl();
		String updateOwnerSql = "update flowerowner set money=? where id=?";

		double count = 0;
		if (0 == type) {
			count = (owner.getMoney() -flower.getPrice());
		}
		if (1 == type) {
			count = (owner.getMoney() + flower.getPrice());
		}
		Object[] ownerParam = { count, owner.getId() };
		int updateOwner = ownerDao.executeSQL(updateOwnerSql, ownerParam);
		return updateOwner;
	}


	/**
	 * 修改鲜花商店信息 type=0是顾客买鲜花，type=1是鲜花商店买鲜花
	 */
	@Override
	public int modifyStore(Flower flower, int type,FlowerStore oldstore) {
		FlowerStoreService store = new FlowerStoreServiceImpl();
		FlowerStore flowerStore = store.getFlowerStore(oldstore.getId());
		String updateStore = "update flowerstore set balance=? where id=?";
	
		double count = 0;
		if (0 == type) {
			count = (flowerStore.getBalance() + flower.getPrice());
		}
		if (1 == type) {
			count = (flowerStore.getBalance() - flower.getPrice());
		}
		Object[] storeParam = { count, oldstore.getId() };
		FlowerStoreDaoImpl storeDao = new FlowerStoreDaoImpl();
		int updatestore = storeDao.executeSQL(updateStore, storeParam);
		return updatestore;
	}

	/**
	 * 鲜花商店登录
	 */
	@Override
	public FlowerStore login() {
		Scanner input = new Scanner(System.in);
		FlowerStore flowerStore = null;
		// 1、输入鲜花商店名字
		boolean type = true;
		while (type) {
			System.out.println("请先登录，请输入鲜花商店名字：");
			String storeName = input.nextLine().trim();
			System.out.println("请输入鲜花商店的密码：");
			String storePassword = input.nextLine().trim();
			flowerStore = subLogin(storeName, storePassword);
			if (null != flowerStore) {
				System.out.println("-------恭喜成功登录-------");
				System.out.println("-------鲜花商店的基本信息：-------");
				System.out.println("名字：" + flowerStore.getName());
				System.out.println("资金：" + flowerStore.getBalance());
				type = false;
			} else {
				System.out.println("登录失败，请确认您的用户名和密码是否正确,重新登录");
				type = true;
			}
		}
		return flowerStore;
	}
	/**
	 * 需测试用例测试的核心方法
	 * @param storeName
	 * @param storePassword
	 * @return
	 */
	public FlowerStore subLogin(String storeName, String storePassword) {
		FlowerStore flowerStore;
		FlowerStoreDao storeDao = new FlowerStoreDaoImpl();
		String sql = "select * from flowerstore where name=? and password=?";
		String[] param = { storeName, storePassword };
		flowerStore = storeDao.getFlowerStore(sql, param);
		return flowerStore;
	}
	/**
	 * 查询出所有鲜花商店正在出售的鲜花
	 */
	@Override
	public List<Flower> getFlowerSelling() {
		FlowerDao flowerDao = new FlowerDaoImpl();
		String sql = "select * from flower where owner_id is not null";
		String[] flowerParam = null;
		List<Flower> flowerList = flowerDao.selectFlower(sql, flowerParam);
		return flowerList;
	}
	/**
	 * 根据鲜花商店标识符查询鲜花信息
	 */
	@Override
	public FlowerStore getFlowerStore(long id) {
		String sql = "select * from flowerstore where id=" + id;
		FlowerStoreDao storeDao = new FlowerStoreDaoImpl();
		FlowerStore flowerStore = storeDao.getFlowerStore(sql, null);
		return flowerStore;
	}

}
