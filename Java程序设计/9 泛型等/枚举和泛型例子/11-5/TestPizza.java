package chap11;

public class TestPizza {
	public static void main(String[] args) {
		Pizza testPz = new Pizza();
	    testPz.setStatus(Pizza.PizzaStatus.READY);
	    testPz.printTimeToDeliver();
	}
}
