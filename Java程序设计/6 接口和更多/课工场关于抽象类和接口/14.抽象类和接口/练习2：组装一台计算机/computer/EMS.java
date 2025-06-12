package cn.jbit.computer;

/**
 * 内存
 * @author administrator
 *
 */
public interface EMS {
	/**
	 * 获取内存类型
	 * @return 类型
	 */
	String getEMSType();
	
	/**
	 * 获取内存容量大小
	 * @return 容量大小
	 */
	int getSize();
}
