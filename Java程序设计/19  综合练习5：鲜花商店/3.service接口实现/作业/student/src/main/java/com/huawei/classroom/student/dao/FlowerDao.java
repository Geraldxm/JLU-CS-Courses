package com.huawei.classroom.student.dao;

import java.util.List;

import com.huawei.classroom.student.entity.Flower;



public interface FlowerDao {
	/**
	 * 查询所有鲜花信息
	 */
	public abstract List<Flower> getAllFlower();

	/**
	 * 根据已知鲜花的信息查询鲜花信息
	 */
	public abstract List<Flower> selectFlower(String sql, String[] param);

	/**
	 * 更新鲜花信息
	 */
	public abstract int updateFlower(String sql, Object[] param);
}
