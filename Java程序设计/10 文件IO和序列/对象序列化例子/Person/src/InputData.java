import java.io.* ;
import java.util.* ;
import java.text.* ;
public class InputData{
	private BufferedReader buf = null ;
	public InputData(){// 只要输入数据就要使用此语句
		this.buf = new BufferedReader(new InputStreamReader(System.in)) ;
	}
	public String getString(String info){	// 得到字符串信息
		String temp = null ;
		System.out.print(info) ;	// 打印提示信息
		try{
			temp = this.buf.readLine() ;	// 接收数据
		}catch(IOException e){
			e.printStackTrace() ;
		}
		return temp ;
	}
	public int getInt(String info,String err){	// 得到一个整数的输入数据
		int temp = 0 ;
		String str = null ;
		boolean flag = true ;	// 定义一个标记位
		while(flag){
			str = this.getString(info) ;	// 接收数据
			if(str.matches("^\\d+$")){	// 判断是否由数字组成
				temp = Integer.parseInt(str) ;	// 转型
				flag = false ;	// 结束循环
			}else{
				System.out.println(err) ;	// 打印错误信息
			}
		}
		return temp ;
	}
	public float getFloat(String info,String err){	// 得到一个小数的输入数据
		float temp = 0 ;
		String str = null ;
		boolean flag = true ;	// 定义一个标记位
		while(flag){
			str = this.getString(info) ;	// 接收数据
			if(str.matches("^\\d+.?\\d+$")){	// 判断是否由数字组成
				temp = Float.parseFloat(str) ;	// 转型
				flag = false ;	// 结束循环
			}else{
				System.out.println(err) ;	// 打印错误信息
			}
		}
		return temp ;
	}
	public Date getDate(String info,String err){	// 得到一个小数的输入数据
		Date temp = null ;
		String str = null ;
		boolean flag = true ;	// 定义一个标记位
		while(flag){
			str = this.getString(info) ;	// 接收数据
			if(str.matches("^\\d{4}-\\d{2}-\\d{2}$")){	// 判断是否由数字组成
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;
				try{
					temp = sdf.parse(str) ;	// 将字符串变为Date型数据
				}catch(Exception e){}
				flag = false ;	// 结束循环
			}else{
				System.out.println(err) ;	// 打印错误信息
			}
		}
		return temp ;
	}
};