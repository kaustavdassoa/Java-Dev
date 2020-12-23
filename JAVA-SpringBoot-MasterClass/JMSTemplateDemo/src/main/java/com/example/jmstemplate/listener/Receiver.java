package com.example.jmstemplate.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import com.example.jmstemplate.exception.SampleJmsErrorHandler;

@Component
public class Receiver { 

	@Autowired
	SampleJmsErrorHandler errorHandler;

	@Bean
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,DefaultJmsListenerContainerFactoryConfigurer configurer)
	{
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setErrorHandler(errorHandler);
		configurer.configure(factory, connectionFactory);
		return factory;
		
	}
	
	
	@JmsListener(destination = "mailbox", containerFactory = "myFactory")
	public void receiveMessage(Message message) {
		
		String textMessage;
		try {
			textMessage = ((TextMessage) message).getText();
			System.out.println("Received <" + textMessage + ">");
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
}
