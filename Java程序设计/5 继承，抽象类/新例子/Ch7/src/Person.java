//Demo: how to define an abstract class

public abstract class Person {

	protected String lastName, firstName;
	protected char sex;
	protected byte age;
	protected String address;

	public Person(String lastName, String firstName, char sex, byte age, String address) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.sex = sex;
		this.age = age;
		this.address = address;
	}
	public String toString() {
		String message = "Last name: " + lastName + ", " + "first name: " + firstName + "\n"
						+ "Sex: " + sex + "\n"
						+ "Age: " + age + "\n"
						+ "Address: " + address + "\n";
		 return message;
		}
}

abstract class Employee extends Person {
	protected String employeeID;
	protected String jobTitle;
	protected byte seniority;
	protected double salary;

	public Employee(String lastName, String firstName, char sex, byte age,
					String address, String employeeID, String jobTitle, byte seniority) {
		super(lastName, firstName, sex, age, address);
		this.jobTitle = jobTitle;
		this.seniority = seniority;
	}
	public abstract void computeSalary();		//abstract method
	public String toString() {					//override toString()
		String message = super.toString() + "EmployeeID: " + employeeID + "\n"
						+ "jobTitle: " + jobTitle + "\n"
						+ "Seniority: " + seniority + "\n";
		return message;
	}
}