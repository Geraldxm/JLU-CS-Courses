package cn.jbit.computer.test;

import cn.jbit.computer.CPU;
import cn.jbit.computer.EMS;
import cn.jbit.computer.HardDisk;

public class Computer {
	CPU cpu;
	EMS ems;
	HardDisk hardDisk;
	
	public Computer(CPU cpu,EMS ems,HardDisk hardDisk){
		this.cpu=cpu;
		this.ems=ems;
		this.hardDisk=hardDisk;
	}
	
	public void showInfo(){
		System.out.println("���������Ϣ���£�");
		System.out.println("CPU��Ʒ���ǣ�"+cpu.getCPUBrand()+"����Ƶ�ǣ�"+cpu.getFrequency()+"GHz");
		System.out.println("Ӳ�������ǣ�"+hardDisk.getCapacity()+"GB");
		System.out.println("�ڴ������ǣ�"+ems.getSize()+"GB");
	}

}
