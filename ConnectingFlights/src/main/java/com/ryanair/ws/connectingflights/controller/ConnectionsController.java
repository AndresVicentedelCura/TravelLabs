package com.ryanair.ws.connectingflights.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ryanair.ws.connectingflights.flights.FlightPOJO;
import com.ryanair.ws.connectingflights.service.ConnectingFlightsService;

/**
 * This class defines the controller to serve the requests
 * @author Andres.Vicente
 *
 */
@RestController
@RequestMapping("/flights")
public class ConnectionsController {
	
	/**
	 * The service
	 */
	@Autowired
	private ConnectingFlightsService connectingFlightsService;

	/**
	 * The method to get the interconnection flights
	 * @param departure 
	 * 			The departure IATA code
	 * @param arrival 
	 * 			The arrival IATA code
	 * @param departureDateTime
	 * 			The departure Date time 
	 * @param arrivalDateTime
	 * 			The arrival Date time
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/interconnections")
	public ResponseEntity<List<FlightPOJO>> getConnectionFlights(@RequestParam("departure") String departure
													, @RequestParam("arrival") String arrival
													, @RequestParam("departureDateTime") @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm") Date departureDateTime
													, @RequestParam("arrivalDateTime") @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm") Date arrivalDateTime) {
		
		List<FlightPOJO> response = connectingFlightsService.getConnectingFlights(departure, arrival, departureDateTime, arrivalDateTime);    	
	
		//return this.getResponseEntity(response);
		
		return new ResponseEntity<List<FlightPOJO>>(response, HttpStatus.OK);
	}

}
