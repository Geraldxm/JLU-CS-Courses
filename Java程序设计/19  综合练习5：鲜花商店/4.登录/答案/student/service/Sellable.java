package com.huawei.classroom.student.service;

import com.huawei.classroom.student.entity.Flower;

/**
 *卖鲜花接口
 */
public interface Sellable {
	/**
	 * 卖鲜花
	 */
	public void sell(Flower flower);
}
