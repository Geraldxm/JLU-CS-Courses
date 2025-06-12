package com.huawei.classroom.student.dao;

import java.util.List;

import com.huawei.classroom.student.entity.FlowerStore;





public interface FlowerStoreDao {

	/**
	 * 查询出所有鲜花商店
	 */
	public abstract List<FlowerStore> getAllStore();

	/**
	 * 根据查询条件查询出鲜花商店
	 */
	public abstract FlowerStore getFlowerStore(String sql, String[] param);

	/**
	 * 更新鲜花商店信息
	 */
	public abstract int updateStore(String sql, Object[] param);

}
