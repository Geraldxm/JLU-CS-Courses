package mymails;

public class ShowMails
{
	static int maxMails = 128;
	static int currentMails = 0;  
	static Mails[] showList = new Mails[maxMails];

	static int putMails(Mails mail)
		{
			if (currentMails >= maxMails)
				{
					System.out.println("Mailbox overwhelmed!");
					return -1;
				}

			showList[currentMails] = mail;
			currentMails ++;
			return currentMails;
		}

	static Mails getMails()
		{
			if (currentMails == 0)
				{
					System.out.println("No mail in the mail box...");
					return null;
				}
			currentMails --;
			return showList[currentMails];
		}

	static void showAll()
		{
			int i;
			for (i=0; i<currentMails; i++)
				{
					showList[i].showMe();
				}
			return;
		}
}