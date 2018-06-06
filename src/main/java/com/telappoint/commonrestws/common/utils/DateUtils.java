package com.telappoint.commonrestws.common.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.telappoint.commonrestws.common.constants.CommonDateContants;

/**
 * @author Balaji
 * 
 */
public class DateUtils {
	private static final Logger logger = Logger.getLogger(DateUtils.class);

	private static Map<String, ThreadLocal<DateFormat>> tlDateFormatMap = new HashMap<String, ThreadLocal<DateFormat>>();
	
	public static String convert12To24HoursFormat(String twelveHourTime) throws ParseException {
		ThreadLocal<DateFormat> time12Format = getSimpleDateFormat(CommonDateContants.TIME_FORMAT_TWELVE_HRS.getValue());
		ThreadLocal<DateFormat> time24Format = getSimpleDateFormat(CommonDateContants.TIME_FORMAT_TWENTY_FOUR_HRS.getValue());
		return time24Format.get().format(time12Format.get().parse(twelveHourTime));
	}
	
	public static String convert12To24HoursHHMMSSFormat(String twelveHourTime) throws ParseException {
		ThreadLocal<DateFormat> time12Format = getSimpleDateFormat(CommonDateContants.TIME_FORMAT_TWELVE_HRS.getValue());
		ThreadLocal<DateFormat> time24Format = getSimpleDateFormat(CommonDateContants.TIME_FORMAT_HHMMSS_CAP.getValue());
		return time24Format.get().format(time12Format.get().parse(twelveHourTime));
	}
	
	
	public static String convert24To12HoursFormat(String twentyFourHourTime) throws ParseException {
		ThreadLocal<DateFormat> time24Format = getSimpleDateFormat(CommonDateContants.TIME_FORMAT_TWENTY_FOUR_HRS.getValue());
		ThreadLocal<DateFormat> time12Format = getSimpleDateFormat(CommonDateContants.TIME_FORMAT_TWELVE_HRS.getValue());
		return time12Format.get().format(time24Format.get().parse(twentyFourHourTime));
	}
	
	public static Timestamp getNextBlockTime(Timestamp timestamp, int blockMinutes) {
		Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        cal.add(Calendar.MINUTE, blockMinutes);
        Timestamp later = new Timestamp(cal.getTime().getTime());
        return later;
	}
	
	public static List<String> convert24To12HoursFormat(List<String> timeList,String timeZone) {
		List<String> time12List = new ArrayList<String>();
		for(String time: timeList) {
			try {
				time12List.add(DateUtils.convert24To12HoursFormat(time));
			} catch (ParseException e) {
				logger.error("Error:"+e,e);
			}
		}
		return time12List;
	}
	
	public static Calendar dateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static ThreadLocal<DateFormat> getSimpleDateFormat(String dateTimeFormatStr) {
		ThreadLocal<DateFormat> tldf = null;
		try {
			if (tlDateFormatMap.containsKey(dateTimeFormatStr)) {
				tldf = tlDateFormatMap.get(dateTimeFormatStr);
					return tldf;
			}
			tldf = getThreadLocal(dateTimeFormatStr);
			tlDateFormatMap.put(dateTimeFormatStr, tldf);
			return tldf;

		} catch (Exception e) {
			logger.error("Error:" + e, e);
		}
		return tldf;
	}

	public static ThreadLocal<DateFormat> getThreadLocal(final String dateTimeForamtStr) {
		final ThreadLocal<DateFormat> tldf_ = new ThreadLocal<DateFormat>() {
			@Override
			protected DateFormat initialValue() {
				return new SimpleDateFormat(dateTimeForamtStr);
			}
		};
		return tldf_;
	}
	
	// not using yet
	public static String getCurrentDate(String format, String timeZone) {
		String dateString = "";
		ThreadLocal<DateFormat> dateFormat = getSimpleDateFormat(format);
		GregorianCalendar gcAppt = new GregorianCalendar(TimeZone.getTimeZone(timeZone));
		dateFormat.get().setCalendar(gcAppt);
		try {
			dateString = dateFormat.get().format(gcAppt.getTime());
		} catch (Exception e) {
			logger.error("Error:" + e, e);
		}
		return dateString;
	}

	public static String getDateStrFrom(String timeZone, int apptDelayTimeDays, int appDelayTimeHours) {
		String dateTimeFormatStr = CommonDateContants.DATETIME_FORMAT_YYYYMMDDHHMMSS.getValue();
		Calendar calendar = getCalendarFrom(timeZone,apptDelayTimeDays,appDelayTimeHours);
		ThreadLocal<DateFormat> dateFormat = getSimpleDateFormat(dateTimeFormatStr);
		dateFormat.get().setCalendar(calendar);
		String currentDate = dateFormat.get().format(calendar.getTime());
		return currentDate;
	}
	
	public static Calendar getCalendarFrom(String timeZone, int apptDelayTimeDays, int appDelayTimeHours) {
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone(timeZone));
		calendar.add(Calendar.DATE, apptDelayTimeDays);
		calendar.add(Calendar.HOUR, appDelayTimeHours);
		return calendar;
	}

	public static String getDateStrTO(String timeZone, int maxApptDurationDays) {
		String dateTimeFormatStr = CommonDateContants.DATETIME_FORMAT_YYYYMMDDHHMMSS.getValue();
		Calendar calendar = getCalendarTO(timeZone,maxApptDurationDays);
		ThreadLocal<DateFormat> dateFormat = getSimpleDateFormat(dateTimeFormatStr);
		dateFormat.get().setCalendar(calendar);
		String date = dateFormat.get().format(calendar.getTime());
		return date;
	}
	
	public static Calendar getCalendarTO(String timeZone, int maxApptDurationDays) {
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone(timeZone));
		calendar.add(Calendar.DATE, maxApptDurationDays);
		return calendar;
	}

	/**
	 * @param dateGC
	 * @param formatDateOrTimeStr - date format might be any thing like
	 * eg: "yyyy-MM-dd" or HH:mm:ss or HH:mm(twenty four time format) or hh:mma (twelve time format)
	 * @return returns date string by formatting based on formatDateOrTimeStr format.  
	 */
	public static String formatGCDateToDateString(Calendar dateCalendar, String formatDateOrTimeStr) {
		String dateString = "";
		ThreadLocal<DateFormat> dateFormat = getSimpleDateFormat(formatDateOrTimeStr); 																
		dateFormat.get().setCalendar(dateCalendar);
		try {
			dateString = dateFormat.get().format(dateCalendar.getTime());
		} catch (Exception e) {
		}
		return dateString;
	}
	
	public static Set<String> getDateTimesSet(String dateTime,int blockInMins,int blocks) {
		Set<String> dateTimes = new HashSet<String>();
		dateTimes.add(dateTime);
		GregorianCalendar gcal = formatSqlStringToGC(dateTime);
		for(int i=1;i<blocks;i++) {
			addMinutesAndGetCalendar(gcal, blockInMins);
			dateTimes.add(formatGCDateToYYYYMMDD(gcal)+" "+formatGCDateToHH_MM_SS(gcal));
		}
		return dateTimes;
	}
	

	/** dateStr = eg: 2013-03-21 00:00:00  */
	public static GregorianCalendar formatSqlStringToGC(String datestr) {
		int year = Integer.parseInt(datestr.substring(0, 4));
		int month = Integer.parseInt(datestr.substring(5, 7));
		int date = Integer.parseInt(datestr.substring(8, 10));
		int hour = Integer.parseInt(datestr.substring(11, 13));
		int min = Integer.parseInt(datestr.substring(14, 16));
		int sec = Integer.parseInt(datestr.substring(17, 19));
		
		GregorianCalendar cal = new GregorianCalendar();
		
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, date);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.SECOND, sec);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}
	
	/** dateStr =  eg: 00:00:00,  HH:mm:ss*/
	public static GregorianCalendar formatHHMMSSStringToGC(String timestr) {
		int hour = Integer.parseInt(timestr.substring(0, 2));
		int min = Integer.parseInt(timestr.substring(3, 5));
		int sec = Integer.parseInt(timestr.substring(6, 8));
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.SECOND, sec);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}
	
	/** dateStr : yyyy:mm:dd */
	public static GregorianCalendar formatYYYYMMDDToGC(String datestr, String timeZone) {
		int year = Integer.parseInt(datestr.substring(0, 4));
		int month = Integer.parseInt(datestr.substring(5, 7));
		int date = Integer.parseInt(datestr.substring(8, 10));
		GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone(timeZone));
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}
	
	public static String addTimeSlotHHMMSS(String time, int minutes) {
		GregorianCalendar slot = formatHHMMSSStringToGC(time);
		slot.add(Calendar.MINUTE, minutes);
		return new String(formatGCDateToHH_MM_SS(slot));
	}

	public static int compareTime(String time1, String time2) {
		int retval = -2;
		if (time1 == null && time2 == null) {
			retval = 0;
		} else if (time1 != null && time2 != null) {
			// hh:mm:ss
			int hour1 = Integer.valueOf(time1.substring(0, 2)).intValue();
			int min1 = Integer.valueOf(time1.substring(3, 5)).intValue();
			int sec1 = Integer.valueOf(time1.substring(6, 8)).intValue();

			// hh:mm:ss
			int hour2 = Integer.valueOf(time2.substring(0, 2)).intValue();
			int min2 = Integer.valueOf(time2.substring(3, 5)).intValue();
			int sec2 = Integer.valueOf(time2.substring(6, 8)).intValue();

			int timeInSeconds1 = hour1 * 60 * 60 + min1 * 60 + sec1;
			int timeInSeconds2 = hour2 * 60 * 60 + min2 * 60 + sec2;

			if (timeInSeconds1 == timeInSeconds2)
				retval = 0;
			else if (timeInSeconds1 < timeInSeconds2)
				retval = -1;
			else
				retval = 1;
		}
		return retval;
	}

	public static boolean isTimeWithinBoundary(String apptTime, String st1, String et1) {
		boolean retval = true;
		if (apptTime != null && st1 != null && et1 != null) {
			if (compareTime(apptTime, st1) == -1)
				retval = false; // apptTime < st1
			else if (compareTime(apptTime, et1) != -1)
				retval = false; // apptTime >= et1
		}
		return retval;
	}

	public static String addTimeSlot(String dateTime, int minutes) {
		GregorianCalendar slot = DateUtils.formatSqlStringToGC(dateTime);
		slot.add(Calendar.MINUTE, minutes);
		return new String(formatGCDateToYYYYMMDD(slot) + " " + formatGCDateToHH_MM_SS(slot));
	}
	
	public static String formatGCDateToHH_MM_SS(GregorianCalendar dateGC) {
		String dateString = "";
		SimpleDateFormat formatter = new SimpleDateFormat(CommonDateContants.TIME_FORMAT_HHMMSS_CAP.getValue());
		formatter.setCalendar(dateGC);
		try {
			dateString = formatter.format(dateGC.getTime());
		} catch (Exception e) {
			// log.error("Exception :"+ e.toString());
			e.printStackTrace();
		}
		return dateString;
	}
	
	public static String formatGCDateToYYYYMMDD(Calendar dateCal) {
		String dateString = "";
		ThreadLocal<DateFormat> formatter = getSimpleDateFormat(CommonDateContants.DATE_FORMAT_YYYYMMDD.getValue());
		formatter.get().setCalendar(dateCal);
		try {
			dateString = formatter.get().format(dateCal.getTime());
		} catch (Exception e) {
		}

		return dateString;
	}

	/**
	 * This method checks to see if the supplied dateValue format matches the
	 * supplied dateFormat.
	 */
	public static boolean isValidDate(String dateValue, String dateFormatStr, String timeZone) {
		GregorianCalendar gcDate = new GregorianCalendar(TimeZone.getTimeZone(timeZone));
		ThreadLocal<DateFormat> dateFormat = getSimpleDateFormat(dateFormatStr);
		dateFormat.get().setCalendar(gcDate);
		try {
			dateFormat.get().setLenient(false);
			dateFormat.get().parse(dateValue);
		} catch (Exception e) {
			logger.error("Error :" + e, e);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// not using yet
 	public static Calendar addMinutesAndGetCalendar(int minutes, String dataFormatStr) {
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.MINUTE, minutes);
		return cal;
	}

	public static Calendar addDaysAndGetCalendar(GregorianCalendar cal, int days, String timeZone) {
		cal.setTimeZone(TimeZone.getTimeZone(timeZone));
		cal.add(Calendar.DATE, days);
		return cal;
	}

	public static Calendar addDaysAndGetCalendar(int days) {
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DATE, days);
		return cal;
	}
	
	public static Calendar addMonthsAndGetCalendar(int months) {
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.MONTH, months);
		return cal;
	}

	public static Calendar addHoursAndGetCalendar(Calendar calendar, int hours) {
		calendar.add(Calendar.HOUR, hours);
		return calendar;
	}
	
	public static Calendar addDaysAndGetCalendar(Calendar calendar, int days) {
		calendar.add(Calendar.DATE, days);
		return calendar;
	}
	
	public static Calendar addMonthsAndGetCalendar(Calendar calendar, int months) {
		calendar.add(Calendar.MONTH, months);
		return calendar;
	}

	public static GregorianCalendar addHoursAndGetCalendar(GregorianCalendar gcalendar, int hours, String timeZone) {
		gcalendar.setTimeZone(TimeZone.getTimeZone(timeZone));
		gcalendar.add(Calendar.HOUR, hours);
		return gcalendar;
	}
	
	public static String getTimeStr(Time time,String timeFormat) {
		ThreadLocal<DateFormat> dateFormat = getSimpleDateFormat(timeFormat);
		return dateFormat.get().format(time);
	}

	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The dates must not be null");
		}
		return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
	}
	
	// not using yet
	public static boolean isToday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return isSameDay(calendar, Calendar.getInstance());
	}
	
	public static boolean isToday(Calendar cal) {
		return isSameDay(cal, Calendar.getInstance());
	}

	public static Calendar addMinutesAndGetCalendar(Calendar calendar, int minutes) {
		calendar.add(Calendar.MINUTE, minutes);
		return calendar;
	}

	public static Calendar addMinutesAndGetCalendar(Calendar calendar, int minutes, String timeZone) {
		calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
		calendar.add(Calendar.MINUTE, minutes);
		return calendar;
	}
	
	// not using yet
	public static Calendar addMinutesAndGetCalendar(int minutes, String dateFormatStr, String timeZone) {
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone(timeZone));
		cal.add(Calendar.MINUTE, minutes);
		return cal;
	}
	
	
	public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }
	
	public static Timestamp getTimestampFromString(String dateTimeString){		
		Date date = null;
		java.sql.Timestamp timeStampDate = null;
		if(dateTimeString!=null){
			try{	
				ThreadLocal<DateFormat> dateFormat =getSimpleDateFormat(CommonDateContants.DATETIME_FORMAT_YYYYMMDDHHMMSS_CAP.getValue());
				date =  dateFormat.get().parse(dateTimeString.trim());
				timeStampDate =  new Timestamp(date.getTime());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return timeStampDate;
	}
	
	public static Timestamp getTimestampFromString(String dateTimeString,String format){		
		Date date = null;
		java.sql.Timestamp timeStampDate = null;
		if(dateTimeString!=null){
			try{	
				ThreadLocal<DateFormat> dateFormat =getSimpleDateFormat(format);
				date =  dateFormat.get().parse(dateTimeString.trim());
				timeStampDate =  new Timestamp(date.getTime());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return timeStampDate;
	}
	
	public static Date getDateObject(String dateTimeString){		
		Date date = null;
		if(dateTimeString!=null){
			try{	
				ThreadLocal<DateFormat> dateFormat =getSimpleDateFormat(CommonDateContants.DATE_FORMAT_YYYYMMDD.getValue());
				date =  dateFormat.get().parse(dateTimeString.trim());
				return date;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return date;
	}
	
	public static List<String> getCurrentWeekDateList(Calendar calendar, String timeZone) {
		List<String> weeklyKeys = new ArrayList<String>();
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		
		Calendar currentDate = calendar;
		Calendar startDate = calendar;

		int firstDayOfWeek = currentDate.getFirstDayOfWeek();
		int days = (startDate.get(Calendar.DAY_OF_WEEK) + 7 - firstDayOfWeek) % 7;
		startDate.add(Calendar.DATE, -days);

		Calendar endDate = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
		endDate.setTime(startDate.getTime());
		endDate.add(Calendar.DATE, 6);
		
		ThreadLocal<DateFormat> dtf = DateUtils.getSimpleDateFormat(CommonDateContants.DATE_FORMAT_YYYYMMDD.getValue());
		ThreadLocal<DateFormat> dayOfTheWeek = DateUtils.getSimpleDateFormat(CommonDateContants.DAY_OF_THE_WEEK_FORMAT.getValue());
		weeklyKeys.add(dtf.get().format(startDate.getTime())+"|"+dayOfTheWeek.get().format(startDate.getTime()));
		while(startDate.before(endDate)) {
			startDate.add(Calendar.DATE, 1);
			String key = dtf.get().format(startDate.getTime())+"|"+dayOfTheWeek.get().format(startDate.getTime());
			weeklyKeys.add(key);
		}

		for(String key: weeklyKeys) {
			System.out.println(key);
		}
		return weeklyKeys;
	}
}
