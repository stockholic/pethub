package kr.pethub.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	/**
	 * 특수문자 제거
	 * @param str
	 * @return
	 */
	public static String specialCharacterRemove(String str) {
		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z0-9\\s-,]";
		str = str.replaceAll(match, "");
		return str;
	}
	
	/**
	 * 정규식 체크
	 * @param pattern
	 * @param str
	 * @return
	 */
	public static boolean isRegex(String pattern, String str) {
		if(str == null) return false;
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		return m.find();
		
	}
	
	/**
	 * 숫자 체크
	 * @param pattern
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		if(str == null) return false;
		return isRegex("^[0-9]+$", str);
	}
    
    
	
}