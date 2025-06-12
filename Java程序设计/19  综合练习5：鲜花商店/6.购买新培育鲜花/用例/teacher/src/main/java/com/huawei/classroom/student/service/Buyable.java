package com.huawei.classroom.student.service;

import com.huawei.classroom.student.entity.Flower;
import com.huawei.classroom.student.entity.FlowerStore;

/**
 * @author  买鲜花接口
 */
public interface Buyable {
	/**
	 * 顾客购买库存鲜花
	 */
	public void buy(Flower flower,FlowerStore store);
	
}
