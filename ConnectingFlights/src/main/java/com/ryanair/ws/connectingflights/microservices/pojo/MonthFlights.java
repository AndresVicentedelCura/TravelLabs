package com.ryanair.ws.connectingflights.microservices.pojo;

import java.util.List;

/**
 * The fight info for a month
 * 
 * @author Andres.Vicente
 *
 */
public class MonthFlights {
	
	/**
	 * The month
	 */
	private int month;
	
	/**
	 * The days
	 */
	private List<DayFlights> days;

	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * @return the days
	 */
	public List<DayFlights> getDays() {
		return days;
	}

	/**
	 * @param days the days to set
	 */
	public void setDays(List<DayFlights> days) {
		this.days = days;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MonthFlights [month=" + month + ", days=" + days + "]";
	}

}
