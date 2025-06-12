public class Test {
	  public static void main(String []args){
	        Fork fork = new Fork();
	        new Philosopher("0",fork).start();
	        new Philosopher("1",fork).start();
	        new Philosopher("2",fork).start();
	        new Philosopher("3",fork).start();
	        new Philosopher("4",fork).start();
	    }
}
