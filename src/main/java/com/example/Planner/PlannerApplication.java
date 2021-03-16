package com.example.Planner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@ComponentScan("com.example") //to scan packages mentioned
public class PlannerApplication {

	public static void main(String[] args) {

		SpringApplication.run(PlannerApplication.class, args);
	}

}
