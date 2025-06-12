// AgeException��
class AgeException extends Exception {
	// �洢�쳣�쳣
	int age;
	
	// ������
	AgeException(int age, String s) {
		super(s);
		this.age = age;
	}
	
	// ������
	AgeException() {
		super();
	}
}

// OutLimitExceptio
class OutLimitException extends AgeException {
	// �洢��������
	int ageLimit;
	
	// ������
	OutLimitException(int age, int ageLimit, String s) {
		super(age, s);
		this.ageLimit = ageLimit;
	}
	
	// ������
	OutLimitException() {
		super();
	}
}

// TooYoungException��
class TooYoungException extends OutLimitException {
	// ������
	TooYoungException (int age, int ageLimit) {
		super(age, ageLimit, "С���������ޣ�");
	}
	
	// ������
	TooYoungException() {
		super();
	}
}

// TooOldException��
class TooOldException extends OutLimitException {
	// ������
	TooOldException (int age, int ageLimit) {
		super(age, ageLimit, "�����������ޣ�");
	}
	
	// ������
	TooOldException () {
		super();
	}
}

// IllegalAgeException��
class IllegalAgeException extends AgeException {
	// ������
	IllegalAgeException (int age, String s) {
		super(age, s);
	}
	
	// ������
	IllegalAgeException () {
		super();
	}
}

// NegativeAgeException��
class NegativeAgeException extends IllegalAgeException {
	// ������
	NegativeAgeException(int age) {
		super(age, "���䲻��Ϊ������");
	}
	
	// ������
	NegativeAgeException () {
		super();
	}
}

// ����
public class AgeExceptionDemo {
	// high_limit�洢�������ޣ�low_limit�洢��������
	static int high_limit, low_limit;
	
	// �볡����������ķ����������˿��׳��˵��쳣
	static void cinemaAgeCheck(int age)
		throws NegativeAgeException, OutLimitException {
		// �������С��0���׳�NegativeAgeException�쳣
		if (age < 0) {
			throw new NegativeAgeException(age);
		}
		// �������С��low_limit���׳�TooYoungException�쳣
		else if (age < low_limit) {
			throw new TooYoungException(age, low_limit);
		}
		// ����������high_limit���׳�TooOldException�쳣
		else if (age > high_limit) {
			throw new TooOldException(age, high_limit);
		}
		System.out.println(age + "���������Ҫ�󣬿����볡��");
	}
	
	// ������
	public static void main(String[] args) {
		// age�����Ź�������
		int[] age = {15, -6, 36, 65, 12};
		high_limit = 60;
		low_limit = 15;
		System.out.println("��ʼ����볡���ڵ����䡭��");
		System.out.println("�����Ĺ������䣺" + age[0]);
		for (int i = 0; i < age.length; i++) {
			try {
				cinemaAgeCheck(age[i]);
			} catch (NegativeAgeException ne) {
				System.out.print("�����ʽ����");
				System.out.println(ne.getMessage());
			} catch (OutLimitException oe) {
				System.out.print(oe.getMessage());
				System.out.println("�����볡��");
			} finally {
				if (i < age.length - 1) {
					System.out.println("�����Ĺ������䣺" + age[i+1] + "��");
				}
			}
		}
	}
}
