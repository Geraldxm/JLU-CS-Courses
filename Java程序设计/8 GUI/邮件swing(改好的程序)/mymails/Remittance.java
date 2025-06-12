package mymails;

public class Remittance extends Mails
{
	double remitValue;
	
	Remittance(String from,String to,double remitValue)
		{
			fromAddr = from;
			toAddr = to;
			this.remitValue = remitValue;
		}

	public void showMe()
		{
			System.out.println("This is a Remittance " + remitValue + " from" + fromAddr + " to " + toAddr);
		}
}
