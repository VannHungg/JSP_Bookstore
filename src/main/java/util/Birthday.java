package util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Birthday {
	public static java.sql.Date changeStringToDate(String str) {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date dateUtil = df.parse(str);
			java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
			return dateSql;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static java.sql.Date thoiHanHieuLuc(int time) throws ParseException {
		java.sql.Date todaysDate = new java.sql.Date(new java.util.Date().getTime());
		Calendar c = Calendar.getInstance();
		c.setTime(todaysDate);
		c.add(Calendar.DATE, time);
		java.sql.Date thoGianHieuLucXacThuc = new java.sql.Date(c.getTimeInMillis());
		return thoGianHieuLucXacThuc;
	}
}
