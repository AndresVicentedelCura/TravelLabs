package com.ryanair.ws.connectingflights.routes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.ryanair.ws.connectingflights.microservices.pojo.Route;

/**
 * This class is the routes repository
 * 
 * @author Andres.Vicente
 *
 */
@Repository
public class RoutesInfo {

	/**
	 * The structure to save the routes
	 */
	private Map<String, Set<String>> routes;
	
	public RoutesInfo() {
		
		routes = new HashMap<String, Set<String>>();
	}

	/**
	 * @return the routes
	 */
	public Map<String, Set<String>> getRoutes() {
		return routes;
	}

	/**
	 * @param routes the routes to set
	 */
	public void setRoutes(Map<String, Set<String>> routes) {
		this.routes = routes;
	}
	
	/**
	 * This method adds a new route to the repository
	 * @param newRoute
	 */
	public void addRoute(Route newRoute) {
		
		Set<String> destinations = null;
		
		destinations = routes.get(newRoute.getAirportFrom());
		
		if (destinations == null) {
			destinations = new HashSet<String>();
		}
		
		destinations.add(newRoute.getAirportTo());
		routes.put(newRoute.getAirportFrom(), destinations);
		
	}
	
	/**
	 * This method checks if a route between 2 airports exists
	 * 
	 * @param airportFrom
	 * 			The IATA code of the departure airport
	 * @param airportTo
	 * 			The IATA code of the arrival airport
	 * @return True if the route exists
	 */
	public boolean existsRoute(String airportFrom, String airportTo) {
		
		boolean exists = false;
		
		Set<String> destinations = routes.get(airportFrom);
		
		if (destinations != null) {
			exists = destinations.contains(airportTo);
		}
		
		return exists;
		
	}
	
	/**
	 * Returns a set of airports with
	 * direct connection from departure Airport
	 * 
	 * @param departureAirport
	 * @return
	 */
	public Set<String> getDestinations(String departureAirport) {
		
		return routes.get(departureAirport);
		
	}
	
	/**
	 * 
	 * @param departureAirport
	 * @param arrivalAirport
	 * @param maxConnections
	 * @return
	 */
	public List<List<String>> getDestination(String departureAirport, String arrivalAirport, int currentConnections, int maxConnections) {
		
		List<List<String>> destinations = new ArrayList<List<String>>();
		
		Set<String> directDestinations = this.routes.get(departureAirport);
		
		List<String> route = null;
		for (String destination : directDestinations) {
			
			
			if (destination.equals(arrivalAirport)) {
				
				route = new ArrayList<String>();
				route.add(departureAirport);
				
				route.add(destination);
				destinations.add(route);
			} else {
				
				if (currentConnections < maxConnections) {
					
					List<List<String>> tmpRoutes = this.getDestination(destination, arrivalAirport, currentConnections + 1, maxConnections);
					
					if (!tmpRoutes.isEmpty()) {
						
						for (List<String> tmpRoute : tmpRoutes) {
							
							route = new ArrayList<String>();
							route.add(departureAirport);
							
							route.addAll(tmpRoute);
							destinations.add(route);
							
						}
						
					}
				}
				
			}
			
		}
		
		return destinations;
		
		
	}
	
}
