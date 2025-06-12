//Project computing geographic objects
//The driver class for testing

import javax.swing.JOptionPane;

public class ShapeApp2{

	public static void main(String[] args) {
		
		String choice = null;
		int verifiedChoice = 0;
		final double min = 0.0, max = 1.7E308;   //define data range
		
		while (verifiedChoice != 3) {
			choice = "Pease enter yourchoice: \n"
 						+ "1. compute rectangular objects\n"
 						+ "2. compute circular objects\n"
 						+ "3. quit\n";
			verifiedChoice = Validator.verifyIntWithRange(choice, 1, 3);
		
		if (verifiedChoice == 1) {		//rectangular computing
			double height = Validator.verifyDoubleWithRange("Please enter the height", min, max);
			double length = Validator.verifyDoubleWithRange("Please enter the length", min, max);
			Rectangle2 rec = new Rectangle2(height, length);
			rec.computeArea();
			JOptionPane.showMessageDialog(null, rec);	
		}
		else if (verifiedChoice == 2) {	//circular shapes computing including sphere objects
			choice = "Pease enter yourchoice: \n"
						+ "1. compute circle objects\n"
						+ "2. compute sphere objects\n"
						+ "3. quit\n";
			verifiedChoice = Validator.verifyIntWithRange(choice, 1, 3);
			
			if (verifiedChoice == 1)  {   	//circle object computing
				
				double radius = Validator.verifyDoubleWithRange("Please enter the circleradius", min, max);
				Circle2 circle = new Circle2(radius);
				circle.computeArea();
				JOptionPane.showMessageDialog(null, circle);
			}
			else if (verifiedChoice == 2){ //sphere object computing	
				double radius = Validator.verifyDoubleWithRange("Please enter the sphere radius", min, max);
				Sphere2 sphere = new Sphere2(radius);
				sphere.computeArea();
				sphere.computeVolume();
				JOptionPane.showMessageDialog(null, sphere);
			
			}
			else  							//choice = 3 to quit
			 verifiedChoice = 3;
		}
		else		 {				//verifiedChoice = 3 to quit
 		 verifiedChoice = 3;  		//to quit
 		 JOptionPane.showMessageDialog(null, "Thank you and good bye!");
		}	//end of else
  	 } 		//end of while loop
	}   	//end of main()
}			//end of ShapeApp2 	