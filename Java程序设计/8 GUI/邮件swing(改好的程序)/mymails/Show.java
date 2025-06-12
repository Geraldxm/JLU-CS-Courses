package mymails;

import java.io.*;

public class Show
{
	public static void main(String args[])
		{
			String fromAddr;
			String toAddr;
			String mailType;
			String endSignal;
			Mails newmail;
			double weight;
			double remitValue;
			boolean isParcel = false;

			BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));


        	while(true)
        	{
        		while(true)
        			{
        				System.out.print("Please input the postmail type(postparcel-p;postremittance-r):");
        				try
        				{
        					mailType = reader.readLine();
							if(mailType.equals("p"))
								{
									isParcel = true;
								}
							else if (mailType.equals("r"))
								{
									isParcel = false;
								}
							else
							{
								throw(new NumberFormatException());
							}
        					System.out.println("Input mail messages:");
        					System.out.print("sourceAddress:");
        					fromAddr = reader.readLine();
            				        System.out.print("destinationAddress:");
        					toAddr = reader.readLine();
        					if(isParcel)
        					{
        						System.out.print("weight:");
        						weight = Double.parseDouble(reader.readLine());
        						newmail = new PostParcel(fromAddr,toAddr,weight);
        					}
        					else
        					{
        	 					System.out.print("remitvalue:");
        						remitValue = Double.parseDouble(reader.readLine());
        						newmail=new Postemit(fromAddr,toAddr,remitValue);
						}
							break;
                                          }
					catch(NumberFormatException e)
						{
							System.out.println("This value does not measure,reinput plz!");
            			                }
					catch(IOException e){}
					}

				ShowMails.putMails(newmail);

				System.out.println("New mail operation complete...");
				System.out.print("Exit----------press e,Continue----------other keys:");

				try
				{
					endSignal = reader.readLine();
					if (endSignal.equals("e"))
					break;
				}
				catch (IOException e){}

			}

		ShowMails.showAll();

		MailPost tempMail = null;

		System.out.println();
		System.out.println("Starting posting all mails...");
		System.out.println();

		while(true)
		{
			tempMail = (MailPost)ShowMails.getMails();
			tempMail.post();
			System.out.println();
			if (ShowMails.currentMails == 0)
			{
				System.out.println("All mails have been shown...");
				break;
			}
		}
	}
}
