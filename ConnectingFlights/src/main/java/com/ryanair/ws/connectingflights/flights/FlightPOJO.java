package com.ryanair.ws.connectingflights.flights;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represents a flight
 * 
 * @author Andres.Vicente
 *
 */
@XmlRootElement(name="flight")
@XmlAccessorType(XmlAccessType.FIELD)
public class FlightPOJO {
	
	/**
	 * The stops number
	 */
	@XmlElement(name = "stops")
	private int stops;
	
	/**
	 * The legs list
	 */
	@XmlElement(name = "legs")
	private List<LegPOJO> legs;

	/**
	 * @return the stops
	 */
	public int getStops() {
		return stops;
	}

	/**
	 * @param stops the stops to set
	 */
	public void setStops(int stops) {
		this.stops = stops;
	}

	/**
	 * @return the legs
	 */
	public List<LegPOJO> getLegs() {
		return legs;
	}

	/**
	 * @param legs the legs to set
	 */
	public void setLegs(List<LegPOJO> legs) {
		this.legs = legs;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FlightPOJO [stops=" + stops + ", legs=" + legs + "]";
	}

}
