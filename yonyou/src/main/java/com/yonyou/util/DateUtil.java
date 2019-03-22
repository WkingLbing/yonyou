package com.yonyou.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static void main(String[] args) {
		String str = dateByFormat(new Date(), "yyyy-mm-dd hh:mm:ss");
		System.out.println("str"+str);
	}
	private static String dateByFormat(Date date, String format) {
		String result = "";
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			result = sdf.format(date);
			System.out.println(result);
		}
		return result;
	}
}
