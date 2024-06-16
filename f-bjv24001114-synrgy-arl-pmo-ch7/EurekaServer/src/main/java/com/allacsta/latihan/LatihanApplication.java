package com.allacsta.latihan;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class LatihanApplication {

	private static final Logger logger = LoggerFactory.getLogger(LatihanApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LatihanApplication.class, args);

		logger.info("info logging level");
		logger.error("eror logging level");
		logger.warn("warning logging level");
////		logger.debug("debug logging level");
////		logger.trace("trace logging level");
	}

}
