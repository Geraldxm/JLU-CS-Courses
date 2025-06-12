package cn.pb.manage;

import cn.pb.cars.Bus;
import cn.pb.cars.Car;
import cn.pb.cars.MotoVehicle;

/**
 * ����ҵ��
 */
public class MotoOperation {	
	public MotoVehicle motos[] = new MotoVehicle[8];
	public void init(){
		motos[0] = new Car("��NY28588", "����", "X6",800);
		motos[1] = new Car("��CNY3284", "����", "550i",600);
		motos[2] = new Car("��NT37465", "���", "������",300);
		motos[3] = new Car("��NT96968", "���", "GL8",600);
		motos[4] = new Bus("��6566754", "��", 16,800);
		motos[5] = new Bus("��8696997", "����", 16,800);
		motos[6] = new Bus("��9696996", "��", 34,1500);
		motos[7] = new Bus("��8696998", "����", 34,1500);
	}
	/**
	 * �������� 
	 * @return ����
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
		return moto;//����һ����������
	}
}
