package com.springdemo.cqrs.spring.oauth20;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
public class OAuth20ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OAuth20ServerApplication.class, args);
	}

}
