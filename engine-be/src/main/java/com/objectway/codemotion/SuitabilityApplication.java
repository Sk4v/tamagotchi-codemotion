package com.objectway.codemotion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.camunda.zeebe.spring.client.annotation.Deployment;

@SpringBootApplication
@ComponentScan(basePackages = "com.objectway.codemotion")
@Deployment(resources = "classpath:rules/suitability-rules.dmn")
public class SuitabilityApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SuitabilityApplication.class, args);
	}

}
