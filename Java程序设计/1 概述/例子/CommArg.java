public class CommArg
{
     public static void main(String args[])
     {
           // Display command arguments
           int i;
		
           if( args.length > 0 )  //have some command arguments
           {
                 for( i=0; i<args.length; i++ )
                 {
                         System.out.println("arg["+i+"] = "+args[i]);
                 }
           }
           else   //no command argument
           {
                  System.out.println("No arguments!");
           }
    }
}
