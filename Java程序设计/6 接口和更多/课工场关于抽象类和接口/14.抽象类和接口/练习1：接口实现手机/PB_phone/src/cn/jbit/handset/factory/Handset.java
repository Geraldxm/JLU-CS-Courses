package cn.jbit.handset.factory;

public abstract class Handset {
	private String brand;  //Ʒ��
	private String type;   //�ͺ�
	
	public Handset(){
		
	}
	
	public Handset(String brand,String type){
		this.brand=brand;
		this.type=type;
	}
	
	/**
	 * ����Ϣ
	 */
	public abstract void sendInfo(); 
    /**
     * ��绰
     */
	public abstract void call();
	
	public void info(){
		System.out.println("����һ���ͺ�Ϊ"+type+"��"+brand+"�ֻ�:");
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
