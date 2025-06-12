package cn.jbit.usb;

import cn.jbit.usb.UsbInterface;

/**
 * U盘。
 * @author 北大青鸟
 */
public class UDisk implements UsbInterface {
	public void service() {
		System.out.println("连接USB口，开始传输数据。");
	}
}
