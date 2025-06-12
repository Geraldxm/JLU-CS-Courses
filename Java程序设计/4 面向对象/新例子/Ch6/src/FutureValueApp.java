//Use constructor overloading£¬ static data and static methods
//to modify the future value return application 

import java.util.*;

public class FutureValueApp {
public static void main(String[] args) {

		String choice = "y",
			   userName;
		double monthlyInvestment,
		       yearlyReturnRate;
		int    investYears;
		final int minYear = 1,
				  maxYears = 150;

	Scanner sc = new Scanner(System.in);

	//Create object using a variety of overloaded constructors
	FutureValue noNameFutureValue = new FutureValue();
		noNameFutureValue.futureValueCompute();
	FutureValue noInvestFutureValue = new FutureValue("John");
		noInvestFutureValue.futureValueCompute();
	FutureValue noRateFutureValue = new FutureValue("Wang", 1000);
		noRateFutureValue.futureValueCompute();
	FutureValue noYearsFutureValue = new FutureValue("Liu", 2000, 9.85);
		noYearsFutureValue.futureValueCompute();
	FutureValue myFutureValue = new FutureValue("Gao", 1590, 10.28, 25);
		myFutureValue.futureValueCompute();

	//Call static formatted methods
	System.out.println(FutureValue.getFormattedMessage(noNameFutureValue));
	System.out.println(FutureValue.getFormattedMessage(noInvestFutureValue));
	System.out.println(FutureValue.getFormattedMessage(noRateFutureValue));
	System.out.println(FutureValue.getFormattedMessage(noYearsFutureValue));
	System.out.println(FutureValue.getFormattedMessage(myFutureValue));


	while(choice.equalsIgnoreCase("y")) {

		FutureValue futureValue = new FutureValue();

		System.out.print("Please enter your name: ");
		userName = sc.next();
		futureValue.setName(userName);				//Set name
		sc.nextLine();

		monthlyInvestment = Validator.validateDoubleWithRange(sc, "Enter your monthly invest amount: ", 0.0, 1000000.0);
		futureValue.setMonthlyInvest(monthlyInvestment);

		yearlyReturnRate = Validator.validateDoubleWithRange(sc, "Enter your interest rate: ", 0.0, 30.0);
		futureValue.setYearlyRate(yearlyReturnRate);

		investYears = Validator.validateIntWithRange(sc, "Enter your invest years: ", minYear, maxYears);
		futureValue.setYears(investYears);

		futureValue.futureValueCompute();

		//Display the return
		System.out.println(FutureValue.getFormattedMessage(futureValue));

		choice = Validator.validateYN(sc, "continue? (y/n): ");	//ask for continue or no
 	} //End of while

 	System.out.println("Number of objects: " + FutureValue.getCount() + "\n\n" ); //Call static method¡§

 	System.out.println("Good bye!");
  } //End of main()
} //End of FutureValueApp