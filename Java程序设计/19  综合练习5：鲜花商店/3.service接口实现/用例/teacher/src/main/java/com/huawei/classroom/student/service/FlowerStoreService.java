package com.huawei.classroom.student.service;

import java.util.List;

import com.huawei.classroom.student.entity.Flower;
import com.huawei.classroom.student.entity.FlowerOwner;
import com.huawei.classroom.student.entity.FlowerStore;



/**
 * @author 鲜花商店接口
 */
public interface FlowerStoreService extends Accountable, Breadable, Buyable, Sellable{
	/**
	 * 查询出所有库存鲜花
	 */
	public List<Flower> getFlowersInstock(long storeId);

	/**
	 * 查询出所有新培育的宠物
	 */
	public List<Flower> getFlowersBread();

	

	/**
	 * 根据顾客信息修改鲜花信息
	 */
	public int modifyFlower(Flower flower, FlowerOwner flowerOwner,FlowerStore store);

	/**
	 * 修改顾客信息
	 */
	public int modifyOwner(FlowerOwner owner, Flower flower, int type);

	/**
	 * 根据鲜花商店标识符查询鲜花商店
	 */
	public FlowerStore getFlowerStore(long id);

	/**
	 * 修改鲜花商店信息
	 */
	public int modifyStore(Flower flower, int type,FlowerStore store);

	/**
	 *鲜花商店登录
	 */
	public FlowerStore login();

	/**
	 * 查询出所有顾客正在出售的鲜花
	 */
	public List<Flower> getFlowerSelling();
}
