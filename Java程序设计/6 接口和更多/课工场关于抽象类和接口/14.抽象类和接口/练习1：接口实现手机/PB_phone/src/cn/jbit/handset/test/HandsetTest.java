package cn.jbit.handset.test;

import cn.jbit.handset.impl.AptitudeHandset;
import cn.jbit.handset.impl.CommonHandset;

/**
 * �ֻ�������
 * @author yuhua.xue
 *
 */
public class HandsetTest {
	public static void main(String[] args) {
		CommonHandset coHandset=new CommonHandset("���ᰮ����","G502c");
		coHandset.info();
		coHandset.play("��ѩ");
		coHandset.sendInfo();
		coHandset.call();
		System.out.println();
		
		AptitudeHandset aHandset=new AptitudeHandset("I9100","HTC");
		aHandset.info();
		aHandset.networkConn();
		aHandset.play("Сʱ��");
		aHandset.takePicture();
		aHandset.sendInfo();
		aHandset.call();

	}

}
