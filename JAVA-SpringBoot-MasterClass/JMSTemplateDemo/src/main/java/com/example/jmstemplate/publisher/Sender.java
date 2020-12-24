package com.example.jmstemplate.publisher;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;


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
