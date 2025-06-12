package cn.jbit.handset.factory;

public abstract class Handset {
	private String brand;  //品牌
	private String type;   //型号
	
	public Handset(){
		
	}
	
	public Handset(String brand,String type){
		this.brand=brand;
		this.type=type;
	}
	
	/**
	 * 发信息
	 */
	public abstract void sendInfo(); 
    /**
     * 打电话
     */
	public abstract void call();
	
	public void info(){
		System.out.println("这是一款型号为"+type+"的"+brand+"手机:");
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
