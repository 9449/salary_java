package com.qhl.salary_java.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateFormatUtil {

	public static Date DateTimeToToday(Date t) {
		if (t != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String s = sdf.format(t);
			try {
				Date date = sdf.parse(s);
				return date;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Date StringToDate(String t, String pattern) {
		if (t != null) {
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			try {
				Date date = sdf.parse(t);
				return date;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String dateToString(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		if (null != date) {
			return sdf.format(date);
		}
		return null;
	}

	public static String dateToyyyymmdd(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		if (null != date) {
			return sdf.format(date);
		}
		return null;
	}

	public static Date DateTimeToTomorrow(Date t) {
		if (t != null) {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String s = sdf.format(t);
			try {
				c.setTime(sdf.parse(s));
				c.add(Calendar.DAY_OF_MONTH, 1);
				Date date = c.getTime();
				return date;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Date DateTimeToYesterday(Date t) {
		if (t != null) {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String s = sdf.format(t);
			try {
				c.setTime(sdf.parse(s));
				c.add(Calendar.DAY_OF_MONTH, -1);
				Date date = c.getTime();
				return date;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String numberToPercent(Integer num1, Integer num2) {
		if (num2 == 0) {
			return "0%";
		}
		BigDecimal a = new BigDecimal(num1);
		BigDecimal b = new BigDecimal(num2);
		BigDecimal divide = a.divide(b, 4, BigDecimal.ROUND_HALF_UP);
		NumberFormat numberFormat = NumberFormat.getPercentInstance();
		numberFormat.setMaximumFractionDigits(2);
		divide.doubleValue();
		return numberFormat.format(divide.doubleValue());
	}

	public static String numberToPercent(Double num1, Double num2) {
		if (num2 == 0) {
			return "0%";
		}
		BigDecimal a = new BigDecimal(num1);
		BigDecimal b = new BigDecimal(num2);
		BigDecimal divide = a.divide(b, 4, BigDecimal.ROUND_HALF_UP);
		System.out.println(divide);
		NumberFormat numberFormat = NumberFormat.getPercentInstance();
		numberFormat.setMaximumFractionDigits(2);
		divide.doubleValue();
		return numberFormat.format(divide.doubleValue());
	}

	public static Double percentToNumber(String percent) {
		if (StringUtils.isNoneBlank(percent)) {
			if (percent.equals("0%")) {
				return 0.0;
			}
		} else {
			return 0.0;
		}
		NumberFormat nf = NumberFormat.getPercentInstance();
		try {
			// 将百分数转换成Number类型
			Number m = nf.parse(percent);
			double dd = m.doubleValue();
			return dd;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0.0;

	}

	public static Date beforeThreeDay() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 三天前
		c.add(Calendar.DATE, -3);
		Date d = c.getTime();
		return d;
	}

	public static Date lastDay() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		return c.getTime();
	}

	public static Date nextDay() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

	public static Date curMonthOfFirstDay() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 当月第一天
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);

		Date d = c.getTime();
		String format = sdf.format(d);
		try {
			Date result = sdf.parse(format);
			return result;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Date nextMonthOfFirstDay() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 下月月第一天
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		Date d = c.getTime();
		String format = sdf.format(d);
		try {
			Date result = sdf.parse(format);
			return result;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Date lastMonthOfFirstDay() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 上个月第一天
		c.add(Calendar.MONTH, -1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		Date d = c.getTime();
		String format = sdf.format(d);
		try {
			Date result = sdf.parse(format);
			return result;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date MonthOfFirstDay(Date date,int num){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 上个月第一天
		c.add(Calendar.MONTH, num);
		c.set(Calendar.DAY_OF_MONTH, 1);
		Date d = c.getTime();
		String format = sdf.format(d);
		try {
			Date result = sdf.parse(format);
			return result;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Date MonthOfFirstDay(int num) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 上个月第一天
		c.add(Calendar.MONTH, num);
		c.set(Calendar.DAY_OF_MONTH, 1);
		Date d = c.getTime();
		String format = sdf.format(d);
		try {
			Date result = sdf.parse(format);
			return result;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Date beforeOneWeek() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		// 过去七天
		c.setTime(new Date());
		c.add(Calendar.WEEK_OF_MONTH, -1);
		Date d = c.getTime();
		return d;
	}

	public static Date beforeDay(int num) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		// 过去七天
		c.setTime(new Date());
		c.add(Calendar.DATE, num);
		Date d = c.getTime();
		return DateTimeToToday(d);
	}

	public static Date beforeOneMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		// 过去一月
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date d = c.getTime();
		return d;
	}

	public static Date nextOneMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		// 过去一月
		c.setTime(new Date());
		c.add(Calendar.MONTH, +1);
		Date d = c.getTime();
		return d;
	}

	public static Date beforeSecondMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		// 过去二月
		c.setTime(new Date());
		c.add(Calendar.MONTH, -2);
		Date d = c.getTime();
		return d;
	}

	public static Date beforeOneYear() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		// 过去一年
		c.setTime(new Date());
		c.add(Calendar.YEAR, -1);
		Date d = c.getTime();
		return d;
	}

	public static boolean isExistDelay(Date max, Date min, int delayTime) {
		Calendar dateOne = Calendar.getInstance();
		Calendar dateTwo = Calendar.getInstance();
		dateOne.setTime(max);// 设置为当前系统时间
		dateTwo.setTime(min);// 获取数据库中的时间
		long timeOne = dateOne.getTimeInMillis();
		long timeTwo = dateTwo.getTimeInMillis();
		long minute = (timeOne - timeTwo) / (1000 * 60);// 转化minute
		if (minute >= delayTime) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isGreateThanThirtySec(Date max, Date min) {
		Calendar dateOne = Calendar.getInstance();
		Calendar dateTwo = Calendar.getInstance();
		dateOne.setTime(max);// 设置为当前系统时间
		dateTwo.setTime(min);// 获取数据库中的时间
		long timeOne = dateOne.getTimeInMillis();
		long timeTwo = dateTwo.getTimeInMillis();
		long minute = (timeOne - timeTwo) / (1000);// 转化second
		if (minute > 60) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 日期加秒数计算
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date addSeconds(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, n);
		return cal.getTime();
	}

	public static String getLastMonth(String dateStr, int addYear, int addMonth, int addDate) throws Exception {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date sourceDate = sdf.parse(dateStr);
			Calendar cal = Calendar.getInstance();
			cal.setTime(sourceDate);
			cal.add(Calendar.YEAR, addYear);
			cal.add(Calendar.MONTH, addMonth);
			cal.add(Calendar.DATE, addDate);

			SimpleDateFormat returnSdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String dateTmp = returnSdf.format(cal.getTime());
			return dateTmp;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	public static int getCurrentMonth() {
		Calendar cale = Calendar.getInstance();
		int month = cale.get(Calendar.MONTH) + 1;
		return month;
	}

	public static String[] getLastYearMonth() {
		String[] last12Months = new String[12];
		Calendar cal = Calendar.getInstance();
		// 如果当前日期大于二月份的天数28天或者29天会导致计算月份错误，会多出一个三月份，故设置一个靠前日期解决此问题
		cal.set(Calendar.DAY_OF_MONTH, 1);
		for (int i = 0; i < 12; i++) {
			String month = (cal.get(Calendar.MONTH) + 1) + "";
			if (month.length() == 1) {
				month = "0" + month;
			}
			last12Months[11 - i] = cal.get(Calendar.YEAR) + "-" + month;
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1); // 逐次往前推1个月
		}
		return last12Months;
	}

	public static Integer getCurrentHours() {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		return hour;
	}

	public static ArrayList<String> getDays(int intervals, String dateformat) {
		ArrayList<String> pastDaysList = new ArrayList<>();
		for (int i = intervals - 1; i >= 0; i--) {
			pastDaysList.add(getPastDate(i, dateformat));
		}
		return pastDaysList;
	}

	public static String getPastDate(int past, String dateformat) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat(dateformat);
		String result = format.format(today);
		return result;
	}

	public static int getWeek() {
		Calendar calendar = Calendar.getInstance();
		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 7;
		}
		return w;
	}

	public static int getWeekByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 7;
		}
		return w;
	}

	public static Date getToday() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date zero = calendar.getTime();
		return zero;
	}

	public static Date getDateToday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date zero = calendar.getTime();
		return zero;
	}

	public static Date getTom(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		date = calendar.getTime();
		return date;
	}

	public static boolean isSameDay(Date date1, Date date2) {
		if (date1 != null && date2 != null) {
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date1);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date2);
			return isSameDay(cal1, cal2);
		} else {
			throw new IllegalArgumentException("The date must not be null");
		}
	}

	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		if (cal1 != null && cal2 != null) {
			return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
		} else {
			throw new IllegalArgumentException("The date must not be null");
		}
	}

	public static Date getHourLate(Date date, int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hour);
		return calendar.getTime();
	}
	
	public static Date getDayLate(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, day);
		return calendar.getTime();
	}
	
	public static Date getMonthLate(Date date, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}

	public static Date getMinuteLate(Date date, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}

	public static Date getMinuteBefore(Date date, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, -30);
		return calendar.getTime();

	}

	public static boolean before(Date date1, Date date2) {
		if (date1.before(date2)) {
			return true;
		} else {
			return false;
		}
	}

	public static Date getNextWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH) + 1);
		calendar.set(Calendar.DAY_OF_WEEK, 2);
		Date zero = calendar.getTime();
		return DateTimeToToday(zero);
	}

	public static Date getCurrentWeekLastDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH) + 1);
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		Date zero = calendar.getTime();
		return DateTimeToToday(zero);
	}

	public static Date getMonthBegin(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date zero = calendar.getTime();
		return DateTimeToToday(zero);
	}
	
	public static Date getCurrentMonthBegin(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date zero = calendar.getTime();
		return DateTimeToToday(zero);
	}

	public static Date getNextMonthBegin(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date zero = calendar.getTime();
		return DateTimeToToday(zero);
	}

	public static Date getYearBegin(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date zero = calendar.getTime();
		return DateTimeToToday(zero);
	}
	
	public static Integer getYear(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	public static Date getHalfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (calendar.get(Calendar.MONTH) > 5) {
			calendar.set(Calendar.MONTH, 6);
		} else {
			calendar.set(Calendar.MONTH, 0);
		}
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date zero = calendar.getTime();
		return DateTimeToToday(zero);
	}
	
	public static String getTimeByDate(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		return hour+":"+minute+":"+second;
	}
	
	public static Date getMonthEnd(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date zero = c.getTime();
		return DateTimeToToday(zero);
	}

	public static Date getMonthBeginHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 0);
		;
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date zero = calendar.getTime();
		return zero;
	}

	public static Date getMonthEndHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		// 将小时至23
		c.set(Calendar.HOUR_OF_DAY, 23);
		// 将分钟至59
		c.set(Calendar.MINUTE, 59);
		// 将秒至59
		c.set(Calendar.SECOND, 59);
		// 将毫秒至999
		c.set(Calendar.MILLISECOND, 999);
		Date zero = c.getTime();
		return zero;
	}

	public static boolean getBig(Date date1, Date date2) {
		if (date1 != null && date2 != null) {
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date1);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date2);
			int h1 = cal1.get(Calendar.HOUR_OF_DAY);
			int m1 = cal1.get(Calendar.MINUTE);
			int s1 = cal1.get(Calendar.SECOND);
			int h2 = cal2.get(Calendar.HOUR_OF_DAY);
			int m2 = cal2.get(Calendar.MINUTE);
			int s2 = cal2.get(Calendar.SECOND);
			if (h1 > h2) {
				return true;
			}
			if (h1 < h2) {
				return false;
			}
			if (m1 > m2) {
				return true;
			}
			if (m1 < m2) {
				return false;
			}
			if (s1 >= s2) {
				return true;
			}
			if (s1 < s2) {
				return false;
			}
			return true;
		} else {
			throw new IllegalArgumentException("The date must not be null");
		}
	}

	public static Date getFourHourAgo(Date date, Integer i) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) - i);
		return c.getTime();
	}

	public static boolean validMint(Date date) {
		long old = date.getTime();
		long now = System.currentTimeMillis();
		long dif = now - old;
		int min = (int) dif / (1000 * 60);
		if (min >= 5) {
			return true;
		}
		return false;
	}

	public static boolean validFourHour(Date date, Date date1) {
		long old = date.getTime();
		long now = date1.getTime();
		long dif = now - old;
		int hour = (int) dif / (1000 * 60 * 60);
		if (hour >= 4) {
			return true;
		}
		return false;
	}

	public static Integer getHours(Date date, Date date1) {
		long one = date.getTime();
		long two = date1.getTime();
		long dif = one - two;
		int hour = (int) dif / (1000 * 60 * 60);
		return hour;
	}

	public static boolean validHour(Date date, Date date1) {
		long old = date.getTime();
		long now = date1.getTime();
		long dif = now - old;
		int hour = (int) dif / (1000 * 60 * 60);
		if (hour >= 16) {
			return true;
		}
		return false;
	}

	public static Date setDate(Date date1, Date dtae2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(dtae2);
		int hour = c2.get(Calendar.HOUR_OF_DAY);// 小时
		int minute = c2.get(Calendar.MINUTE);// 分
		int second = c2.get(0);// 秒
		c1.set(Calendar.HOUR_OF_DAY, hour);
		c1.set(Calendar.MINUTE, minute);
		c1.set(Calendar.SECOND, second);
		return c1.getTime();
	}

	public static String dateDiff(Date start, Date end) {
		try {
			long diff = end.getTime() - start.getTime();// 这样得到的差值是毫秒级别
			long days = diff / (1000 * 60 * 60 * 24);

			long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
			long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
			if (days == 0) {
				return hours + "时" + minutes + "分";
			}
			return days + "天" + hours + "时" + minutes + "分";
		} catch (Exception e) {
		}
		return null;
	}

	public static Date getDayOfNum(int num, Date date) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date);
		int hour = c2.get(Calendar.HOUR_OF_DAY);// 小时
		int minute = c2.get(Calendar.MINUTE);// 分
		// int second = c2.get(Calendar.SECOND);// 秒
		c1.set(Calendar.YEAR, 2020);
		c1.add(Calendar.MONTH, 0);
		c1.set(Calendar.MONTH, 2);
		int month = 0;
		switch (num) {
		case 1:
			month = 16;
			break;
		case 2:
			month = 17;
			break;
		case 3:
			month = 18;
			break;
		case 4:
			month = 19;
			break;
		case 5:
			month = 20;
			break;
		case 6:
			month = 21;
			break;
		case 7:
			month = 22;
			break;
		case 8:
			month = 23;
			break;
		}
		if (month == 0) {
			return null;
		}
		c1.set(Calendar.DAY_OF_MONTH, month);
		c1.set(Calendar.HOUR_OF_DAY, hour);
		c1.set(Calendar.MINUTE, minute);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);
		return c1.getTime();
	}

	public static Date getHourAgo(Date date, int hour) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + hour);
		return c.getTime();
	}

	public static Date getMin(Date date, int min) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, min);
		return c.getTime();
	}

	public static Date getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		// 设置月份
		cal.set(Calendar.MONTH, month - 1);
		// 获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		// 格式化日期
		Date lastDayOfMonth = DateTimeToToday(cal.getTime());
		return lastDayOfMonth;
	}

	public static Date getFirstDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		// 设置月份
		cal.set(Calendar.MONTH, month - 1);
		// 获取某月最小天数
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
		// 格式化日期
		Date firstDayOfMonth = DateTimeToToday(cal.getTime());
		return firstDayOfMonth;
	}

	public static String dateToyyyymmddhhmmss(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String year = cal.get(Calendar.YEAR) + "";
		String month = cal.get(Calendar.MONTH) + 1 + "";
		if (month.length() == 1) {
			month = "0" + month;
		}
		String day = cal.get(Calendar.DAY_OF_MONTH) + "";
		if (day.length() == 1) {
			day = "0" + day;
		}
		String hour = cal.get(Calendar.HOUR_OF_DAY) + "";
		if (hour.length() == 1) {
			hour = "0" + hour;
		}
		String mintue = cal.get(Calendar.MINUTE) + "";
		if (mintue.length() == 1) {
			mintue = "0" + mintue;
		}
		String sencond = cal.get(Calendar.SECOND) + "";
		if (sencond.length() == 1) {
			sencond = "0" + sencond;
		}
		return "" + year + month + day + hour + mintue + sencond;
	}

	public static String _dateToyyyymmdd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String year = cal.get(Calendar.YEAR) + "";
		String month = cal.get(Calendar.MONTH) + 1 + "";
		if (month.length() == 1) {
			month = "0" + month;
		}
		String day = cal.get(Calendar.DAY_OF_MONTH) + "";
		if (day.length() == 1) {
			day = "0" + day;
		}
		return "" + year + month + day;
	}

	public static LocalDateTime dateLocalDateTime(Date date) {
		Instant instant = date.toInstant();// An instantaneous point on the
											// time-line.(时间线上的一个瞬时点。)
		ZoneId zoneId = ZoneId.systemDefault();// A time-zone ID, such as {@code
												// Europe/Paris}.(时区)
		LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
		return localDateTime;
	}

	public static Date getNeedTime(int hour, int minute, int second, int addDay, int... args) {
		Calendar calendar = Calendar.getInstance();
		if (addDay != 0) {
			calendar.add(Calendar.DATE, addDay);
		}
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		if (args.length == 1) {
			calendar.set(Calendar.MILLISECOND, args[0]);
		}
		return calendar.getTime();
	}

	public static Date setDayOfMonthToToday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, nowCalendar.get(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	public static Date setDayOfCurMonthToToday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(new Date());
		calendar.set(Calendar.YEAR, nowCalendar.get(Calendar.YEAR));
		calendar.set(Calendar.MONTH, nowCalendar.get(Calendar.MONTH));
		calendar.set(Calendar.DAY_OF_MONTH, nowCalendar.get(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	public static Integer getDateHoursDifference(Date startTime, Date endTime) {
		long between = (endTime.getTime() - startTime.getTime()) / 1000;
		long hour = (between / 60) / 60;
		return Integer.parseInt(String.valueOf(hour));
	}

	public static Date d(String s) {
		Long aLong = Long.valueOf(s);
		Date date = new Date(aLong);
		return date;
	}

	public static int getDayNum(Date date1, Date date2) {
		long old = date1.getTime();
		long now = date2.getTime();
		long dif = now - old;
		long i = dif / (1000 * 60 * 60 * 24);
		// int day = (int) dif / (1000 * 60 * 60 * 24);
		int day = (int) i;
		if (dif % (1000 * 60 * 60 * 24) != 0) {
			day = day + 1;
		}
		return day;
	}
	
	public static List<String> getMonthList(Date startDate, Date endDate){

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            // 获取开始年份和开始月份
            int startYear = calendar.get(Calendar.YEAR);
            int startMonth = calendar.get(Calendar.MONTH);
            // 获取结束年份和结束月份
            calendar.setTime(endDate);
            int endYear = calendar.get(Calendar.YEAR);
            int endMonth = calendar.get(Calendar.MONTH);
            //
            List<String> list = new ArrayList<String>();
            for (int i = startYear; i <= endYear; i++) {
                String date = "";
                if (startYear == endYear) {
                    for (int j = startMonth; j <= endMonth; j++) {
                        if (j < 9) {
                            date = i + "-0" + (j + 1);
                        } else {
                            date = i + "-" + (j + 1);
                        }
                        list.add(date);
                    }

                } else {
                    if (i == startYear) {
                        for (int j = startMonth; j < 12; j++) {
                            if (j < 9) {
                                date = i + "-0" + (j + 1);
                            } else {
                                date = i + "-" + (j + 1);
                            }
                            list.add(date);
                        }
                    } else if (i == endYear) {
                        for (int j = 0; j <= endMonth; j++) {
                            if (j < 9) {
                                date = i + "-0" + (j + 1);
                            } else {
                                date = i + "-" + (j + 1);
                            }
                            list.add(date);
                        }
                    } else {
                        for (int j = 0; j < 12; j++) {
                            if (j < 9) {
                                date = i + "-0" + (j + 1);
                            } else {
                                date = i + "-" + (j + 1);
                            }
                            list.add(date);
                        }
                    }

                }

            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}

	public static String dateTo(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String year = cal.get(Calendar.YEAR) + "";
		String month = cal.get(Calendar.MONTH) + 1 + "";
		if (month.length() == 1) {
			month = "0" + month;
		}
		String day = cal.get(Calendar.DAY_OF_MONTH) + "";
		if (day.length() == 1) {
			day = "0" + day;
		}
		return year + "-" + month + "-" + day;
	}

	public static boolean isCurMonthWeekend(Date date) {
		Calendar weekEndCalendar = Calendar.getInstance();
		weekEndCalendar.setTime(DateFormatUtil.getMonthEnd(new Date()));
		Integer WeekendNum = weekEndCalendar.get(Calendar.WEEK_OF_MONTH);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Integer curWeekNum = calendar.get(Calendar.WEEK_OF_MONTH);
		if (curWeekNum == WeekendNum) {
			return true;
		}
		return false;
	}

	public static boolean isCurYearMonthend(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int i = calendar.get(Calendar.MONTH);
		if (i == 11) {
			return true;
		}
		return false;
	}

	public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);

		Calendar begin = Calendar.getInstance();
		begin.setTime(beginTime);

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);

		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}

	public static Integer getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
	}

	public static String dateToStamp(Date d) {
		String res;
		long ts = d.getTime();
		res = String.valueOf(ts);
		return res;
	}
	public static int getDayNums(Date date1,Date date2){
		long old = date1.getTime();
		long now = date2.getTime();
		long dif = now - old;
		long i = dif / (1000 * 60 * 60 * 24 * 15);
		//int day = (int) dif / (1000 * 60 * 60 * 24);
		int day = (int) i;
		if (dif % (1000 * 60 * 60 * 24 * 15) != 0){
			day = day + 1;
		}
		return day;
	}
	
	public static int getCurMonthDaysNum(){
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	public static boolean alarmValid(){
		Calendar a = Calendar.getInstance();
		boolean b = false;
		Calendar start = Calendar.getInstance();
		start.set(Calendar.HOUR_OF_DAY, 8);
		start.set(Calendar.MINUTE, 30);
		start.set(Calendar.SECOND, 0);
		Calendar end = Calendar.getInstance();
		end.set(Calendar.HOUR_OF_DAY, 16);
		end.set(Calendar.MINUTE, 30);
		end.set(Calendar.SECOND, 0);
		if (start.before(a) && a.before(end)){
			b = true;
		}
		return b;
	}

	public static String monthToDateStr(Integer month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.MONTH,month - 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(a.getTime());
	}

}
