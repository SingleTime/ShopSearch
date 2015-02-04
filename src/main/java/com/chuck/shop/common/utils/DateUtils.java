/**
 * DateUtil.java 
 * Copyright © 2009-2013, 杭州跃度网络科技有限公司
 * 
 * @author Liu Tao
 * @create 2013年8月9日
 */
package com.chuck.shop.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TimeZone;

public class DateUtils {
	/**
	 * 默认的日期格式
	 */
	public static String SHORT_DATE_FORMAT = "yyyy-MM";// 超短日期格式

	public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	public static String DEFAULT_DATE_CN = "yyyy年MM月dd日";

	public static String LONG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static String NUMBER_DATE_FORMAT = "yyyyMMddHHmm";

	public static String NUMBER_DATE_NO_HOUR_FORMAT = "yyyyMMdd";

	public static String LONG_DATE_FORMAT_MINUTE = "yyyy-MM-dd HH:mm";

	public static String SEALIONX_FORMAT = "MM/dd/yyyy HH:mm:ss";

	public static String SEALIONX_DATE_FORMAT = "MM/dd/yyyy";

	public static String MDT_FORMAT = "MM/dd HH:mm";
	
	public static String HOUR_FORMAT = "HH:mm";

	private static final String DEFAULT_DATE = "2000-01-01";

	// 1970年1月1日 (格里高利历)
	public static final int EPOCH_JULIAN_DAY = 2440588;

	// 第1年1月1日 (格里高利历)
	public static final int JAN_1_1_JULIAN_DAY = 1721426;

	// 一周、一天、一小时、一分钟、一秒，换算成毫秒的全局变量。
	public static final int ONE_SECOND = 1000;

	public static final int ONE_MINUTE = 60 * ONE_SECOND;

	public static final int ONE_HOUR = 60 * ONE_MINUTE;

	public static final long ONE_DAY = 24 * ONE_HOUR;

	public static final long ONE_WEEK = 7 * ONE_DAY;

	/** 默认的时区，一般是使用东八区。即比标准的格林威治时间快8个小时。 */
	public static TimeZone TIME_ZONE = TimeZone.getDefault();

	/**
	 * 取得当前日期
	 * 
	 * @return Date 当前日期
	 */
	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 返回当前日期对应的默认格式的字符串
	 * 
	 * @return String 当前日期对应的字符串
	 */
	public static String getCurrentStringDate() {
		return convertDate2String(getCurrentDate(), DEFAULT_DATE_FORMAT);
	}

	public static String getCurrentCnDate() {
		return convertDate2String(getCurrentDate(), DEFAULT_DATE_CN);
	}

	public static String getLongDateFormat(Date date) {
		return convertDate2String(date, LONG_DATE_FORMAT_MINUTE);
	}

	public static String getCurrentFirstDate() {
		String date = getCurrentStringDate();
		return date.substring(0, 8) + "01";
	}

	public static String getCurrentPureYMD() {
		return convertDate2String(getCurrentDate(), NUMBER_DATE_NO_HOUR_FORMAT);
	}

	/**
	 * 返回当前日期对应的指定格式的字符串
	 * 
	 * @param dateFormat - 日期格式
	 * @return String 当前日期对应的字符串
	 */
	public static String getCurrentStringDate(String dateFormat) {
		return convertDate2String(getCurrentDate(), dateFormat);
	}

	/**
	 * 将日期转换成指定格式的字符串
	 * 
	 * @param date - 要转换的日期
	 * @param dateFormat - 日期格式
	 * @return String 日期对应的字符串
	 */
	public static String convertDate2String(Date date, String dateFormat) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = null;
		if (dateFormat != null && !dateFormat.equals("")) {
			try {
				sdf = new SimpleDateFormat(dateFormat);
			} catch (Exception e) {
				// e.printStackTrace();
				sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			}
		} else {
			sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		}
		return sdf.format(date);
	}

	/**
	 * 将日期转换成指定格式的字符串
	 * 
	 * @param date - 要转换的日期
	 * @param dateFormat - 日期格式
	 * @return String 日期对应的字符串
	 */
	public static String convertDate2String(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat sdf = null;
		sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return sdf.format(date);
	}

	public static String convertDate2MDTString(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat sdf = null;
		sdf = new SimpleDateFormat(MDT_FORMAT);
		return sdf.format(date);
	}

	/**
	 * 将字符串转换成日期
	 * 
	 * @param stringDate - 要转换的字符串格式的日期
	 * @return Date 字符串对应的日期
	 */
	public static Date convertString2Date(String stringDate) {
		return convertString2Date(stringDate, DEFAULT_DATE_FORMAT);
	}

	public static Date convertString2FullDate(String stringDate) {
		return convertString2Date(stringDate, LONG_DATE_FORMAT);
	}

	public static Date convertString2SealionxDate(String stringDate) {
		return convertString2Date(stringDate, SEALIONX_FORMAT);
	}

	public static Date convertString2SealionxOnlyDate(String stringDate) {
		return convertString2Date(stringDate, SEALIONX_DATE_FORMAT);
	}

	public static Date convertString2LongMinuteDate(String stringDate) {
		return convertString2Date(stringDate, LONG_DATE_FORMAT_MINUTE);
	}

	public static Date convertHourMinuteString2LongDate(String stringDate) {
		if (StringUtils.isBlank(stringDate)) {
			return null;
		}

		return convertString2Date(DEFAULT_DATE + " " + stringDate, LONG_DATE_FORMAT_MINUTE);
	}

	/**
	 * 将字符串转换成日期
	 * 
	 * @param stringDate - 要转换的字符串格式的日期
	 * @param dateFormat - 要转换的字符串对应的日期格式
	 * @return Date 字符串对应的日期
	 */
	public static Date convertString2Date(String stringDate, String dateFormat) {
		if (StringUtils.isEmpty(stringDate)) {
			return null;
		}
		SimpleDateFormat sdf = null;
		if (dateFormat != null && !dateFormat.equals("")) {
			try {
				sdf = new SimpleDateFormat(dateFormat);
			} catch (Exception e) {
				e.printStackTrace();
				sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			}
		} else {
			sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		}
		try {
			return sdf.parse(stringDate);
		} catch (ParseException pe) {
			pe.printStackTrace();
			return null;
		}
	}

	/**
	 * 将一种格式的日期字符串转换成默认格式的日期字符串
	 * 
	 * @param oldDate - 要格式化的日期字符串
	 * @param oldFormat - 要格式化的日期的格式
	 * @return String 格式化后的日期字符串
	 */
	public static String formatStringDate(String oldStringDate, String oldFormat) {
		return convertDate2String(convertString2Date(oldStringDate, oldFormat), DEFAULT_DATE_FORMAT);
	}

	/**
	 * 将一种格式的日期字符串转换成另一种格式的日期字符串
	 * 
	 * @param oldDate - 要格式化的日期字符串
	 * @param oldFormat - 要格式化的日期的格式
	 * @param newFormat - 格式化后的日期的格式
	 * @return String 格式化后的日期字符串
	 */
	public static String formatStringDate(String oldStringDate, String oldFormat, String newFormat) {
		return convertDate2String(convertString2Date(oldStringDate, oldFormat), newFormat);
	}

	/**
	 * 根据年份和月份判断该月有几天
	 * 
	 * @param year - 年份
	 * @param month - 月份
	 * @return int
	 */
	public static int days(int year, int month) {
		int total = 30;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			total = 31;
			break;
		case 2:
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
				total = 29;
			else
				total = 28;
			break;
		default:
			break;
		}
		return total;
	}

	public static String getCurrDateByMonth(String date) {
		int day = days(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)));
		return date + "-" + day;
	}

	/**
	 * 根据年份和月份判断该月的第一天是星期几
	 * 
	 * @param year - 年份
	 * @param month - 月份
	 * @return int
	 */
	@SuppressWarnings("deprecation")
	public static int firstDayOfWeek(int year, int month) {
		Date firstDate = new Date(year - 1900, month - 1, 1);
		return firstDate.getDay();
	}

	/**
	 * 根据年份和月份判断该月的最后一天是星期几
	 * 
	 * @param year - 年份
	 * @param month - 月份
	 * @return int
	 */
	@SuppressWarnings("deprecation")
	public static int lastDayOfWeek(int year, int month) {
		Date lastDate = new Date(year - 1900, month - 1, days(year, month));
		return lastDate.getDay();
	}

	/**
	 * 给定一个日期,返回是一周中的第几天 星期日为每周的第一天,星期六为每周的最后一天
	 * */
	public static int dayOfWeek(Date date) {
		// 首先定义一个calendar，必须使用getInstance()进行实例化
		Calendar aCalendar = Calendar.getInstance();
		// 里面野可以直接插入date类型
		aCalendar.setTime(date);
		// 计算此日期是一周中的哪一天
		int x = aCalendar.get(Calendar.DAY_OF_WEEK);
		return x;
	}
	
	/**
	 * 获取今天周几
	 * @param date
	 * @return
	 */
	public static int getWeekNum(Date date) {
		int weekNum;
		int dayOfWeek=dayOfWeek(date);
		if(dayOfWeek==1){
			weekNum=7;
		}else if(dayOfWeek==7){
			weekNum=6;
		}else{
			weekNum=dayOfWeek-1;
		}
		return weekNum;
	}
	 
	/**
	 * 获取当前年份
	 * 
	 * @return int
	 */
	@SuppressWarnings("deprecation")
	public static int getCurrentYear() {
		return getCurrentDate().getYear() + 1900;
	}

	/**
	 * 获取当前月份(1-12)
	 * 
	 * @return int
	 */
	@SuppressWarnings("deprecation")
	public static int getCurrentMonth() {
		return getCurrentDate().getMonth();
	}

	@SuppressWarnings("deprecation")
	public static int getCurrentDay() {
		return getCurrentDate().getDay();
	}

	@SuppressWarnings("deprecation")
	public static String getCurrentMonthFormat() {
		int i = getCurrentDate().getMonth() + 1;
		if (i < 10) {
			return "0" + i;
		}
		return String.valueOf(i);
	}

	public static String getCurrentMonth2String() {
		String str = getCurrentStringDate();
		return str.substring(0, 7) + "-01";
	}

	@SuppressWarnings("deprecation")
	public static int getMonth(int i) {
		return getCurrentDate().getMonth();
	}

	public static int getWeek(Date d) {
		java.util.Date lastweek = d;
		Calendar c = Calendar.getInstance();
		c.setTime(lastweek);
		return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取给定日期的下一个月的日期，返回格式为yyyy-MM-dd
	 * 
	 * @param dateStr - 给定日期
	 * @param format - 给定日期的格式
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	public static String getNextMonth(String stringDate, String format) {
		Date date = convertString2Date(stringDate, format);
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		if (month == 12) {
			year = year + 1;
			month = 1;
		} else {
			month = month + 1;
		}
		return year + "-" + (month < 10 ? "0" : "") + month + "-01";
	}

	/**
	 * 获取给定日期的下一个月的日期，返回格式为yyyy-MM-dd
	 * 
	 * @param dateStr - 给定日期
	 * @return String
	 */
	public static String getNextMonth(String stringDate) {
		return getNextMonth(stringDate, DEFAULT_DATE_FORMAT);
	}

	/**
	 * 获取给定日期的前一天
	 * 
	 * @param stringDate - 给定日期
	 * @param format - 给定日期的格式
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	public static String getBeforDate(String stringDate, String format) {
		Date date = convertString2Date(stringDate, format);
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		int day = date.getDate();
		if (day == 1) {
			if (month == 1) {
				return (year - 1) + "-12-31";
			} else {
				month = month - 1;
				day = days(year, month);
				return year + "-" + (month < 10 ? "0" : "") + month + "-" + day;
			}
		} else {
			return year + "-" + (month < 10 ? "0" : "") + month + "-" + (day < 11 ? "0" : "") + (day - 1);
		}
	}

	/**
	 * 获取给定日期的前一天
	 * 
	 * @param stringDate - 给定日期
	 * @return String
	 */
	public static String getBeforDate(String stringDate) {
		return getBeforDate(stringDate, DEFAULT_DATE_FORMAT);
	}

	/**
	 * 得到当前日期的前后日期 +为后 -为前
	 * 
	 * @param day_i
	 * @return
	 */
	public static final String getBefDateString(String currentDate, int day_i, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = sdf.parse(currentDate);
			Calendar day = Calendar.getInstance();
			day.setTime(date);
			day.add(Calendar.DATE, day_i);
			return sdf.format(day.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static final String getBefDateString(String currentDate, int day_i) {
		return getBefDateString(currentDate, day_i, DEFAULT_DATE_FORMAT);
	}

	public static String getCurrWeek() {
		Calendar c = Calendar.getInstance();
		int w = c.get(Calendar.WEEK_OF_YEAR);
		if (w < 10) {
			return "0" + w;
		}
		return String.valueOf(w);
	}

	/**
	 * 得到一个月的天数
	 */
	public static int getMonthDays(String dt) {
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(dt.substring(0, 4)), Integer.parseInt(dt.substring(5, 7)) - 1, 1);

		int num = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		return num;
	}

	/**
	 * 得到一个月的天数
	 */
	@SuppressWarnings("static-access")
	public static int getMonthDays(String dt, String format) {
		try {
			if (dt == null) {
				throw new NullPointerException("日期参数为null");
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				Date date = sdf.parse(dt);
				Calendar cld = Calendar.getInstance();
				cld.setTime(date);
				return cld.getActualMaximum(cld.DAY_OF_MONTH);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * 得到当前日期的星期
	 * 
	 * @param date
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static final String getDateWeek(String currentDate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = sdf.parse(currentDate);
			Integer i = date.getDay();
			if (i == 0)
				i = 7;
			return i.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static final char[] zeroArray = "0000000000".toCharArray();

	/**
	 * 得到指定长度格式的字符串 ‘000000123456789’
	 * 
	 * @param string
	 * @param length
	 * @return
	 */
	public static final String zeroPadString(String string, int length) {
		if (string == null || string.length() > length) {
			return string;
		}
		StringBuilder buf = new StringBuilder(length);
		buf.append(zeroArray, 0, length - string.length()).append(string);
		return buf.toString();
	}

	/** 得到这个月的第一天 **/
	public static Date getMonthFirstDay(Date date) {
		String strdate = convertDate2String(date);
		strdate = strdate.substring(0, 8) + "01";
		return convertString2Date(strdate);
	}

	/**
	 * 得到这个月的第一天 2009-08-01
	 * **/
	public static String getMonthFirstDay2String() {
		String strdate = convertDate2String(new Date());
		strdate = strdate.substring(0, 4) + "-" + strdate.substring(5, 7) + "-01";
		return strdate;
	}

	/** 秒数转化为小时格式 HH:MM:SS **/
	public static String convertSecToHour(int sec) {
		String time = "";
		int hour = 0;
		int minute = 0;
		int second = 0;
		hour = sec / 3600 > 0 ? sec / 3600 : 0;
		minute = (sec - hour * 3600) / 60 > 0 ? (sec - hour * 3600) / 60 : 0;
		second = sec - hour * 3600 - minute * 60 > 0 ? sec - hour * 3600 - minute * 60 : 0;
		String shour = String.valueOf(hour).length() < 2 ? "0" + String.valueOf(hour) : String.valueOf(hour);
		String sminute = String.valueOf(minute).length() < 2 ? "0" + String.valueOf(minute) : String.valueOf(minute);
		String ssecond = String.valueOf(second).length() < 2 ? "0" + String.valueOf(second) : String.valueOf(second);
		time = shour + ":" + sminute + ":" + ssecond;
		return time;
	}

	/**
	 * Formats a Date as a fifteen character long String made up of the Date's
	 * padded millisecond value.
	 */
	public static final String dateToMillis(Date date) {
		return zeroPadString(Long.toString(date.getTime()), 15);
	}

	public static final Date millisToDate(String stime) {
		long time = Long.parseLong(stime);
		return new Date(time);
	}

	SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	SimpleDateFormat sFullFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static final String DATE_SEPARATOR = "-/";

	/** 作日期分析之用 */
	static StringTokenizer sToken;

	/** 将日期变为字符串格式 * */
	public String format(GregorianCalendar pCal) {
		return sDateFormat.format(pCal.getTime());
	}

	public String format(Date pDate) {
		return sDateFormat.format(pDate);
	}

	public String fullFormat(Date pDate) {
		return sFullFormat.format(pDate);
	}

	/** 将字符串格式的日期转换为Calender* */
	public static GregorianCalendar parse2Cal(String pDateStr) {
		sToken = new StringTokenizer(pDateStr, DATE_SEPARATOR);
		int vYear = Integer.parseInt(sToken.nextToken());
		// GregorianCalendar的月份是从0开始算起的，变态！！
		int vMonth = Integer.parseInt(sToken.nextToken()) - 1;
		int vDayOfMonth = Integer.parseInt(sToken.nextToken());
		return new GregorianCalendar(vYear, vMonth, vDayOfMonth);
	}

	/** 将字符串类型的日期(yyyy-MM-dd)转换成Date* */
	public Date parse2Date(String pDate) {
		try {
			return sDateFormat.parse(pDate);
		} catch (ParseException ex) {
			return null;
		}
	}

	/** 给定两个时间相差的月数,String版* */
	public static int monthsBetween(String pFormerStr, String pLatterStr) {
		GregorianCalendar vFormer = parse2Cal(pFormerStr);
		GregorianCalendar vLatter = parse2Cal(pLatterStr);
		return monthsBetween(vFormer, vLatter);
	}

	@SuppressWarnings("static-access")
	public static int monthsBetween(GregorianCalendar pFormer, GregorianCalendar pLatter) {
		GregorianCalendar vFormer = pFormer, vLatter = pLatter;
		boolean vPositive = true;
		if (pFormer.before(pLatter)) {
			vFormer = pFormer;
			vLatter = pLatter;
		} else {
			vFormer = pLatter;
			vLatter = pFormer;
			vPositive = false;
		}

		int vCounter = 0;
		while (vFormer.get(vFormer.YEAR) != vLatter.get(vLatter.YEAR)
				|| vFormer.get(vFormer.MONTH) != vLatter.get(vLatter.MONTH)) {
			vFormer.add(Calendar.MONTH, 1);
			vCounter++;
		}
		if (vPositive)
			return vCounter;
		else
			return -vCounter;
	}

	// 年,月,日
	public static int[] getYMD(String str) {
		int re[] = null;
		if (str.length() >= 10) {
			re = new int[3];
			re[0] = Integer.parseInt(str.substring(0, 4));
			re[1] = Integer.parseInt(str.substring(5, 7));
			re[2] = Integer.parseInt(str.substring(8, 10));

		} else if (str.length() >= 7) {
			re = new int[2];
			re[0] = Integer.parseInt(str.substring(0, 4));
			re[1] = Integer.parseInt(str.substring(5, 7));

		}
		if (str.length() == 4) {
			re = new int[1];
			re[0] = Integer.parseInt(str.substring(0, 4));

		}
		return re;
	}

	@SuppressWarnings("deprecation")
	public static String getPreMonth(String stringDate, String format) {
		Date date = convertString2Date(stringDate, format);
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		if (month == 1) {
			year = year - 1;
			month = 12;
		} else {
			month = month - 1;
		}
		return year + "-" + (month < 10 ? "0" : "") + month;
	}

	@SuppressWarnings("deprecation")
	public static String getNowMonth() {
		Date date = new Date();
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		return year + "-" + (month < 10 ? "0" : "") + month;
	}

	public static long getRuntime(long begintime) {
		return ((System.currentTimeMillis() - begintime)) / 1000;
	}

	public static long getRuntimeMin(long begintime) {
		return ((System.currentTimeMillis() - begintime)) / 60000;
	}

	public static String getCurrentYMD() {
		return getCurrentStringDate().substring(0, 7) + "-01";
	}

	/**
	 * 获取工单完成时间 取最大的时间
	 * 
	 * */
	public static Date getMaxDate(List<Date> datelist) {
		if (datelist != null && datelist.size() > 0) {
			Date darray[] = new Date[datelist.size()];
			for (int i = 0; i < datelist.size(); i++) {
				darray[i] = datelist.get(i);
			}
			Date maxDate = darray[0];

			for (int i = 0; i < darray.length; i++) {
				if (darray[i] != null && maxDate != null && (darray[i].getTime() > maxDate.getTime()))
					maxDate = darray[i];
			}

			return maxDate;
		} else
			return null;
	}

	/**
	 * 工作日
	 * */
	public static int workDays(Date d1, Date d2) {
		Date d = d1.before(d2) ? d1 : d2;
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		int count = 0;
		while (!d.after(d2)) {
			int day = cal.get(Calendar.DAY_OF_WEEK);
			if (day < 7 && day > 1)
				count++;
			cal.add(Calendar.DATE, 1);
			d = cal.getTime();
		}
		return count;
	}

	/**
	 * 获取当前时间所在的周的第一天是哪一天。
	 * 
	 * 
	 * */
	public static String getFirstWeekDay() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return (convertDate2String(cal.getTime()));
	}

	/**
	 * get the next day with date and given increment
	 * 
	 * @param date
	 * @param incr
	 * @return
	 */
	public static Date getNextDay(Date date, int incr) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, incr);
		return new Date(calendar.getTimeInMillis());
	}

	public static Date getMinimumTimeOfMonth(Date date) {
		checkNullDate(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int minimumDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, minimumDay);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	public static Date getMaximumTimeOfMonth(Date date) {
		checkNullDate(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int maximumDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, maximumDay);
		cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
		return cal.getTime();
	}

	public static Date getMinimumTimeOfQuarter(Date date) {
		checkNullDate(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		int quarter = (month / 3) + 1;
		int minMonth = (quarter * 3) - 3;
		cal.set(Calendar.MONTH, minMonth);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	private static void checkNullDate(Date date) {
		if (null == date)
			throw new NullPointerException("date");
	}

	public static Date getMaximumTimeOfQuarter(Date date) {
		checkNullDate(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		int quarter = (month / 3) + 1;
		int minMonth = (quarter * 3) - 1;
		cal.set(Calendar.MONTH, minMonth);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
		return cal.getTime();
	}

	public static Date getMinimumTimeOfYear(Date date) {
		checkNullDate(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, cal.getActualMinimum(Calendar.MONTH));
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	public static Date getMaximumTimeOfYear(Date date) {
		checkNullDate(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, cal.getActualMaximum(Calendar.MONTH));
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
		return cal.getTime();
	}

	/**
	 * 计算两个日期之间相差的天数。如果第一个日期在第二个日期之前，则返回正，反之返回负。
	 * 
	 * @param early 第一个日期
	 * @param late 第二个日期
	 * @return 两个日期之间相差的天数
	 */
	public static int daysBetween(Date early, Date late) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(early);
		c2.setTime(late);
		return dateToJulianDay(c2.getTime()) - dateToJulianDay(c1.getTime());
	}

	/**
	 * 将<code>java.util.Date</code>转换成格里高利历的日数。 日数是只从格里高利历第1年1月1日算起的日数。
	 * 
	 * @param date <code>java.util.Date</code>
	 * @return 格里高利历的日数
	 */
	public static int dateToJulianDay(Date date) {
		return millisToJulianDay(date.getTime());
	}

	/**
	 * 将毫秒时间戳转换成格里高利历的日数。日数是只从格里高利历第1年1月1日算起的日数。
	 * 
	 * @param millis 给定的毫秒时间戳
	 * @return 格里高利历的日数
	 */
	public static int millisToJulianDay(long millis) {
		return EPOCH_JULIAN_DAY - JAN_1_1_JULIAN_DAY + (int) (millis / ONE_DAY);
	}

	public static String dateToString(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(LONG_DATE_FORMAT);
		return df.format(date);
	}

	public static String dateToString(Date date, String simpleDateFormat) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(simpleDateFormat);
		return df.format(date);
	}

	public static Date stringToDate(String dateStr) throws ParseException {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(LONG_DATE_FORMAT);
		return df.parse(dateStr);
	}

	public static Date stringToDate(String dateStr, String simpleDateFormat) throws ParseException {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(simpleDateFormat);
		return df.parse(dateStr);
	}

	/**
	 * 根据日历的规则，为给定的日历字段添加或减去指定的时间量。
	 * 
	 * @param date <code>java.util.Date</code>
	 * @param field 给定的日历字段
	 * @param amount 数量
	 * @return 运算后的 <code>java.util.Date</code>
	 */
	public static Date add(Date date, int field, int amount) {
		if (date == null) {
			throw new RuntimeException("日期不能为空！");
		}
		Calendar cal = GregorianCalendar.getInstance(TIME_ZONE);
		cal.setTime(date);
		cal.add(field, amount);

		return cal.getTime();
	}

	/**
	 * 增加或减少指定数量的日数。
	 * 
	 * @param date <code>java.util.Date</code>
	 * @param amount 数量
	 * @return 运算后的 <code>java.util.Date</code>
	 */
	public static Date addDays(Date date, int amount) {
		return add(date, Calendar.DAY_OF_MONTH, amount);
	}

	/**
	 * 截取日期，只保留年、月、日部分。
	 * 
	 * @param date <code>java.util.Date</code>
	 * @return 截取后的<code>java.util.Date</code>
	 */
	public static Date trunc(Date date) {
		if (date != null) {
			Calendar c1 = Calendar.getInstance();
			c1.setTime(date);
			Calendar c2 = (Calendar) c1.clone();
			c1.clear();
			c1.set(Calendar.YEAR, c2.get(Calendar.YEAR));
			c1.set(Calendar.MONTH, c2.get(Calendar.MONTH));
			c1.set(Calendar.DAY_OF_MONTH, c2.get(Calendar.DAY_OF_MONTH));
			return c1.getTime();
		}
		return null;
	}

	/**
	 * 截取日期，只保留年、月部分。
	 * 
	 * @param date <code>java.util.Date</code>
	 * @return 截取后的<code>java.util.Date</code>
	 */
	public static Date truncToShort(Date date) {
		if (date != null) {
			Calendar c1 = Calendar.getInstance();
			c1.setTime(date);
			Calendar c2 = (Calendar) c1.clone();
			c1.clear();
			c1.set(Calendar.YEAR, c2.get(Calendar.YEAR));
			c1.set(Calendar.MONTH, c2.get(Calendar.MONTH));
			return c1.getTime();
		}
		return null;
	}

}
