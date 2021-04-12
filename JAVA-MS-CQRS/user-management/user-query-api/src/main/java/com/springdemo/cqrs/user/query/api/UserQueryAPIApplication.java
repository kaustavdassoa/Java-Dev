package com.springdemo.cqrs.user.query.api;

import com.springdemo.cqrs.user.core.configuration.AxonConfig;
import com.springdemo.cqrs.user.core.configuration.MongoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@Import({AxonConfig.class,MongoConfig.class})
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserQueryAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserQueryAPIApplication.class, args);
	}

}
