package cn.jbit.computer.test;

import cn.jbit.computer.CPU;
import cn.jbit.computer.EMS;
import cn.jbit.computer.HardDisk;
import cn.jbit.computer.impl.IntelCPU;
import cn.jbit.computer.impl.JSD_EMS;
import cn.jbit.computer.impl.XJ_HardDisk;

/**
 * ������
 * @author ��������
 *
 */
public class ComputerTest {

	public static void main(String[] args) {
		/*�����������*/
		CPU cpu=new IntelCPU();
		EMS ems=new JSD_EMS();
		HardDisk hardDisk=new XJ_HardDisk();
		//����һ̨���������
		Computer computer=new Computer(cpu, ems, hardDisk);
		computer.showInfo();
	}
}
