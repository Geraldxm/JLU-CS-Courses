package cn.jbit.computer.impl;

import cn.jbit.computer.EMS;

/**
 * ��ʿ�ٳ���
 * @author administrator
 *
 */
public class JSD_EMS implements EMS {

	@Override
	public String getEMSType() {
		return "DDR3";
	}
	
	@Override
	public int getSize() {
		return 4;
	}

}
