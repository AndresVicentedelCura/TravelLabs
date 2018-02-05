package com.ryanair.ws.connectingflights.microservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryanair.ws.connectingflights.microservices.pojo.MonthFlights;
import com.ryanair.ws.connectingflights.microservices.pojo.Route;

/**
 * The Services implementation
 * 
 * @author Andres.Vicente
 *
 */
@Service
public class RyanairServicesImpl implements RyanairServices {
	
	/**
	 * The microservices consumer
	 */
	@Autowired
	private MicroServicesConsumer microServicesConsumer;

	@Override
	public List<Route> getAvailableRoutes() {
		
		return microServicesConsumer.getAvailableRoutes();
	}

	@Override
	public MonthFlights getFlights(String departureAirport, String arrivalAirport, int year, int month) {

		return microServicesConsumer.getFlights(departureAirport, arrivalAirport, year, month);
	}

}
