package employeesys;
import java.util.*;
abstract public class Employee{
	String name;
	int salary;
	Calendar hireDay;
	abstract public void print();
	abstract public void raiseSalary(double byPercent);
	public int getHireYear(){
		return hireDay.get(Calendar.YEAR);
	}
	public String hireDayToString() {
		return hireDay.get(Calendar.YEAR)+"年"+hireDay.get(Calendar.MONTH)+"月"+hireDay.get(Calendar.DATE)+"日";
	}
}
