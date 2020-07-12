package com.rest.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class RestDemoApplication {

	
	//  Demo app showing feature of add, update number of quantity and removed products from the cart through api. 
	
	public static void main(String[] args) {
		SpringApplication.run(RestDemoApplication.class, args);
	}

}
