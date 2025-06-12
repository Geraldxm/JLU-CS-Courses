package cn.jbit.computer;

/**
 * CPU
 * @author administrator
 *
 */
public interface CPU {	
	/**
	 * 获取CPU品牌
	 * @return 品牌
	 */
	String getCPUBrand(); 
	
	/**
	 * 获取CPU主频
	 * @return 主频
	 */
	Float getFrequency();
}
