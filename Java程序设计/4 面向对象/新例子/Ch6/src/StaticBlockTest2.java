//Demo: use of static block, example 2

import java.util.Scanner;

public class StaticBlockTest2  //example that shows how static block works
{
    public static void main(String args[])
    {

       StaticBlock staticBlockObj = new StaticBlock(); // the static block is automatically called

       staticBlockObj.displayTaxRate(); // call method
	}
}

class StaticBlock  //demo
{
	public static double taxRate[] = new double[5];  //create array to hold the static data
        	static {  //static block
					Scanner sc = new Scanner(System.in);
					for(int i = 0; i < 5; i++)
					 {
						 System.out.print("Eneter the tax rate for County " + i + ": ");
						 taxRate[i] = sc.nextDouble();
						 System.out.println();
				     }
					sc.close();	
			}    //end of static block
        	
	public void displayTaxRate() //static method
	 {

		 for (int i = 0; i < 5; i++)
          { System.out.println(StaticBlock.taxRate[i]);}
     }
}
