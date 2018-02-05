package com.ryanair.ws.connectingflights.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * The Utils class
 * 
 * @author Andres.Vicente
 *
 */
public class Utils {
	
	/**
	 * Gets the day of month number of a date
	 * 
	 * @param date
	 * 			The date
	 * @return
	 */
	public static int getDay(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * Gets the month number of a date
	 * 
	 * @param date
	 * 			The date
	 * @return
	 */
	public static int getMonth(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		return calendar.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * Gets the year of a date
	 * 
	 * @param date
	 * 			The date
	 * @return
	 */
	public static int getYear(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		return calendar.get(Calendar.YEAR);		
	}
	
	/**
	 * Gets the first day of the next month of a given date
	 * @param date
	 * 			The date
	 * @return
	 */
	public static Date getNextMonth(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
		
	}

	/**
	 * Creates a new date given the year, month, day and time
	 * 
	 * @param year
	 * 			The year
	 * @param month
	 * 			The month
	 * @param day
	 * 			The day of the month
	 * @param time
	 * 			The time in format HH:mm
	 * @return
	 */
	public static Date createDate(int year, int month, int day, String time) {
		
		Calendar calendar = Calendar.getInstance();
		
		
		String[] splittedTime = time.split(":");
		int hour = Integer.parseInt(splittedTime[0]);
		int minute = Integer.parseInt(splittedTime[1]);
		
		calendar.set(year, month - 1, day, hour, minute, 0);
		
		return calendar.getTime();
		
	}
	
	/**
	 * Adds two hours to a date
	 * 
	 * @param date
	 * 			The date
	 * @return
	 */
	public static Date addTwoHours(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(date);
		
		calendar.add(Calendar.HOUR, 2);
		
		return calendar.getTime();
	}

}
