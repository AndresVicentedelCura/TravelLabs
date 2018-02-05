package com.ryanair.ws.connectingflights.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryanair.ws.connectingflights.flights.FlightPOJO;
import com.ryanair.ws.connectingflights.flights.LegPOJO;
import com.ryanair.ws.connectingflights.microservices.RyanairServices;
import com.ryanair.ws.connectingflights.microservices.pojo.DayFlights;
import com.ryanair.ws.connectingflights.microservices.pojo.FlightInfo;
import com.ryanair.ws.connectingflights.microservices.pojo.MonthFlights;
import com.ryanair.ws.connectingflights.routes.RoutesInfo;
import com.ryanair.ws.connectingflights.utils.Utils;

/**
 * This class implements the service
 * 
 * @author Andres.Vicente
 *
 */
@Service
public class ConnectingFlightsServiceImpl implements ConnectingFlightsService{
	
	/**
	 * The constant wiht the max connections for the flights
	 */
	private static final int MAX_CONNECTIONS = 1;
	
	/**
	 * The routes info
	 */
	@Autowired
	private RoutesInfo routesInfo;
	
	/**
	 * The Service to get the flights
	 */
	@Autowired
	private RyanairServices ryanairServices;

	@Override
	public List<FlightPOJO> getConnectingFlights(String departureAirport, String arrivalAirport, Date departureDateTime,
			Date arrivalDateTime) {
		
		List<List<String>> routes = routesInfo.getDestination(departureAirport, arrivalAirport, 0, MAX_CONNECTIONS);
		
		List<FlightPOJO> flights = new ArrayList<FlightPOJO>();
		
		for (List<String> route : routes) {
			flights.addAll(this.recursiveSearch(route, departureDateTime, arrivalDateTime, 0, 1));
		}
		
		
		return flights;
	}
	
	/**
	 * The recursive search method to get the flights for a route
	 * @param route
	 * 			The route
	 * @param departureDateTime
	 * 			The departure date time
	 * @param arrivalDateTime
	 * 			The arrival date time
	 * @param departureIndex
	 * 			The index of the departure airport
	 * @param arrivalIndex
	 * 			The index of the arrival airport
	 * @return
	 * 			The list of available flights
	 */
	private List<FlightPOJO> recursiveSearch(List<String> route, Date departureDateTime, Date arrivalDateTime, int departureIndex, int arrivalIndex) {
		
		List<FlightPOJO> listToReturn = new ArrayList<FlightPOJO>();
		FlightPOJO flight = null;
		List<LegPOJO> auxList = null;
		
		if (arrivalIndex == route.size() - 1) {
			
			String departureAirport = route.get(departureIndex);
			String arrivalAirport = route.get(arrivalIndex);
			
			List<LegPOJO> legs = this.getFlightsAfterDeparture(departureAirport, arrivalAirport, departureDateTime, arrivalDateTime);
			
			for (LegPOJO legPOJO : legs) {
				
				if (legPOJO.getArrivalDateTime().before(arrivalDateTime)) {
					flight = new FlightPOJO();
					flight.setStops(0);
					auxList = new ArrayList<LegPOJO>();
					auxList.add(legPOJO);
					flight.setLegs(auxList);
					
					listToReturn.add(flight);
				}
			}
			
		}
		
		if (arrivalIndex < route.size() - 1) {
			
			String departureAirport = route.get(departureIndex);
			String arrivalAirport = route.get(arrivalIndex);
			
			List<LegPOJO> legs = this.getFlightsAfterDeparture(departureAirport, arrivalAirport, departureDateTime, arrivalDateTime);
			
			Date earlierDate = arrivalDateTime;
			for (LegPOJO legPOJO : legs) {
				
				List<FlightPOJO> flights = new ArrayList<FlightPOJO>();
				if (legPOJO.getArrivalDateTime().before(earlierDate)) {
					Date newDepartureDate = Utils.addTwoHours(legPOJO.getArrivalDateTime());
					flights = this.recursiveSearch(route, newDepartureDate, arrivalDateTime, departureIndex + 1, arrivalIndex + 1);
					
					for (FlightPOJO flightPOJO : flights) {
						
						flightPOJO.setStops(flightPOJO.getStops() + 1);
						flightPOJO.getLegs().add(0, legPOJO);
						
						listToReturn.add(flightPOJO);
						
					}
					
					earlierDate = legPOJO.getArrivalDateTime();
				}
				
			}
			
			
			
		}
		
		
		return listToReturn;
		
	}
	
	/**
	 * Returns a list of Legs
	 * after the departure date and before the arrival
	 * 
	 * @param departureAirport
	 * 			The departure airport
	 * @param arrivalAirport
	 * 			The arrival airport
	 * @param departureDate
	 * 			The departure date time
	 * @param arrivalDate
	 * 			The arrival date time
	 * @return
	 */
	private List<LegPOJO> getFlightsAfterDeparture(String departureAirport, String arrivalAirport,Date departureDate, Date arrivalDate) {
		
		LegPOJO leg = null;
		List<LegPOJO> listToReturn = new ArrayList<LegPOJO>();
		
		Date currentDate = departureDate;
		
		MonthFlights availableFlights  = null;
		int month = 0;
		int day = 0;
		Date auxDepartureDate = null;
		Date auxArrivalDate = null;
		while (currentDate.before(arrivalDate)) {
			
			availableFlights = ryanairServices.getFlights(departureAirport, arrivalAirport, Utils.getYear(currentDate), Utils.getMonth(currentDate));
			if (availableFlights != null) {
				month = availableFlights.getMonth();
				
				for (DayFlights dayFlights : availableFlights.getDays()) {
					
					day = dayFlights.getDay();
					
					for (FlightInfo flight : dayFlights.getFlights()) {
						
						auxDepartureDate = Utils.createDate(Utils.getYear(currentDate), month, day, flight.getDepartureTime());
						auxArrivalDate = Utils.createDate(Utils.getYear(currentDate), month, day, flight.getArrivalTime());
						
						if (auxDepartureDate.after(departureDate) && auxArrivalDate.before(arrivalDate)) {
							// Create LEG
							leg = new LegPOJO();
							leg.setArrivalAirport(arrivalAirport);
							leg.setArrivalDateTime(auxArrivalDate);
							leg.setDepartureAirport(departureAirport);
							leg.setDepartureDateTime(auxDepartureDate);
							
							listToReturn.add(leg);
						}
						
						currentDate = auxDepartureDate;
								
					}
				}
				
			}
			currentDate = Utils.getNextMonth(currentDate);
			
		}
		
		
		return listToReturn;
		
	}
	
	

}
