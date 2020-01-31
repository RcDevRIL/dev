package com.occ.demoSpringBoot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class DemoSpringBootApplication {
    private static final Logger logger
            = LoggerFactory.getLogger(DemoSpringBootApplication.class);

	public static void main(String[] args) {
		logger.debug("COUCOU JE SUIS UN LOG");
		SpringApplication.run(DemoSpringBootApplication.class, args);
	}

}
