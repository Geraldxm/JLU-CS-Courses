import java.util.Date;
public class User {
	 private String u_name;//用户名
	    private String u_login_name;//登录名 卡的id
	    private String u_login_pwd;//登录密码
	    private String u_wallet;//钱包
	    private Date  draw_money_time;//取钱时间
	    private Date  save_money_time;//存钱时间
	    public User(){}
	    public User(String u_name, String u_login_name, String u_login_pwd, 
                      String u_wallet) {
	        this.u_name = u_name;
	        this.u_login_name = u_login_name;
	        this.u_login_pwd = u_login_pwd;
	        this.u_wallet = u_wallet;
	    }
	    public User(String u_name, String u_login_name, String u_login_pwd, 
             String u_wallet, Date draw_money_time, Date save_money_time) {
	        this.u_name = u_name;
	        this.u_login_name = u_login_name;
	        this.u_login_pwd = u_login_pwd;
	        this.u_wallet = u_wallet;
	        this.draw_money_time = draw_money_time;
	        this.save_money_time = save_money_time;
	    }
	    public String getU_name() {
	        return u_name;
	    }
	    public void setU_name(String u_name) {
	        this.u_name = u_name;
	    }
	    public String getU_login_name() {
	        return u_login_name;
	    }
	    public void setU_login_name(String u_login_name) {
	        this.u_login_name = u_login_name;
	    }
	    public String getU_login_pwd() {
	        return u_login_pwd;
	    }
	    public void setU_login_pwd(String u_login_pwd) {
	        this.u_login_pwd = u_login_pwd;
	    }
	    public String getU_wallet() {
	        return u_wallet;
	    }
	    public void setU_wallet(String u_wallet) {
	        this.u_wallet = u_wallet;
	    }
	    public Date getDraw_money_time() {
	        return draw_money_time;
	    }
	    public void setDraw_money_time(Date draw_money_time) {
	        this.draw_money_time = draw_money_time;
	    }
	    public Date getSave_money_time() {
	        return save_money_time;
	    }
	    public void setSave_money_time(Date save_money_time) {
	        this.save_money_time = save_money_time;
	    }
	}
