import java.util.ArrayList;
import java.util.List;
public class BankTest {
	  public static void main(String[] args) throws Exception {
	        User u = new User("张三", "132466", "123", "100");
	        User uu = new User("李四", "4600882", "123", "0");
	        List<User> list = new ArrayList<>();
	        list.add(u);
	        list.add(uu);
	        Bank atm = new Bank(list);//初始化数据 模拟
	        Thread t = new Thread() {
	            public void run() {

	                for (int i = 0; i < 10; i++) {
	                    atm.saveMoney("132466", "123", "12");
	                    atm.delayTime(250);
	                    atm.getMoney("4600882", "123", "14");
	                    atm.delayTime(250);
	                }

	            }
	        };
	        Thread tt = new Thread() {
	            public void run() {

	                for (int i = 0; i < 10; i++) {
	                    atm.getMoney("132466", "123", "2");
	                    atm.delayTime(250);
	                    atm.saveMoney("4600882", "123", "12");
	                    atm.delayTime(250);
	                }
	            }
	        };
	        t.start();
	        tt.start();
	    }
	}
