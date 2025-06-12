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
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
