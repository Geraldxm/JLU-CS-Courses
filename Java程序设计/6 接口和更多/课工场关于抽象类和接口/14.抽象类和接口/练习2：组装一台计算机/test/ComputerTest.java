package cn.jbit.computer.test;

import cn.jbit.computer.CPU;
import cn.jbit.computer.EMS;
import cn.jbit.computer.HardDisk;
import cn.jbit.computer.impl.IntelCPU;
import cn.jbit.computer.impl.JSD_EMS;
import cn.jbit.computer.impl.XJ_HardDisk;

/**
 * 测试类
 * @author 北大青鸟
 *
 */
public class ComputerTest {

	public static void main(String[] args) {
		/*创建组件对象*/
		CPU cpu=new IntelCPU();
		EMS ems=new JSD_EMS();
		HardDisk hardDisk=new XJ_HardDisk();
		//创建一台计算机对象
		Computer computer=new Computer(cpu, ems, hardDisk);
		computer.showInfo();
	}
}
