package cn.pb.cars;

/**
 * �ͳ�
 */
public class Bus extends MotoVehicle {
	private int seatCount;//��λ��

	public int getSeatCount() {
		return seatCount;
	}
	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}
	public Bus(){
		
	}
	public Bus(String vehicleId, String brand,int seatCount,int perRent){
		super(vehicleId, brand,perRent);
		this.seatCount=seatCount;
	}
	
	/**
	 * ��д�������
	 */
	public float calRent(int days) {
		float price=this.getPerRent()*days;
		if(days>=3 && days<7){
			price *= 0.9f;
		}else if(days>=7 && days<30){
			price *= 0.8f;
		}else if(days>=30 && days<150){
			price *= 0.7f;
		}else if(days>=150){
			price*= 0.6f;
		}
		return price;
	}
	
}
