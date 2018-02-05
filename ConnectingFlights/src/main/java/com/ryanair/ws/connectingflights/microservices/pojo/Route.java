package com.ryanair.ws.connectingflights.microservices.pojo;

/**
 * This class defines a route given from the microservice
 * 
 * @author Andres.Vicente
 *
 */
public class Route {
	
	/**
	 * The departure airport IATA Code
	 */
	private String airportFrom;
	
	/**
	 * The arrival airport IATA Code
	 */
	private String airportTo;
	
	/**
	 * A connecting airport IATA Code
	 */
	private String connectingAirport;
	
	/**
	 * Boolean to mark new routes
	 */
	private boolean newRoute;
	
	/**
	 * Boolean to mark seasonal routes
	 */
	private boolean seasonalRoute;
	
	private String group;

	/**
	 * @return the airportFrom
	 */
	public String getAirportFrom() {
		return airportFrom;
	}

	/**
	 * @param airportFrom the airportFrom to set
	 */
	public void setAirportFrom(String airportFrom) {
		this.airportFrom = airportFrom;
	}

	/**
	 * @return the airportTo
	 */
	public String getAirportTo() {
		return airportTo;
	}

	/**
	 * @param airportTo the airportTo to set
	 */
	public void setAirportTo(String airportTo) {
		this.airportTo = airportTo;
	}

	/**
	 * @return the connectionAirport
	 */
	public String getConnectingAirport() {
		return connectingAirport;
	}

	/**
	 * @param connectionAirport the connectionAirport to set
	 */
	public void setConnectingAirport(String connectingAirport) {
		this.connectingAirport = connectingAirport;
	}

	/**
	 * @return the newRoute
	 */
	public boolean isNewRoute() {
		return newRoute;
	}

	/**
	 * @param newRoute the newRoute to set
	 */
	public void setNewRoute(boolean newRoute) {
		this.newRoute = newRoute;
	}

	/**
	 * @return the seasonalRoute
	 */
	public boolean isSeasonalRoute() {
		return seasonalRoute;
	}

	/**
	 * @param seasonalRoute the seasonalRoute to set
	 */
	public void setSeasonalRoute(boolean seasonalRoute) {
		this.seasonalRoute = seasonalRoute;
	}

	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Route [airportFrom=" + airportFrom + ", airportTo=" + airportTo + ", connectingAirport="
				+ connectingAirport + ", newRoute=" + newRoute + ", seasonalRoute=" + seasonalRoute + ", group=" + group
				+ "]";
	}

}
