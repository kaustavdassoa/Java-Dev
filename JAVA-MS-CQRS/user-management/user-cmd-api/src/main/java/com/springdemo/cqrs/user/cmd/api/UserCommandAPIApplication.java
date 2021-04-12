package com.springdemo.cqrs.user.cmd.api;

import com.springdemo.cqrs.user.core.configuration.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@Import({AxonConfig.class})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserCommandAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserCommandAPIApplication.class, args);
	}

}
