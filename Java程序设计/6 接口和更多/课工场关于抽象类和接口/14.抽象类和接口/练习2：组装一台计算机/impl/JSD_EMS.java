package cn.jbit.computer.impl;

import cn.jbit.computer.EMS;

/**
 * 金士顿厂商
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
