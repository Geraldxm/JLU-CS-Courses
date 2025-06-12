//Demo: use of static initialization block

import java.util.Scanner;

public class StaticBlockTest1 { //example that shows how static block works

    public static void main(String args[]) {

	   StaticBlock1.setup();
       StaticBlock1.displayTaxRate(); // call method
	}
}

class StaticBlock1 { //demo
	public static double taxRate[] = new double[5];  //create array to hold the static data
        	static {  //static block
					Scanner sc = new Scanner(System.in);
					for(int i = 0; i < 5; i++) {
						 System.out.print("Eneter the tax rate for County " + i + ": ");
						 taxRate[i] = sc.nextDouble();
						 System.out.println();
				     }
					sc.close();
				}   //end of static block
	public static void setup() {
		System.out.println("Begin static data initialization...");
	}


	public static void displayTaxRate() { //static method

		 for (int i = 0; i < 5; i++) {
			 System.out.println(StaticBlock1.taxRate[i]);
		}
     }
}