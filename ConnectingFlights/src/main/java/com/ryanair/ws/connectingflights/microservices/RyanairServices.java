package com.ryanair.ws.connectingflights.microservices;

import java.util.List;

import com.ryanair.ws.connectingflights.microservices.pojo.MonthFlights;
import com.ryanair.ws.connectingflights.microservices.pojo.Route;

/**
 * The interface for the Ryanair services
 * 
 * @author Andres.Vicente
 *
 */
public interface RyanairServices {
	
	/**
	 * Gets the available routes
	 * 
	 * @return
	 */
	public List<Route> getAvailableRoutes();
	
	/**
	 * Gets the monthly flights
	 * 
	 * @param departureAirport
	 * @param arrivalAirport
	 * @param year
	 * @param month
	 * @return
	 */
	public MonthFlights getFlights(String departureAirport, String arrivalAirport, int year, int month);

}
