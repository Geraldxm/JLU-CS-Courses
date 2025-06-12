package mymails;

public class Postemit extends Remittance implements MailPost
{
	boolean isPosted;
	static double remitFee = 0.1;

	Postemit(String from,String to,double remitValue)
		{
			super(from,to,remitValue);
			isPosted = false;
		}

	public double callPrice()
		{
			return remitFee * remitValue + 1.0;
		}

	public void post()
		{
		   isPosted = true;
		   System.out.println("You have paid " + callPrice() + " for this Remittance...");
		   System.out.println("Your remittance has been posted.");
		}

	public void showMe()
		{
			super.showMe();
			System.out.println("This remittance's fee is " + callPrice());
			if (isPosted){
				System.out.println("This remittance has been posted...");
				}
			else
				{
				System.out.println("This remittance has not been posted...");
				}
			System.out.println();
		}
}