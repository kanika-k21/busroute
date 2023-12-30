package com.javaws.busroute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BusrouteApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BusrouteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Inside run method");
	}
}
