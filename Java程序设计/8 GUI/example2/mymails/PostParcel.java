package mymails;

public class PostParcel extends Parcel implements MailPost
{
	boolean isPosted;
	static double parcelFee = 1.2;
	
	PostParcel(String from,String to,double weight)
		{
			super(from,to,weight);
			isPosted = false;
		}

	public double callPrice()
		{
			return parcelFee * weight + 1.0;
		}

	public void post()
		{
				isPosted = true;
				System.out.println("You have paid " + callPrice() + " for this Parcel...");
				System.out.println("Your parcel has been posted.");
		}

	public void showMe()
		{
			super.showMe();
			System.out.println("This Parcel's fee is " + callPrice());
			if (isPosted){
				System.out.println("This Parcel has been posted...");
				}
			else
				{
				System.out.println("This Parcel has not been posted...");
				}
			System.out.println();
		}
}