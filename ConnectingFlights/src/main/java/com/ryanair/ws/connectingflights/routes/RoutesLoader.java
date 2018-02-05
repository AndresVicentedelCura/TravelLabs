package com.ryanair.ws.connectingflights.routes;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryanair.ws.connectingflights.microservices.RyanairServices;
import com.ryanair.ws.connectingflights.microservices.pojo.Route;

/**
 * The routes loader
 * 
 * @author Andres.Vicente
 *
 */
@Component
public class RoutesLoader {
	
	/**
	 * The logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(RoutesInfo.class);
	
	/**
	 * The services
	 */
	@Autowired
	private RyanairServices ryanairServices;
	
	/**
	 * The routes info object
	 */
	@Autowired
	private RoutesInfo routesInfo;

	/**
	 * Loads all the routes at the begining and stores it
	 */
	@PostConstruct
	public void loadRoutes() {
		LOGGER.info("Starting app, loading routes...");
		
		List<Route> routes = ryanairServices.getAvailableRoutes();
		
		for (Route route : routes) {
			
			if (route.getConnectingAirport() == null) {
				routesInfo.addRoute(route);	
			}
			
		}
		
		LOGGER.info("Routes loaded: " + routesInfo.getRoutes());
		
	}
}
