package com.ryanair.ws.connectingflights.microservices.pojo;

/**
 * Flight info received from Ryanair service
 * 
 * @author Andres.Vicente
 *
 */
public class FlightInfo {
	
	/**
	 * The flight number
	 */
	private String number;
	
	/**
	 * The departure time
	 */
	private String departureTime;
	
	/**
	 * The arrival time
	 */
	private String arrivalTime;

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the departureTime
	 */
	public String getDepartureTime() {
		return departureTime;
	}

	/**
	 * @param departureTime the departureTime to set
	 */
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * @return the arrivalTime
	 */
	public String getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * @param arrivalTime the arrivalTime to set
	 */
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FlightInfo [number=" + number + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime
				+ "]";
	}

}
