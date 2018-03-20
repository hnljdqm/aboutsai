package com.aboutsai.framework.util;

import java.util.List;
import org.springframework.util.StringUtils;

public class StringUtil extends StringUtils {
	public static final boolean isNotEmpty(String[] strs) {
		return (strs != null) && (strs.length != 0);
	}

	public static final boolean isEmpty(String[] strs) {
		return !isNotEmpty(strs);
	}

	public static final boolean isEqual(String str1, String str2) {
		if ((isEmpty(str1)) && (isEmpty(str2))) {
			return true;
		}
		if ((isEmpty(str1)) || (isEmpty(str2))) {
			return false;
		}
		return str1.trim().equals(str2.trim());
	}

	public static final boolean isNotEqual(String str1, String str2) {
		return !isEqual(str1, str2);
	}

	public static final boolean isEqualIgnoreCase(String str1, String str2) {
		if ((isEmpty(str1)) && (isEmpty(str2))) {
			return true;
		}
		if ((isEmpty(str1)) || (isEmpty(str2))) {
			return false;
		}
		return str1.trim().equalsIgnoreCase(str2.trim());
	}

	public static final String removeLast(String str, int n) {
		if (isEmpty(str)) {
			return str;
		}
		if (n >= str.length()) {
			return "";
		}
		return str.substring(0, str.length() - n);
	}

	public static final String removeLast(String str) {
		if (isEmpty(str)) {
			return str;
		}
		return str.substring(0, str.length() - 1);
	}

	public static final String removeAllSpace(String str) {
		if (isEmpty(str)) {
			return "";
		}
		return str.replaceAll("\\s+", "");
	}

	public static final String field2Column(String str) {
		if (isEmpty(str)) {
			return "";
		}
		return str.replaceAll("[A-Z]", "_$0").toUpperCase();
	}

	public static final boolean hasSubString(String str, String subString) {
		if ((isEmpty(str)) || (isEmpty(subString))) {
			return false;
		}
		return str.indexOf(subString) >= 0;
	}

	public static boolean isNumeric(String str) {
		if (isEmpty(str)) {
			return false;
		}
		if (str.matches("\\d*")) {
			return true;
		}
		return false;
	}

	public static boolean isDouble(String str) {
		if (isEmpty(str)) {
			return false;
		}
		try {
			Double.parseDouble(str);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static String[] listToArray(List<String> emailList) {
		String[] userEmails = new String[emailList.size()];
		int index = 0;
		for (String email : emailList) {
			userEmails[(index++)] = email;
		}
		return userEmails;
	}

	public static String jsonCharFilter(String jsonStr) {
		if ((isEmpty(jsonStr)) || (jsonStr.length() == 0)) {
			return "";
		}
		if (jsonStr.indexOf("'") > 0) {
			jsonStr = jsonStr.replaceAll("'", "\\\\'");
		}
		if ((jsonStr.indexOf("\r") > 0) || (jsonStr.indexOf("\n") > 0)) {
			jsonStr = jsonStr.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		}
		return jsonStr;
	}

}
