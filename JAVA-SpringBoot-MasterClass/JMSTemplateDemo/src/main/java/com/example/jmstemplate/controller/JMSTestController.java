package com.example.jmstemplate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.jmstemplate.publisher.Sender;

@RestController
@RequestMapping (value = "/v01")
public class JMSTestController {

	@Autowired
	Sender jmsSender;
	
	@PostMapping(path = "/sendMessage")
	public void sendMessage(@RequestBody String body)
	{
		System.out.println("inside sendMessage()");
		System.out.println("Sending <"+body+">");
		jmsSender.sendMessage(body);
		System.out.println("Message Sent Successfully.");
	}
}
