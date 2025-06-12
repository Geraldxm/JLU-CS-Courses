package employeesys;
import java.util.Calendar;

public class Manager extends Employee {
	private String secretaryName;// 秘书名字

	public Manager(String name, int salary, Calendar hireDay, String secretaryName) {
		this.name = name;
		this.salary = salary;
		this.hireDay = hireDay;
		this.secretaryName = secretaryName;
	}

	public void raiseSalary(double byPercent) {
		Calendar c = Calendar.getInstance();
		// 奖金
		double bonus = 0.5 * (c.get(Calendar.YEAR) - getHireYear());
		salary *= 1 + (byPercent + bonus) / 100;
	}

	public void setSecretary(String s) {
		secretaryName = s;
	}

	public String getSecretary() {
		return secretaryName;
	}

	public void print() {
		System.out.println("经理的姓名为:" + name + ",工资:" + salary + ",入职时间为:" + hireDayToString() + ",秘书姓为:" + secretaryName);
	}
}
