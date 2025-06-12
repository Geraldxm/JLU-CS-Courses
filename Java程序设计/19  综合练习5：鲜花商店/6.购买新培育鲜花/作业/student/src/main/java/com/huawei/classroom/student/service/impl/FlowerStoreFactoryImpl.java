package com.huawei.classroom.student.service.impl;

import java.util.Scanner;

import com.huawei.classroom.student.dao.FlowerStoreDao;
import com.huawei.classroom.student.dao.impl.FlowerStoreDaoImpl;
import com.huawei.classroom.student.service.FlowerStoreFactory;

/**
 * @author 鲜花商店工程实现类
 */
public class FlowerStoreFactoryImpl implements FlowerStoreFactory {

	/**
	 * 创建鲜花商店
	 */
	@Override
	public void createFlowerStore() {
		Scanner input = new Scanner(System.in);
		System.out.println("*************请在下面输入鲜花商店属性**************");
		System.out.println("请输入您创建的鲜花商店名字：");
		String storeName = input.nextLine();
		System.out.println("请输入您创建的鲜花商店的密码：");
		String storePassword = input.nextLine();
		System.out.println("请输入您创建的鲜花商店的资金：");
		String flowerBalance = input.nextLine();
		String sql = "insert into flowerstore(`name`,`password`,balance) values(?,?,?)";
		Object[] param = { storeName, storePassword, flowerBalance };
		FlowerStoreDao storeDao = new FlowerStoreDaoImpl();
		int count = storeDao.updateStore(sql, param);
		if (count > 0) {
			System.out.println("您已成功创建了一个鲜花商店，商店名字叫" + storeName);
		}
	}

	}


