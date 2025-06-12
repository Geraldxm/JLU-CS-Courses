//Demo:  Method overloading

import java.util.*;
import java.text.*;

public class MethodOverload {

	//Use of system default currency format
	public void printFormattedCurrency(double amount) {
		String out = NumberFormat.getCurrencyInstance().format(amount);
		System.out.println("System default currency format: " + out);
	}
	//Use of user-defined currency format
	public void printFormattedCurrency(double amount, Locale locale) {
		String out = NumberFormat.getCurrencyInstance(locale).format(amount);
		System.out.println("User-defined locale " + locale + ", amount:" + out);
	}
	//Use of user-defined currency format with control of decimals
	public void printFormattedCurrency(double amount, Locale locale, int decimal) {
		NumberFormat currency = NumberFormat.getCurrencyInstance(locale);
		currency.setMinimumFractionDigits(decimal);
		String out = currency.format(amount);
		System.out.println("User-defined locale " + locale + ", format with "+ decimal + ", amount:" + out);
    }
}
