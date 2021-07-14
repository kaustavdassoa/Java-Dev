package com.example.spring.integration.dbpoller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIntegrationDbPollerApplication {


	/**
	 * Idea : https://stackoverflow.com/questions/49204369/spring-integrations-java-dsl-with-h2-adapter
	 * Reference doc : https://docs.spring.io/spring-integration/docs/current/reference/html/dsl.html
	 * https://docs.spring.io/spring-integration/docs/current/reference/html/jdbc.html
	 *
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationDbPollerApplication.class, args);
	}

}
