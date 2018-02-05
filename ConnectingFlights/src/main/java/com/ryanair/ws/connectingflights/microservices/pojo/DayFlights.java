package com.ryanair.ws.connectingflights.microservices.pojo;

import java.util.List;

/**
 * Info for the flights in a day
 * 
 * @author Andres.Vicente
 *
 */
public class DayFlights {
	
	/**
	 * The day number
	 */
	private int day;
	
	/**
	 * The list of flights
	 */
	private List<FlightInfo> flights;

	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * @return the flights
	 */
	public List<FlightInfo> getFlights() {
		return flights;
	}

	/**
	 * @param flights the flights to set
	 */
	public void setFlights(List<FlightInfo> flights) {
		this.flights = flights;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DayFlights [day=" + day + ", flights=" + flights + "]";
	}

}
