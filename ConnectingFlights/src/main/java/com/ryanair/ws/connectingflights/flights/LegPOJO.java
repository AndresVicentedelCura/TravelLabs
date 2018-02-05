package com.ryanair.ws.connectingflights.flights;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * This class represents a leg
 * 
 * @author Andres.Vicente
 *
 */
@XmlRootElement(name="leg")
@XmlAccessorType(XmlAccessType.FIELD)
public class LegPOJO {
	
	/**
	 * The departure airport
	 */
	@XmlElement(name = "departureAirport")
	private String departureAirport;
	
	/**
	 * The arrival airport
	 */
	@XmlElement(name = "arrivalAirport")
	private String arrivalAirport;
	
	/**
	 * The departure datetime
	 */
	@XmlElement(name = "departureDateTime")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	private Date departureDateTime;
	
	/**
	 * The arrival datetime
	 */
	@XmlElement(name = "arrivalDateTime")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	private Date arrivalDateTime;

	/**
	 * @return the departureAirport
	 */
	public String getDepartureAirport() {
		return departureAirport;
	}

	/**
	 * @param departureAirport the departureAirport to set
	 */
	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	/**
	 * @return the arrivalAirport
	 */
	public String getArrivalAirport() {
		return arrivalAirport;
	}

	/**
	 * @param arrivalAirport the arrivalAirport to set
	 */
	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	/**
	 * @return the departureDateTime
	 */
	public Date getDepartureDateTime() {
		return departureDateTime;
	}

	/**
	 * @param departureDateTime the departureDateTime to set
	 */
	public void setDepartureDateTime(Date departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	/**
	 * @return the arrivalDateTime
	 */
	public Date getArrivalDateTime() {
		return arrivalDateTime;
	}

	/**
	 * @param arrivalDateTime the arrivalDateTime to set
	 */
	public void setArrivalDateTime(Date arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LegPOJO [departureAirport=" + departureAirport + ", arrivalAirport=" + arrivalAirport
				+ ", departureDateTime=" + departureDateTime + ", arrivalDateTime=" + arrivalDateTime + "]";
	}

}
