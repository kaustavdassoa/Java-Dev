package com.example.springbeanlifecycle.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



@Service
public class HelloWorldService {

	
	
	private String greetingMessage;
	
	private final static Logger logger = LoggerFactory.getLogger(HelloWorldService.class);
	
	HelloWorldService()
	{
		this.greetingMessage="Hello World - from spring boot";
	}
	
	public void SayHello()
	{
		logger.info("Message :"+this.greetingMessage);
	}
	
}
