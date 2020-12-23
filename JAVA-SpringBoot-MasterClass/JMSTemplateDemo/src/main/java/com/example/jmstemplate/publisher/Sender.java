package com.example.jmstemplate.publisher;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Component;

import com.example.jmstemplate.exception.SampleJmsErrorHandler;

@Component
public class Sender {
		
		@Autowired
		JmsTemplate jmsTemplate;
	
		public void sendMessage(String message)
		{
			jmsTemplate.send("mailbox", new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
              return session.createTextMessage(message);
            }
			});
		}
	
	
}
