//demo:  testing this as argument

class Help {
    int n;
    public void setMe (int m) {

        Helper.setValue(this, m);
	}
	public void setN(int num) { n = num; }
	public String toString() { return ("" + n ); }
}

//middle tie class
class Helper {
   public static void setValue (Help help, int num) {help.setN(num);};
	}

//the driver code
public class TestThisApp {
    public static void main(String[] args) {
        Help help = new Help();
        help.setMe(3);
        System.out.println( help );
    }
}
