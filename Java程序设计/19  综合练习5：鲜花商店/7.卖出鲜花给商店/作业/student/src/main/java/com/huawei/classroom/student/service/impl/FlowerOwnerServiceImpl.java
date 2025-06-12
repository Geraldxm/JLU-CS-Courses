package com.huawei.classroom.student.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.huawei.classroom.student.entity.Flower;
import com.huawei.classroom.student.entity.FlowerOwner;
import com.huawei.classroom.student.entity.FlowerStore;
import com.huawei.classroom.student.service.FlowerOwnerService;
import com.huawei.classroom.student.service.FlowerStoreService;

/**
 * 
 * @author 鲜花顾客实现类
 *
 */
public class FlowerOwnerServiceImpl implements FlowerOwnerService{
	/**
	 * 顾客购买库存鲜花，根据用户控制台输入获得到的序号，来实际调用购买库存鲜花或者购买新培育的鲜花
	 */
	@Override
	public void sell(Flower flower) {
		FlowerDaoImpl flowerDao = new FlowerDaoImpl();
		FlowerOwnerDaoImpl ownerDao = new FlowerOwnerDaoImpl();
		String updatesql = "update flower set store_id=?,owner_id=NUll  where id=?";
		Object[] param = {flower.getStoreId(), flower.getId() };
		int updateFlower = flowerDao.executeSQL(updatesql, param);// 更新鲜花信息

		if (updateFlower > 0) {// 更新顾客的信息
			String ownersql = "select * from flowerowner where id=?";
			String ownerparam[] = { String.valueOf(flower.getOwnerId()) };

			FlowerOwner owner = ownerDao.selectOwner(ownersql, ownerparam);
			String updateOwnerSql = "update flowerowner set money=? where id=?";
			Object[] ownerParam = { (owner.getMoney() + flower.getPrice()), owner.getId() };
			int updateOwner = ownerDao.executeSQL(updateOwnerSql, ownerParam);
			if (updateOwner > 0) {// 更新鲜花商店的信息
				FlowerStoreServiceImpl store = new FlowerStoreServiceImpl();
				FlowerStore flowerStore = store.getFlowerStore(flower.getStoreId());
				//TODO:购买鲜花后更新鲜花商店的余额
				if (updatestore > 0) {// 更新鲜花商店台帐信息
					String insertsql = "insert into account(deal_type,flower_id,seller_id,buyer_id,price,deal_time) values (?, ?, ?, ?, ?, ?)";
					String date = new SimpleDateFormat("yyyy-MM-dd")
							.format(new Date());
					Object[] accountParam = { 2, flower.getId(), owner.getId(),
							flower.getStoreId(), flower.getPrice(), date };
					//TODO:增加账单信息
					//TODO:增加账单信息
					if (insertAccount > 0) {
						System.out.println("您已成功卖出鲜花："+flower.getName()+"获得收入"+flower.getPrice());
					}
				}
			}
		}
		
	}

	@Override
	public void FlowerOwnerbuy(Flower flower) {
		String sql = "select * from flowerowner where id=?";
		String param[] = { String.valueOf(flower.getOwnerId()) };
		FlowerOwnerDao ownerDao = new FlowerOwnerDaoImpl();
		FlowerOwner owner = ownerDao.selectOwner(sql, param);
		String sql1 = "select * from flowerStore where id=?";
		String param1[] = { String.valueOf(flower.getStoreId()) };
		FlowerStoreDao storeDao=new FlowerStoreDaoImpl();
		FlowerStore store = storeDao.getFlowerStore(sql, param1);	
		FlowerStoreService flowerStore = new FlowerStoreServiceImpl();
		int updateFlower = flowerStore.modifyFlower(flower, owner, null);// 更新鲜花信息
		if (updateFlower > 0) {// 更新顾客的信息
			int updateOwner = flowerStore.modifyOwner(owner, flower, 0);
			if (updateOwner > 0) {// 更新鲜花商店的信息
				int updateStore = flowerStore.modifyStore(flower, 0,store);
				if (updateStore > 0) {// 更新鲜花商店帐户信息
					int insertAccount = flowerStore.modifyAccount(flower, owner);
					if (insertAccount > 0) {
						System.out.println("您已成功购买价格为"+flower.getPrice()+"的"+flower.getName());
					}
				}
			}
		}
		
	}

	/**
	 *顾客登录
	 */
	@Override
	public FlowerOwner login() {
		Scanner input = new Scanner(System.in);
		// 1、输入顾客姓名
		System.out.println("请先登录，请您输入姓名：");
		String ownerName = input.nextLine().trim();
		System.out.println("请您输入密码：");
		String ownerPassword = input.nextLine().trim();
		FlowerOwnerDao ownerDao = new FlowerOwnerDaoImpl();
		String sql = "select * from flowerowner where name=? and password=?";
		String[] param = { ownerName, ownerPassword };
		FlowerOwner owner = ownerDao.selectOwner(sql, param);
		if (null != owner) {
			System.out.println("-------恭喜您成功登录-------");
			System.out.println("-------您的基本信息：-------");
			System.out.println("名字：" + owner.getName());
			System.out.println("资金：" + owner.getMoney());
		}
		return owner;
	}
	/**
	 * 
	 * 根据顾客标识符（id）获得到该顾客所有鲜花信息
	 */
	@Override
	public List<Flower> getMyFlower(int ownerId) {
		List<Flower> flowerList = new ArrayList<Flower>();
		String sql = "select * from flower where owner_id=?";
		String[] param = { String.valueOf(ownerId) };
		FlowerDao petDao = new FlowerDaoImpl();
		flowerList = petDao.selectFlower(sql, param);
		return flowerList;
	}

}
