//Utility class to verify data using JOptionPane
//validation class with verification of double, double with range, integer, integer with range
//and y/n 

import javax.swing.JOptionPane;

public class Validator {
	
	public static double verifyDoubleWithRange(String prompt, double min, double max) {
		boolean isValid = false;
		double data = 0.0;
		String strData = null;

		while(!isValid) {
			  try {
		 		strData = JOptionPane.showInputDialog(prompt);
		 		data = Double.parseDouble(strData);             //throw inputMismatchException

				if (data < min || data > max) 				//less than min
						throw new Exception("Data is out of range " + min + " - " + max);		//Throw the exception
				isValid = true;   //otherwise data is correct
			  }		//End of try
			catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, e + "\nData type incorrect, try again...");
			 }						//End of catch
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, e + "\nTry again...");
			 }						//End of the catch
			} 						//End of while
			return data;
		}	//end of validateDoubleWithRange()

	//Method of validateInt()with range
	public static int verifyIntWithRange(String prompt, int min, int max) {
		boolean isValid = false;
		int data = 0;
		String strData = null;
		 

		 while(!isValid) {
		  try {
	 		strData = JOptionPane.showInputDialog(prompt);
	 		data = Integer.parseInt(strData);             //throw inputMismatchException

			if (data < min || data > max) 				//less than min
					throw new Exception("Data is out of range " + min + " - " + max);		//Throw the exception
			isValid = true;   //otherwise data is correct
		  }		 
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, e + "\nData type incorrect, try again...");
		 }						//End of catch
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e + "\nTry again...");
		 }						//End of the catch
		} 						//End of while
		return data;
	 }	//End of validateIntWithRange()


	public static String validateYN(String prompt) {
		boolean isValid = false; 
		String choice = null;
		
		while (!isValid) {
			try {
				choice = JOptionPane.showInputDialog(prompt);
				
				if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n"))
					isValid = true;
				else
					throw new Exception("Invalid entry and try again...");
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
				continue;
			}
		}   //end of while loop
		return choice;
	}  //end of validateYN()
	
}	   //End of Validator