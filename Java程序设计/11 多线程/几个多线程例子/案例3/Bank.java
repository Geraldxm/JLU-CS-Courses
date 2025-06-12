import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Bank {
	  private List<User> userList=new ArrayList<>();
	    public  Bank(List<User> userList) {
	         this.userList = userList;
	    }
	    public  List<User> getUserList() {
	        return userList;
	    }
	    public void setUserList(List<User> userList) {
	        this.userList = userList;
	    }
	    //存钱
	    public Boolean saveMoney(String card,String pwd,String moneyNum){
	        User u=getUserByCard(card);
	        synchronized (Bank.class) {
	        if (u.getU_login_name().equals(card) && 
                  u.getU_login_pwd().equals(pwd)) {
	                    BigDecimal oldData=new BigDecimal(u.getU_wallet());
	                    BigDecimal money=new BigDecimal(moneyNum);
	                    u.setU_wallet(oldData.add(money).toString());
	                    u.setSave_money_time(new Date());
          System.out.println(Thread.currentThread().getName()+
        		  "存钱---->"+u.getU_name()+"在"+new SimpleDateFormat(
        				  "yyyy-MM-dd  HH:mm:ss").format(u.getSave_money_time())+"存["+moneyNum+
        		  "]钱，余额："+u.getU_wallet());
	                    return true;
	                }
	            }
	        System.out.println(getUserByCard(card).getU_name()+"存钱失败");
	      return false;
	    }
	    //取钱
	    public Boolean getMoney(String card,String pwd,String moneyNum){
	        User u=getUserByCard(card);
	        synchronized (Bank.class) {
	        if (u!=null && u.getU_login_name().equals(card) && 
                 u.getU_login_pwd().equals(pwd)) {
	                BigDecimal oldData=new BigDecimal(u.getU_wallet());
	                BigDecimal money=new BigDecimal(moneyNum);
	                if(oldData.compareTo(money)>=0){
                     u.setU_wallet(oldData.subtract(money).toString());
	                 u.setDraw_money_time(new Date());
              System.out.println(Thread.currentThread().getName()+
            		  "取钱---->"+u.getU_name()+"在"+new SimpleDateFormat(
            				  "yyyy-MM-dd HH:mm:ss").format(u.getDraw_money_time())+"取["+moneyNum+
            		  "]钱,余额："+u.getU_wallet());
	                        return true;
	                    }else {
                System.out.println(getUserByCard(card).getU_name()+
                		"要取["+moneyNum+"]钱,但余额不足");
	                        return false;
	                    }
	                }
	            }
	        System.out.println(card+"取钱失败");
	        return false;
	    }
	    //查询余额
	    public String balanceEnquiry(String card,String pwd){
	        for(User u :this.userList){
	            if(u.getU_login_name().equals(card)&& 
                    u.getU_login_pwd().equals(pwd)){
                 System.out.println(Thread.currentThread().getName()+":"
                                     +u.getU_name()+"余额："+u.getU_wallet());
	                return u.getU_wallet();
	            }
	        }
	        System.out.println(Thread.currentThread().getName()+":"+card+"操作失败");
	        return null;
	    }
	   //获取当前用户
	    public synchronized User getUserByCard(String card){
	        for(User u :this.userList){
	            if(u.getU_login_name().equals(card)){
	                return u;
	            }
	        }
	        return null;
	    }
	    public void delayTime(Integer nim){
	        try {
	            Thread.sleep(nim);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}
