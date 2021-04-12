package com.springdemo.cqrs.user.cmd.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import com.springdemo.cqrs.user.core.configuration.AxonConfig;

@SpringBootApplication
@Import({AxonConfig.class})
public class UserCommandAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserCommandAPIApplication.class, args);
	}

}
