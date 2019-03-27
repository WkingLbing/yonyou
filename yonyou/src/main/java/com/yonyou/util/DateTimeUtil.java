package com.yonyou.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateTimeUtil {
	public static void main(String[] args) {
		String c="2019-03-25";
		String b="2019-04-03";
		List<String> str = PackageDate(c,b);
		System.out.println(str);
	}
	public static List<String> PackageDate(String startTime, String endTime) {

		if (startTime==null || endTime==null) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		if (startTime.length() == 8) {
			if (startTime.substring(0, 4).equals(endTime.substring(0, 4))) {
				if(startTime.substring(4, 6).equals(endTime.substring(4, 6))) {
					// 同一年,同月
					int ys = Integer.parseInt(startTime.substring(6, 8));
					int ye = Integer.parseInt(endTime.substring(6, 8));
					for (int i = ys; i <= ye; i++) {
						String num;
						if (i < 10) {
							num = "0" + i;
						} else {
							num = i + "";
						}
						list.add(startTime.substring(0, 6) + num);
					}
				}else {
					//同年 不同月
					int year = Integer.parseInt(startTime.substring(0, 4));
					int month = Integer.parseInt(startTime.substring(4, 6));

					Calendar a = Calendar.getInstance();
					a.set(Calendar.YEAR, year);
					a.set(Calendar.MONTH, month - 1);
					a.set(Calendar.DATE, 1);
					a.roll(Calendar.DATE, -1);
					int maxDate = a.get(Calendar.DATE);

					int ds = Integer.parseInt(startTime.substring(6, 8));
					int de = Integer.parseInt(endTime.substring(6, 8));

					for (int i = ds; i <= maxDate; i++) {
						String num;
						if (i < 10) {
							num = "0" + i;
						} else {
							num = i + "";
						}
						list.add(startTime.substring(0, 6) + num);
					}
					for (int i = 1; i <= de; i++) {
						String num;
						if (i < 10) {
							num = "0" + i;
						} else {
							num = i + "";
						}
						list.add(endTime.substring(0, 6) + num);
					}
				}
			}
		} else if (startTime.length() == 10) {
			if (startTime.substring(5, 7).equals(endTime.substring(5, 7))) {
				// 同一月
				int ds = Integer.parseInt(startTime.substring(8, 10));
				int de = Integer.parseInt(endTime.substring(8, 10));

				for (int i = ds; i <= de; i++) {
					String num;
					if (i < 10) {
						num = "0" + i;
					} else {
						num = i + "";
					}
					list.add(startTime.substring(0, 8) + num);
				}
			} else {
				// 不同月
				int year = Integer.parseInt(startTime.substring(0, 4));
				int month = Integer.parseInt(startTime.substring(5, 7));

				Calendar a = Calendar.getInstance();
				a.set(Calendar.YEAR, year);
				a.set(Calendar.MONTH, month - 1);
				a.set(Calendar.DATE, 1);
				a.roll(Calendar.DATE, -1);
				int maxDate = a.get(Calendar.DATE);

				int ds = Integer.parseInt(startTime.substring(8, 10));
				int de = Integer.parseInt(endTime.substring(8, 10));

				for (int i = ds; i <= maxDate; i++) {
					String num;
					if (i < 10) {
						num = "0" + i;
					} else {
						num = i + "";
					}
					list.add(startTime.substring(0, 8) + num);
				}
				for (int i = 1; i <= de; i++) {
					String num;
					if (i < 10) {
						num = "0" + i;
					} else {
						num = i + "";
					}
					list.add(endTime.substring(0, 8) + num);
				}

			}

		}

		return list;
	}
}
