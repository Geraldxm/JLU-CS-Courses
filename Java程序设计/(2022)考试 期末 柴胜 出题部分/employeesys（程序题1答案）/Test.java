package employeesys;
import java.util.Calendar;
public class Test {
	public static void main(String[] args) {
		Calendar c1 = Calendar.getInstance();
		c1.set(2010, 1, 1);
		Worker worker = new Worker("张工", 3000,c1);
		worker.print();
		worker.raiseSalary(10);
		worker.print();
		Calendar c2 = Calendar.getInstance();
		c2.set(2015, 5, 5);
		Saler saler = new Saler("王销售", 3000, c2,0);
		saler.print();
		saler.setAchievement(20);
		saler.raiseSalary(10);
		saler.print();
		Calendar c3 = Calendar.getInstance();
		c3.set(2018, 8, 8);
		Manager manager = new Manager("刘经理", 4000, c3,"XX行政秘书");
		manager.print();
		manager.raiseSalary(20);
		manager.print();
	}

}
