package com.allacsta.latihan;

import com.allacsta.latihan.controller.fileupload.FileStorageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EntityScan(basePackages = {"com.allacsta.latihan"})
//@ComponentScan(basePackages = {"com.allacsta.latihan"})
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class LatihanApplication {

	private static final Logger logger = LoggerFactory.getLogger(LatihanApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LatihanApplication.class, args);

		logger.info("info logging level");
		logger.error("eror logging level");
		logger.warn("warning logging level");
//		logger.debug("debug logging level");
//		logger.trace("trace logging level");
	}

}
