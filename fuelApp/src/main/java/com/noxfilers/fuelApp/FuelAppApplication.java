package com.noxfilers.fuelApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class FuelAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(FuelAppApplication.class, args);
	}
}
