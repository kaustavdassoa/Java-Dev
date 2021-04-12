package com.springdemo.cqrs.user.query.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import com.springdemo.cqrs.user.core.configuration.MongoConfig;
import com.springdemo.cqrs.user.core.configuration.AxonConfig;

@SpringBootApplication
@Import({AxonConfig.class,MongoConfig.class})
public class UserQueryAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserQueryAPIApplication.class, args);
	}

}
