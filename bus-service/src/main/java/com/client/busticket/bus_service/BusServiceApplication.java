package com.client.busticket.bus_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class BusServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusServiceApplication.class, args);

		System.out.println("Bus Service is running...");
	}

}
