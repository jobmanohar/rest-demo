package com.rest.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import com.rest.demo.controller.ProductController;

@SpringBootTest
class RestDemoApplicationTests {
	
	@Autowired
	private ProductController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}
	

}
