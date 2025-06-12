package employeesys;
import java.util.Calendar;

public class Saler extends Employee {
	private int achievement;

	public Saler(String name, int salary, Calendar hireDay, int achievement) {
		this.name = name;
		this.salary = salary;
		this.hireDay = hireDay;
		this.achievement = achievement;
	}

	public void raiseSalary(double byPercent) {
		salary *= 1 + (byPercent + achievement) / 100;
	}

	public int getAchievement() {
		return achievement;
	}

	public void setAchievement(int achievement) {
		this.achievement = achievement;
	}

	public void print(){
		System.out.println("销售员的姓名为:"+name+",工资为:" +salary+",入职时间为:"+hireDayToString() +",销售业绩为:"+achievement);
	}
}
