// AgeException类
class AgeException extends Exception {
	// 存储异常异常
	int age;
	
	// 构造器
	AgeException(int age, String s) {
		super(s);
		this.age = age;
	}
	
	// 构造器
	AgeException() {
		super();
	}
}

// OutLimitExceptio
class OutLimitException extends AgeException {
	// 存储年龄限制
	int ageLimit;
	
	// 构造器
	OutLimitException(int age, int ageLimit, String s) {
		super(age, s);
		this.ageLimit = ageLimit;
	}
	
	// 构造器
	OutLimitException() {
		super();
	}
}

// TooYoungException类
class TooYoungException extends OutLimitException {
	// 构造器
	TooYoungException (int age, int ageLimit) {
		super(age, ageLimit, "小于年龄下限！");
	}
	
	// 构造器
	TooYoungException() {
		super();
	}
}

// TooOldException类
class TooOldException extends OutLimitException {
	// 构造器
	TooOldException (int age, int ageLimit) {
		super(age, ageLimit, "大于年龄上限！");
	}
	
	// 构造器
	TooOldException () {
		super();
	}
}

// IllegalAgeException类
class IllegalAgeException extends AgeException {
	// 构造器
	IllegalAgeException (int age, String s) {
		super(age, s);
	}
	
	// 构造器
	IllegalAgeException () {
		super();
	}
}

// NegativeAgeException类
class NegativeAgeException extends IllegalAgeException {
	// 构造器
	NegativeAgeException(int age) {
		super(age, "年龄不能为负数！");
	}
	
	// 构造器
	NegativeAgeException () {
		super();
	}
}

// 主类
public class AgeExceptionDemo {
	// high_limit存储年龄上限，low_limit存储年龄下限
	static int high_limit, low_limit;
	
	// 入场观众年龄检查的方法，声明了可抛出了的异常
	static void cinemaAgeCheck(int age)
		throws NegativeAgeException, OutLimitException {
		// 如果年龄小于0，抛出NegativeAgeException异常
		if (age < 0) {
			throw new NegativeAgeException(age);
		}
		// 如果年龄小于low_limit，抛出TooYoungException异常
		else if (age < low_limit) {
			throw new TooYoungException(age, low_limit);
		}
		// 如果年龄大于high_limit，抛出TooOldException异常
		else if (age > high_limit) {
			throw new TooOldException(age, high_limit);
		}
		System.out.println(age + "岁符合年龄要求，可以入场！");
	}
	
	// 主方法
	public static void main(String[] args) {
		// age数组存放观众年龄
		int[] age = {15, -6, 36, 65, 12};
		high_limit = 60;
		low_limit = 15;
		System.out.println("开始检查入场观众的年龄……");
		System.out.println("待检查的观众年龄：" + age[0]);
		for (int i = 0; i < age.length; i++) {
			try {
				cinemaAgeCheck(age[i]);
			} catch (NegativeAgeException ne) {
				System.out.print("年龄格式有误！");
				System.out.println(ne.getMessage());
			} catch (OutLimitException oe) {
				System.out.print(oe.getMessage());
				System.out.println("不能入场！");
			} finally {
				if (i < age.length - 1) {
					System.out.println("待检查的观众年龄：" + age[i+1] + "岁");
				}
			}
		}
	}
}
