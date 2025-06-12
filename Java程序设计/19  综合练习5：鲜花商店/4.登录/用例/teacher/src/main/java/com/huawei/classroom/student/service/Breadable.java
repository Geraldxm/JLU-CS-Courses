package com.huawei.classroom.student.service;

import com.huawei.classroom.student.entity.Flower;
import com.huawei.classroom.student.entity.FlowerStore;

/**
 * @author 鲜花培育接口
 * 
 */
public interface Breadable {
	/**
	 * 鲜花繁殖
	 */
	public Flower bread(String flower,FlowerStore store);
}
