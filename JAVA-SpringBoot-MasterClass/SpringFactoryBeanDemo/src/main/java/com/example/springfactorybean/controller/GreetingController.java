package com.example.springfactorybean.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.springfactorybean.services.GreetingService;



@Component
public class GreetingController {
	
	private final static Logger logger = LoggerFactory.getLogger(GreetingController.class);
	
	GreetingService greetingService;
	
	public GreetingController(GreetingService greetingService)
	{
		this.greetingService=greetingService;
	}
	
	
	
	public void sayHello()
	{
		logger.info("SayHello() method is executed");
		logger.info("greetingService.sayGreeting()="+greetingService.sayGreeting());
		
	}

}
