package com.huawei.classroom.student.entity;


/**
 * 鲜花实体类
 * 
 */
public class Flower {
	/**
	 * 鲜花标识符
	 */
	private long id;

	/**
	 * 鲜花名称
	 */
	private String name;

	/**
	 * 鲜花类别
	 */
	private String typeName;


	/**
	 * 鲜花所属顾客标识符
	 */
	private int ownerId;

	/**
	 * 鲜花所属鲜花商店标识符
	 */
	private long storeId;

	private double price;
	
	//TODO:补齐getset方法
}
