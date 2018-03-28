package com.aboutsai.framework.util;

import java.util.List;

public class StringUtil {
	public static final boolean isNotEmpty(String[] strs) {
		return (strs != null) && (strs.length != 0);
	}
	
	public static final boolean isNotEmpty(String strs) {
		return (strs != null) && (strs.length() != 0);
	}

	public static final boolean isEmpty(String[] strs) {
		return !isNotEmpty(strs);
	}
	
	public static final boolean isEmpty(String strs) {
		return !isNotEmpty(strs);
	}


	public static String[] listToArray(List<String> emailList) {
		String[] userEmails = new String[emailList.size()];
		int index = 0;
		for (String email : emailList) {
			userEmails[(index++)] = email;
		}
		return userEmails;
	}

}
