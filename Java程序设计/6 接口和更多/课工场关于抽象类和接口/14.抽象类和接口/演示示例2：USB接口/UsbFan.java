package cn.jbit.usb;

import cn.jbit.usb.UsbInterface;

/**
 * USB���ȡ�
 * @author ��������
 */
public class UsbFan implements UsbInterface {
	public void service() {
		System.out.println("����USB�ڣ���õ��������ȿ�ʼת����");
	}
}
