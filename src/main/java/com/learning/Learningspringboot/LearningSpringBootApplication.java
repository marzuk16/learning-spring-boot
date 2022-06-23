package com.learning.Learningspringboot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearningSpringBootApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(LearningSpringBootApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringBootApplication.class, args);

		LOGGER.info("###################################");
		LOGGER.info("Learning-spring-boot started successfully!!!");
		LOGGER.info("###################################");
	}

}
