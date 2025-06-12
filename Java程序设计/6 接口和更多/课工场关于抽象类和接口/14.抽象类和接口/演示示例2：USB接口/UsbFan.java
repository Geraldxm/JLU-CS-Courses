package cn.jbit.usb;

import cn.jbit.usb.UsbInterface;

/**
 * USB风扇。
 * @author 北大青鸟
 */
public class UsbFan implements UsbInterface {
	public void service() {
		System.out.println("连接USB口，获得电流，风扇开始转动。");
	}
}
