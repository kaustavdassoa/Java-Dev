package com.example.jmstemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * @@Also See : https://docs.spring.io/spring-framework/docs/3.0.x/spring-framework-reference/html/jms.html
 * @author Kaustav Das
 *
 */

@SpringBootApplication
@EnableJms
public class JmsTemplateDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmsTemplateDemoApplication.class, args);

	}

}
