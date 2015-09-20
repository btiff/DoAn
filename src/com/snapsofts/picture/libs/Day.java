package com.snapsofts.picture.libs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Day {
	public static String day(long a) {
		Date d = new Date(a * 1000);
		SimpleDateFormat f = new SimpleDateFormat("dd");
		f.setTimeZone(TimeZone.getTimeZone("GMT"));
		String s = f.format(d);
		return s;
	}
	public static String time(long a) {
		Date d = new Date(a * 1000);
		SimpleDateFormat f = new SimpleDateFormat("HH:mm");
		f.setTimeZone(TimeZone.getTimeZone("GMT"));
		String s = f.format(d);
		return s;
	}
	public static String year(long a) {
		Date d = new Date(a);
		SimpleDateFormat f = new SimpleDateFormat("yyyy");
		f.setTimeZone(TimeZone.getTimeZone("GMT"));
		String s = f.format(d);
		return s;
	}
	public static String month(long a) {
		Date d = new Date(a * 1000);
		SimpleDateFormat f = new SimpleDateFormat("MM");
		f.setTimeZone(TimeZone.getTimeZone("GMT"));
		String s = f.format(d);
		return s;
	}
}
