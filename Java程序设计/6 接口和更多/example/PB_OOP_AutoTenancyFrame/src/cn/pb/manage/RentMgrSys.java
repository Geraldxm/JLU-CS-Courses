package cn.pb.manage;
import java.util.Scanner;

import cn.pb.cars.MotoVehicle;

/**
 * �������޹�����
 */
public class RentMgrSys {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		MotoOperation motoMgr=new MotoOperation();
		motoMgr.init();
		MotoVehicle moto=null;//����
		System.out.println("***********��ӭ�����ڷ��������޹�˾***********");
		System.out.println("1���γ� \t2���ͳ�");		
		System.out.print("��ѡ����Ҫ���޵��������ͣ�");		
		int motoType=input.nextInt();
		String brand="";
		String type="";
		int seat=0;
		if(motoType==1){
			System.out.print("��ѡ����Ҫ���޵�����Ʒ�ƣ�1����� 2������");	
			int choose2=input.nextInt();
			if(choose2==1){
				brand="���";
				System.out.print("��ѡ����Ҫ���޵��������ͣ�1�������� 2��GL8");
				type=(input.nextInt()==1)?"������":"GL8";
			}else if(choose2==2){
				brand="����";
				System.out.print("��ѡ����Ҫ���޵��������ͣ�1��X6  2��550i");
				type=(input.nextInt()==1)?"X6":"550i";
			}
		}else if(motoType==2){
			type="";
			System.out.print("��ѡ����Ҫ���޵�����Ʒ�ƣ�1������ 2����");	
			brand=(input.nextInt()==1)?"����":"��";
			System.out.print("��ѡ����Ҫ���޵�������λ����1��16�� 2��34��");
			seat=(input.nextInt()==1)?16:34;
		}
		moto=motoMgr.motoLeaseOut(brand,type,seat);//��� 
		System.out.print("��������Ҫ���޵�������");
		int days=input.nextInt();
		double money=moto.calRent(days);//���޷���
		System.out.println("��������������ƺ��ǣ�"+moto.getVehicleId());
		System.out.println("����Ҫ֧�������޷����ǣ�"+money+"Ԫ��");
	}
}
