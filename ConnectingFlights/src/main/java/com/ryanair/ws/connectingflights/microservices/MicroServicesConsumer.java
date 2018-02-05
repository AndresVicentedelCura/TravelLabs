package com.ryanair.ws.connectingflights.microservices;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryanair.ws.connectingflights.microservices.pojo.MonthFlights;
import com.ryanair.ws.connectingflights.microservices.pojo.Route;

/**
 * This class connects to the Ryanair Microservices to get the info
 * 
 * @author Andres.Vicente
 *
 */
@Component
public class MicroServicesConsumer {
	
	/**
	 * The available routes URL
	 */
	private final String AVAILABLE_ROUTES_URL = "https://api.ryanair.com/core/3/routes";
	
	private final String GET_FLIGHTS_URL = "https://api.ryanair.com/timetable/3/schedules/{0}/{1}/years/{2}/months/{3}";
	
	/**
	 * The logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(MicroServicesConsumer.class);
	
	/**
	 * Gets the available routes from the Ryanair service
	 * 
	 * @return
	 */
	public List<Route> getAvailableRoutes() {
		
		LOGGER.info("Entering in the routes request");
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		
		/*
		 * Creates the http request with a POST method
		 */
		HttpGet httpRequest = new HttpGet(AVAILABLE_ROUTES_URL);
		
		/*
		 * Executes the call 
		 */
		HttpResponse response = null;
		List<Route> responseObj = null;
		try {
			response = httpClient.execute(httpRequest);
		} catch (ClientProtocolException e) {
			LOGGER.error("ERROR ClientProtocolException in http execute.", e);
		} catch (IOException e){
			LOGGER.error("ERROR IOException in http execute.", e);
		}
		
		if(response!= null && response.getStatusLine().getStatusCode() == 200){
			
			try {
				responseObj = this.convertToObject(EntityUtils.toString(response.getEntity()));
				LOGGER.info("RESPONSE OK");
			} catch (IOException e) {
				LOGGER.error("ERROR, Couldn't Stringify", e);
				return null;
			}
			
		} else {
			LOGGER.error("Error getting routes, Response: [" + response.toString() + "]");
		}
		
		try {
			EntityUtils.consume(response.getEntity());
		} catch (IOException e) {
			LOGGER.error("ERROR IOException in consume response.", e);
		} finally {
			httpRequest.releaseConnection();
			HttpClientUtils.closeQuietly(response);
		}
		
		return responseObj;
	}
	
	/**
	 * Gets the flights from the Ryanair services
	 * 
	 * @param departureAirport
	 * @param arrivalAirport
	 * @param year
	 * @param month
	 * @return
	 */
	public MonthFlights getFlights(String departureAirport, String arrivalAirport, int year, int month) {
		
		LOGGER.info("Entering in the flights request");
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		
		/*
		 * Creates the http request with a POST method
		 */
		MessageFormat urlFormatter = new MessageFormat(GET_FLIGHTS_URL);
		String finalURL = urlFormatter.format(new Object[] {departureAirport, arrivalAirport, String.valueOf(year), String.valueOf(month)});
		LOGGER.info(finalURL);
		
		HttpGet httpRequest = new HttpGet(finalURL);
		
		/*
		 * Executes the call 
		 */
		HttpResponse response = null;
		MonthFlights responseObj = null;
		try {
			response = httpClient.execute(httpRequest);
		} catch (ClientProtocolException e) {
			LOGGER.error("ERROR ClientProtocolException in http execute.", e);
		} catch (IOException e){
			LOGGER.error("ERROR IOException in http execute.", e);
		}
		
		if(response!= null && response.getStatusLine().getStatusCode() == 200){
			
			try {
				responseObj = this.convertToMonthFlights(EntityUtils.toString(response.getEntity()));
				LOGGER.info("RESPONSE OK");
			} catch (IOException e) {
				LOGGER.error("ERROR, Couldn't Stringify", e);
				return null;
			}
			
		} else {
			LOGGER.error("Error getting routes, Response: [" + response.toString() + "]");
		}
		
		try {
			EntityUtils.consume(response.getEntity());
		} catch (IOException e) {
			LOGGER.error("ERROR IOException in consume response.", e);
		} finally {
			httpRequest.releaseConnection();
			HttpClientUtils.closeQuietly(response);
		}
		
		return responseObj;
	}
	
	/**
	 * Converts a JSON to a list of routes
	 * 
	 * @param json
	 * @return
	 */
	private List<Route> convertToObject(String json){
		
		ObjectMapper mapper = new ObjectMapper();
		List<Route> object = null;
		try {
			object = mapper.readValue(json, new TypeReference<List<Route>>(){});
		} catch (JsonProcessingException e1) {
			LOGGER.error("ERROR, Couldn't transforme auth to JSON", e1);
			return null;
		} catch (IOException e) {
			LOGGER.error("ERROR, Couldn't transforme auth to JSON", e);
			return null;
		} 
		
		return object;
		
	}
	
	/**
	 * Converts a JSON to a MonthFlights object
	 * 
	 * @param json
	 * @return
	 */
	private MonthFlights convertToMonthFlights(String json){
		
		ObjectMapper mapper = new ObjectMapper();
		MonthFlights object = null;
		try {
			object = mapper.readValue(json, MonthFlights.class);
		} catch (JsonProcessingException e1) {
			LOGGER.error("ERROR, Couldn't transforme auth to JSON", e1);
			return null;
		} catch (IOException e) {
			LOGGER.error("ERROR, Couldn't transforme auth to JSON", e);
			return null;
		} 
		
		return object;
		
	}

}
