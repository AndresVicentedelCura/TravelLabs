package com.ryanair.ws.connectingflights.service;

import java.util.Date;
import java.util.List;

import com.ryanair.ws.connectingflights.flights.FlightPOJO;

/**
 * This interface defines the operations to be implemented
 * to serve the controller
 * 
 * @author Andres.Vicente
 *
 */
public interface ConnectingFlightsService {
	
	/**
	 * Gets the list of connection flights that fit with the request
	 * 
	 * @param departureAirport
	 * @param arrivalAirport
	 * @param departureDateTime
	 * @param arrivalDateTime
	 * @return
	 */
	public List<FlightPOJO> getConnectingFlights(String departureAirport, String arrivalAirport, Date departureDateTime, Date arrivalDateTime);

}
