package cn.jbit.handset.impl;

import cn.jbit.handset.factory.Handset;
import cn.jbit.handset.factory.Network;
import cn.jbit.handset.factory.PlayWiring;
import cn.jbit.handset.factory.TheakePictures;

/**
 * �����ֻ�
 * @author ��������
 *
 */
public class AptitudeHandset extends Handset implements Network, PlayWiring,
		TheakePictures {
	
	public AptitudeHandset(){
		
	}
	
	public AptitudeHandset(String brand,String type){
		super(brand,type);
	}

	@Override
	public void sendInfo() {
		System.out.println("���ʹ�ͼƬ�����ֵ���Ϣ......");	
	}

	@Override
	public void takePicture() {
		System.out.println("����......���ճɹ�");
	}

	@Override
	public void play(String content) {
		System.out.println("��ʼ������Ƶ��"+content+"��......");
	}

	@Override
	public void networkConn() {
		System.out.println("�Ѿ������ƶ�����......");
	}

	@Override
	public void call() {
		System.out.println("��ʼ��Ƶͨ��......");

	}

}
