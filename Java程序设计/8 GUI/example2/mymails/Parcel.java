package mymails;

public class Parcel extends Mails
{
	double weight;
	
	Parcel(String from,String to,double weight)
		{
			fromAddr = from;
			toAddr = to;
			this.weight = weight;
		}

	public void showMe()
		{
			System.out.println("This is a Parcel from " + fromAddr + " to " + toAddr);
			System.out.println("And its weight is " + weight);
		}
}