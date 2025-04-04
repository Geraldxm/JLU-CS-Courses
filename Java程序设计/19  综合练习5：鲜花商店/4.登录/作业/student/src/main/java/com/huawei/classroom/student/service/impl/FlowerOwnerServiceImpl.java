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
		
		
	}

	@Override
	public void FlowerOwnerbuy(Flower flower) {}

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
