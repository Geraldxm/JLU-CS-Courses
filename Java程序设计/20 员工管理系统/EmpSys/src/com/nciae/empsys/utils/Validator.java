package com.nciae.empsys.utils;
public class Validator {
	// 字符串为空
	public static boolean isNoEmpty(String str) {
		if (str.isEmpty())
			return false;
		else
			return true;
	}

	// 判断字符串的长度是否大于指定长度
	public static boolean isLength(String str, int len) {
		if (str.length() >= len) {
			return true;
		} else {
			return false;
		}
	}

	// 是否为数字整数
	public static boolean isIntNum(String str) {
		boolean flag = true;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (!(ch >= 'θ' && ch <= '9')) {
				break;
			}
		}
		// 区分是正常还是非正常
		return flag;
	}

	// 是否为浮点数
	public static boolean isFloat(String str) {
		if (str.indexOf(".") > 0) // 判断“.”不在最前边；否则不继续
			// 检验“.”是否在最后一位
			if (str.indexOf(".") == str.lastIndexOf(".") && str.indexOf(".") < str.length()) {
				String ch = str.replace(".", "");
				return isIntNum(ch);
			}
		return false;
	}

	// 是否是手机电话号码
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

	// 是否是合格电子邮件
	public static boolean isEmail(String str) {
		for (int i = 0; i < str.length(); i++) {
			int intat = str.indexOf("@");
			int intpoint = str.indexOf(".");
			if ((intat > 0) && (intpoint < str.length() - 1) && (intpoint > (intat + 1))) {
				return true;
			}
		}
		String ch1 = str.replace("@", ""); // 除去“@”
		String ch2 = ch1.replace(".", ""); // 除去“.”
		if (isIntNum(ch2) && isEnglish(ch2)) {
			return true;
		}
		return false;
	}

	// 是否是英文字母
	public static boolean isEnglish(String str) {
		boolean flag = true;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')))// 包括大小写
			{
				flag = false;
				break;
			}
		}
		return flag;
	}
}