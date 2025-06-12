package employeesys;
import java.util.Calendar;

public class Worker extends Employee{
	public Worker(String name,int salary,Calendar hireDay){
		this.name=name;
		this.salary=salary;
		this.hireDay=hireDay;
	}
	public void print(){
		System.out.println("工人的姓名为:"+name+",工资为:"+salary +",入职时间为:"+hireDayToString());
	}
	public void raiseSalary(double byPercent){
		salary*=1+byPercent/100;
	}
}
