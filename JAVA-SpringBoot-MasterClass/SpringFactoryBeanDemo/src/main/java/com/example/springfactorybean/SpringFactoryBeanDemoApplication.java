package com.example.springfactorybean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.springfactorybean.controller.GreetingController;





@SpringBootApplication
public class SpringFactoryBeanDemoApplication {

	private final static Logger logger = LoggerFactory.getLogger(SpringFactoryBeanDemoApplication.class);
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SpringFactoryBeanDemoApplication.class, args);
		GreetingController greetingController = (GreetingController) ctx.getBean("greetingController");
		greetingController.sayHello();
		
	}

}
