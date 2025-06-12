package cn.jbit.computer.impl;

import cn.jbit.computer.CPU;

/**
 * Intel≥ß…Ã
 * @author administrator
 *
 */
public class IntelCPU implements CPU {

	@Override
	public String getCPUBrand() {
		return "Intel";
	}

	@Override
	public Float getFrequency() {
		return 3.8f;
	}

}
