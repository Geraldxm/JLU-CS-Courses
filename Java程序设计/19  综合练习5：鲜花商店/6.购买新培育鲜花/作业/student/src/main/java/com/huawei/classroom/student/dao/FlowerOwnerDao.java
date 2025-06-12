package com.huawei.classroom.student.dao;

import java.util.List;

import com.huawei.classroom.student.entity.FlowerOwner;



public interface FlowerOwnerDao {

	/**
	 * 查询所有顾客信息
	 */
	public abstract List<FlowerOwner> getAllOwner();

	/**
	 * 更新顾客信息
	 */
	public abstract int updateOwner(String sql, String[] param);

	/**
	 * 根据查询条件查询顾客信息
	 */
	public abstract FlowerOwner selectOwner(String sql, String[] param);

}
