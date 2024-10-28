package com.adcc.flightsobtcheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@ComponentScan(basePackages = {"com.adcc.flightsobtcheck.UpdateSDeptimeTask"})
public class FlightsobtcheckApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightsobtcheckApplication.class, args);
		System.out.println("Hello, World!");
	}

}
