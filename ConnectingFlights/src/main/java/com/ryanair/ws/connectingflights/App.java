package com.ryanair.ws.connectingflights;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class is the Application class of Spring boot
 * 
 * @author Andres.Vicente
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
public class App 
{
	/**
	 * The main method
	 * 
	 * @param args
	 * @throws Exception
	 */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}
