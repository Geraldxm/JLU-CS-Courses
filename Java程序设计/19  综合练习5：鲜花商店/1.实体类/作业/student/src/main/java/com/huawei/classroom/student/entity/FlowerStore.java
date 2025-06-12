package com.huawei.classroom.student.entity;
/**
 * 鲜花商店实体类
 * 
 */
public class FlowerStore {
	/**
	 * 鲜花商店id
	 */
	private long id;

	/**
	 * 鲜花商店名称
	 */
	private String name;

	/**
	 * 鲜花商店密码
	 */
	private String password;

	/**
	 * 鲜花商店资金
	 */
	private double balance;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	//TODO:补全name的getset方法

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
