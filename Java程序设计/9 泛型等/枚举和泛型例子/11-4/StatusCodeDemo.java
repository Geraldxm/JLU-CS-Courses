package chap12;
enum StatusCode{
	//枚举常量
	OK(0, "成功"),
    ERROR_A(100, "错误A"),
    ERROR_B(200, "错误B");
	
	//类字段
	 private int code;
	 private String description;
	//构造方法
    private StatusCode(int number, String description) {
        this.code = number;
        this.description = description;
    }
   //其他方法
    public int getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
}
public class StatusCodeDemo {
	public static void main(String args[]) { 
		//列出所有错误编码
        for (StatusCode s : StatusCode.values()) {
            System.out.println(s.ordinal()+","+"code: " + s.getCode() + ", description: " + s.getDescription());
        }
        //定义运行状态变量并赋值       
        StatusCode code=StatusCode.ERROR_A;
    }
}
