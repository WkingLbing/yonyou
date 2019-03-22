package com.yonyou.util;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {
	
	public static void main(String[] args) {
		String str="123123487978";
		boolean result=check(str);
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);  
		String password = scanner.next();
		String result1=checkPassword(password);
		System.out.println(str+","+result);
		System.out.println(password+","+result1);
	}
	//全部为数字
	private static boolean check(String str) {
		Pattern pattern = Pattern.compile("[\\d]*");
		 Matcher isNum = pattern.matcher(str);
         if( !isNum.matches() ){
             return false;
         }
         return true;
	}
	//为6-12位得字母、数字或英文字符
	public static String checkPassword(String passwordStr) {
	    if (passwordStr != null && !"".equals(passwordStr) && (passwordStr.length() < 6 || passwordStr.length() > 12)) {
	        return "密码为 6-12 位字母、数字或英文字符!";
	    }
	    // Z = 字母       S = 数字           T = 特殊字符
	    String regexZ = "[A-Za-z]+";
	    String regexS = "^\\d+$";
	    String regexT = "[~!@#$%^&*.]+";
	    String regexZT = "[a-zA-Z~!@#$%^&*.]+";
	    String regexZS = "[0-9A-Za-z]+";
	    String regexST = "[\\d~!@#$%^&*.]*";
	    String regexZST = "[\\da-zA-Z~!@#$%^&*.]+";
	 
	    if (passwordStr.matches(regexZ)){
	        return "纯字母，弱";
	    }
	    if (passwordStr.matches(regexS)){
	        return "纯数字，弱";
	    }
	    if (passwordStr.matches(regexT)){
	        return "纯字符，弱";
	    }
	    if (passwordStr.matches(regexZT)){
	        return "字母字符，中";
	    }
	    if (passwordStr.matches(regexZS)){
	        return "字母数字，中";
	    }
	    if (passwordStr.matches(regexST)){
	        return "数字字符，中";
	    }
	    if (passwordStr.matches(regexZST)) {
	        return "符合要求";
	    }
	    return "不知道是啥";
	}
	
	
	
}
