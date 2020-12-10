package com.spring.integration.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author Kaustav Das
 * 
 * Add transactions and faultHandling  
 * @RefLink : https://github.com/iainporter/spring-file-poller : 
 * @RefLink : https://medium.com/@changeant/file-poller-with-spring-integration-dsl-ecb7bc996ba5
 */

@SpringBootApplication
public class SpringIntegrationExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationExampleApplication.class, args);
	}

}
