//Demo£º Modified operation class to compute future value
//using static data and overloaded constructors

import java.text.*;

public class FutureValue {
		private String name;			 //Define instance variables
		private int years;
		private double  monthlyInvest,
			 	yearlyRate,
			 	futureValue;
		private static int count = 0;	//Define static data foe object count
		public static final double TAX_RATE = 0.085;    //and for capital gain tax rate

		public static int getCount() {	//Static methods
			return count;
		}

		public static String getFormattedMessage(FutureValue futureValue) {
			//Currency format
			String investStr = NumberFormat.getCurrencyInstance().format(futureValue.getMonthlyInvest());
			String futureValueStr = NumberFormat.getCurrencyInstance().format(futureValue.getFutureValue());

			//Percent format
			NumberFormat percent = NumberFormat.getPercentInstance();
			percent.setMinimumFractionDigits(2);
			String rateStr = percent.format(futureValue.getYealyRate()/100);

			String message =  "Your Future Value Report\n"
							+ futureValue.getName() + "\n"
							+ investStr + "\n"
							+ rateStr + "\n"
							+ futureValue.getYears() + "\n"
							+ futureValueStr + "\n\n";

			return message;
		}

		public FutureValue () {   //comstructor
			name = "Someone";
			monthlyInvest = 0.0;
			yearlyRate = 0.0;
			years = 0;
			futureValue = 0.0;
			count++;			//Increase user count by 1
		}
		public FutureValue(String name) {  //Overloaded constructors
			this.name = name;
			monthlyInvest = 0.0;
			yearlyRate = 0.0;
			years = 0;
			futureValue = 0.0;
			count++;
		}
		public FutureValue(String name, double monthlyInvest){
			this.name = name;
			this.monthlyInvest = monthlyInvest;
			yearlyRate = 6.5;	//Defualt rate
			years = 1;			//Defualt year
			futureValue = 0.0;
			count++;
		}
		public FutureValue(String name, double monthlyInvest, double yearlyRate) {
			this.name = name;
			this.monthlyInvest = monthlyInvest;
			this.yearlyRate = yearlyRate;
			years = 1;
			futureValue = 0.0;
			count++;
		}
		public FutureValue(String name, double monthlyInvest, double yearlyRate, int years) {
			this.name = name;
			this.monthlyInvest = monthlyInvest;
			this.yearlyRate = yearlyRate;
			this.years = years;
			futureValue = 0.0;
			count++;
		}
		//following are setXx() and getXxx() for class variables
		public void setName(String userName) {
					name = userName;
				}
		public String getName() {
					return name;
				}

		public void setMonthlyInvest(double monthlyInvestment) {
					monthlyInvest = monthlyInvestment;
				}
		public double getMonthlyInvest() {
					return monthlyInvest;
				}
		public void setYearlyRate(double yearlyReturnRate) {
					yearlyRate = yearlyReturnRate;
				}
		public double getYealyRate() {
					return yearlyRate;
				}
		public void setYears(int investYears){
					years = investYears;
				}
		public int getYears() {
					return years;
				}
		public double getFutureValue() {
					return futureValue;
				}
		public void futureValueCompute() {  //Compute future value
					double monthlyReturnRate = yearlyRate/12/100;
					int months = years * 12;
					int i = 1;
					do  {
						futureValue = (futureValue + monthlyInvest) *
									  (1 + monthlyReturnRate);
						i++;
			 		}	while (i <= months);		//End of do-while
		}		   									//End of futureValueCompute()
		public double getTaxCompute() {
			return futureValue * FutureValue.TAX_RATE;
		}
	}	//End of FutureValue
