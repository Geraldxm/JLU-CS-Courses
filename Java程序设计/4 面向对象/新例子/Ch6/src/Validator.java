//Example of validation class with verification of double, double with range, integer, integer with range
//and y/n using Scanner's methods and try-catch mechanism

import java.util.Scanner;

public class Validator {
	
	public static double validateDoubleWithRange(Scanner sc, String prompt, double min, double max) {
		boolean isValid = false;
		double data = 0.0;

		while(!isValid) {
			try {
				   System.out.print(prompt);

				   if (!sc.hasNextDouble())     //Not a double					   												
						throw new NumberFormatException("\nData input error.  Please enter a double type data...");	 
						data = sc.nextDouble();	//Receive double

						if (data < min || data > max)		//not in the right range
					       throw new Exception("Data is out of range " + min + " - " + max); //throw the error message
						isValid = true;				
			 }								//End of try

			catch (NumberFormatException e) {
				System.out.println(e);
				sc.nextLine();		//Clear buffer
			 }						//End of catch
			catch (Exception e) {
				System.out.println(e);
				sc.nextLine();
			 }
			} //End of while
			return data;
		}	//end of validateDoubleWithRange()

	//Method of validateInt()with range
	public static int validateIntWithRange(Scanner sc, String prompt, int min, int max) {
		boolean isValid = false;
		int data = 0;
		sc.nextLine();								//Clear the buffer

		 while(!isValid) {
		  try {
	 		System.out.print(prompt);
			if (!sc.hasNextInt())
				throw new NumberFormatException("\nData input error.  Please enter an integer type data...");
			data = sc.nextInt();

			if (data < min || data > max) 				//less than min
					throw new Exception("Data is out of range " + min + " - " + max);		//Throw the exception
			isValid = true;   //otherwise data is correct
		  }		//End of try
		catch (NumberFormatException e) {
			System.out.println(e);
			sc.nextLine();		//Clear buffer
		 }						//End of catch
		catch (Exception e) {
			System.out.println(e);
			sc.nextLine();
		 }
		} 						//End of while
		return data;
	 }	//End of validateIntWithRange()


	public static String validateYN(Scanner sc, String prompt) {
		boolean isValid = false; 
		String choice = "";
		while (!isValid) {
			try {
				System.out.println(prompt);
				choice = sc.next(); 
				if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n"))
					isValid = true;
				else
					throw new Exception("Invalid entry and try again...");
			}
			catch (Exception e) {
				System.out.println(e);
				sc.nextLine();
				continue;
			}
		}   //end of while loop
		return choice;
	}  //end of validateYN()
	
}	   //End of Validator