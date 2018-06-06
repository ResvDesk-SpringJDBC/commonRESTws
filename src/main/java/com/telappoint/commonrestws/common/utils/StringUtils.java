package com.telappoint.commonrestws.common.utils;

import java.util.Set;

/**
 * @author Rajin
 * @author Balaji
 * 
 */
public class StringUtils {

	public static boolean isEmpty(String string) {
		return (string != null && string.length() > 0) ? false : true;
	}

	public static <T> String concatWithSeparator(Set<T> set,String separator) {
		StringBuilder idStr = new StringBuilder();
		for (T t: set) {
			if (idStr.length() > 0) {
				idStr.append(separator);
				idStr.append(t);
			} else {
				idStr.append(t);
			}
		}
		return idStr.toString();
	}
	
	public static String singleQuoteString(String input, String delimitor) {
		String loginSplit[] = input.split("\\" + delimitor);
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < loginSplit.length; i++) {
			sb.append("'" + loginSplit[i] + "'");
			if (i + 1 < loginSplit.length == true)
				sb.append(",");
		}
		return sb.toString();
	}
}
