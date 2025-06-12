package com.nciae.empsys.utils;
public class Validator {
	// �ַ���Ϊ��
	public static boolean isNoEmpty(String str) {
		if (str.isEmpty())
			return false;
		else
			return true;
	}

	// �ж��ַ����ĳ����Ƿ����ָ������
	public static boolean isLength(String str, int len) {
		if (str.length() >= len) {
			return true;
		} else {
			return false;
		}
	}

	// �Ƿ�Ϊ��������
	public static boolean isIntNum(String str) {
		boolean flag = true;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (!(ch >= '��' && ch <= '9')) {
				break;
			}
		}
		// �������������Ƿ�����
		return flag;
	}

	// �Ƿ�Ϊ������
	public static boolean isFloat(String str) {
		if (str.indexOf(".") > 0) // �жϡ�.��������ǰ�ߣ����򲻼���
			// ���顰.���Ƿ������һλ
			if (str.indexOf(".") == str.lastIndexOf(".") && str.indexOf(".") < str.length()) {
				String ch = str.replace(".", "");
				return isIntNum(ch);
			}
		return false;
	}

	// �Ƿ����ֻ��绰����
	public static boolean isPhone(String str) {
		boolean flag = true;
		if (isNoEmpty(str)) {
			if ((isIntNum(str) == true) && (str.charAt(0) == 1) && (str.length() == 11))
				flag = true;
			else
				flag = false;
		} else
			flag = false;
		return flag;
	}

	// �Ƿ��Ǻϸ�����ʼ�
	public static boolean isEmail(String str) {
		for (int i = 0; i < str.length(); i++) {
			int intat = str.indexOf("@");
			int intpoint = str.indexOf(".");
			if ((intat > 0) && (intpoint < str.length() - 1) && (intpoint > (intat + 1))) {
				return true;
			}
		}
		String ch1 = str.replace("@", ""); // ��ȥ��@��
		String ch2 = ch1.replace(".", ""); // ��ȥ��.��
		if (isIntNum(ch2) && isEnglish(ch2)) {
			return true;
		}
		return false;
	}

	// �Ƿ���Ӣ����ĸ
	public static boolean isEnglish(String str) {
		boolean flag = true;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')))// ������Сд
			{
				flag = false;
				break;
			}
		}
		return flag;
	}
}