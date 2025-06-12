package cn.jbit.handset.impl;

import cn.jbit.handset.factory.Handset;
import cn.jbit.handset.factory.PlayWiring;

/**
 * ��ͨ�ֻ�
 * @author ��������
 *
 */
public class CommonHandset extends Handset implements PlayWiring {
	
	public CommonHandset(){
		
	}
	
	public CommonHandset(String brand,String type){
		super(brand,type);
	}
	
	@Override
	public void play(String content) {
		System.out.println("��ʼ�������֡�"+content+"��......");
	}

	@Override
	public void sendInfo() {
		System.out.println("����������Ϣ......");
	}

	@Override
	public void call() {
		System.out.println("��ʼ����ͨ��.....");
	}
}
