package com.huawei.classroom.student.service;

import com.huawei.classroom.student.entity.Flower;

/**
 *  鲜花工厂接口
 */
public interface FlowerFactory {
	/**
	 * 培育新品种宠物
	 */
	public Flower breadNewFlower(String[] petParam);
}
