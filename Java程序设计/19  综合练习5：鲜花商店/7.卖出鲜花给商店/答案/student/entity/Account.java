package com.huawei.classroom.student.entity;

import java.util.Date;

/**
 * 
 * @author 鲜花商店的账户信息类
 *
 */
public class Account {
	/**
	 * 帐单标识符
	 */
	private long id;

	/**
	 * 交易类型，1--代表鲜花商店卖给顾客 2--代表顾客卖给商店 3---顾客之间交易
	 */
	private int dealType;

	/**
	 * 鲜花标识符
	 */
	private long flowerId;

	/**
	 * 卖家标识符
	 */
	private long sellerId;

	/**
	 * 买家标识符
	 */
	private long buyerId;

	/**
	 * 交易价格
	 */
	private double price;

	/**
	 * 交易时间
	 */
	private Date dealTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getDealType() {
		return dealType;
	}

	public void setDealType(int dealType) {
		this.dealType = dealType;
	}

	
	public long getFlowerId() {
		return flowerId;
	}

	public void setFlowerId(long flowerId) {
		this.flowerId = flowerId;
	}

	public long getSellerId() {
		return sellerId;
	}

	public void setSellerId(long sellerId) {
		this.sellerId = sellerId;
	}

	public long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	
}
