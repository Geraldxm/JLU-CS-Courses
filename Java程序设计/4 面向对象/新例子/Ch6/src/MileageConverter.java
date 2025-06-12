//Demo: constructor overloading in defining MileageConverter class

public class MileageConverter {		//The operation class

	double	kilometers,				//declare the class variables
			miles,					//They will be automatically 
			result;					//initialized to 0.0

	public MileageConverter() {		//non-argument constructor
			miles = 0.0;			//these are optional and
			kilometers = 0.0;		//not necessary
			result = 0.0;
	}
	public MileageConverter (double distance){
			miles = distance;
			kilometers = distance;
			result = 0.0;
		}

	public void setKilometers(double km) //setKilometers() method
	 	{ kilometers = km; }

	public double getKilometers()		//getKilometers() method
		{ return kilometers; }

	public void setMiles(double mile)  	//setMiles() method
	 	{ miles = mile; }

	public double getMiles()			//getMiles() method
		{ return miles; }

	public double getResult()			//getResult() method
		{ return result; }

	public void convertKilometers()		//convert kilometers to miles
		{ result = kilometers * 0.62137; }

	public void convertMiles()			//convert miles to kilometers
		{ result = miles * 1.609347; }

 } //end of MileageConverter class