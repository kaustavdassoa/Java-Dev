package com.example.jmstemplate.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

@Service
public class SampleJmsErrorHandler implements ErrorHandler {

	private static final Logger logger = LoggerFactory.getLogger(SampleJmsErrorHandler.class);
	
	@Override
	public void handleError(Throwable t) {
		logger.info("insider custom errorHandler");
		logger.info("Error Message : "+t.getMessage());
		
	}

}
