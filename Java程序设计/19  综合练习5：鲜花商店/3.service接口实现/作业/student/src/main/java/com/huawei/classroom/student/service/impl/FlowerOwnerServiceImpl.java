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
		FlowerOwner owner = new FlowerOwner();
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
		//TODO:根据顾客id获得到该顾客所有鲜花信息
	}

}
