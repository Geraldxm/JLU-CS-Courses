package com.ssdult.eflowerShop.entity;
/**
 * �ʻ�ʵ����
 */
public class Flower {
	/**
	 * �ʻ���ʶ��
	 */
	private long id;
	/**
	 * �ʻ�����
	 */
	private String name;
	/**
	 * �ʻ����
	 */
	private String typeName;
	/**
	 * �ʻ������˿ͱ�ʶ��
	 */
	private int ownerId;
	/**
	 * �ʻ������ʻ��̵��ʶ��
	 */
	private long storeId;
/**
	 * �ʻ��۸�
	 */
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
