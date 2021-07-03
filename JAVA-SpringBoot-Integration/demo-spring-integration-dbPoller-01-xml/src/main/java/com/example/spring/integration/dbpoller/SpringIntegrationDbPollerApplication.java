package com.example.spring.integration.dbpoller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("application-context.xml")
public class SpringIntegrationDbPollerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationDbPollerApplication.class, args);
	}

}
