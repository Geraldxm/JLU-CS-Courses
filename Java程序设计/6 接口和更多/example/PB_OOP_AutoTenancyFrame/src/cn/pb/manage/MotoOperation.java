package cn.pb.manage;

import cn.pb.cars.Bus;
import cn.pb.cars.Car;
import cn.pb.cars.MotoVehicle;

/**
 * 汽车业务
 */
public class MotoOperation {	
	public MotoVehicle motos[] = new MotoVehicle[8];
	public void init(){
		motos[0] = new Car("京NY28588", "宝马", "X6",800);
		motos[1] = new Car("京CNY3284", "宝马", "550i",600);
		motos[2] = new Car("京NT37465", "别克", "林荫大道",300);
		motos[3] = new Car("京NT96968", "别克", "GL8",600);
		motos[4] = new Bus("京6566754", "金杯", 16,800);
		motos[5] = new Bus("京8696997", "金龙", 16,800);
		motos[6] = new Bus("京9696996", "金杯", 34,1500);
		motos[7] = new Bus("京8696998", "金龙", 34,1500);
	}
	/**
	 * 租赁汽车 
	 * @return 汽车
	 */
	public MotoVehicle motoLeaseOut(String brand,String type,int seat){
		MotoVehicle moto=null;
		for (MotoVehicle mymoto : motos) {
			if(mymoto instanceof Car){
				Car car=(Car)mymoto;
				if(car.getBrand().equals(brand)&&car.getType().equals(type)){
					moto=car;
					break;
				}
			}else{
				Bus bus=(Bus)mymoto;
				if(bus.getBrand().equals(brand)&&bus.getSeatCount()==seat){
					moto=bus;
					break;
				}
			}
		}
		return moto;//返回一个汽车对象
	}
}
