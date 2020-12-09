package com.spring.integration.example.integration;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;


import com.spring.integration.example.config.IntegrationFileConfig;
import com.spring.integration.example.transformations.SimpleFileTransformation;

@Configuration
public class FileIntegration {

	@Autowired
	SimpleFileTransformation transformer;
	
	@Autowired
	IntegrationFileConfig config;
	

	
	@Bean
	public IntegrationFlow integrationFlow() {
	return IntegrationFlows.from(config.fileReader(),
	        		configurer -> configurer.poller(Pollers.fixedDelay(500)))
	        		.filter(source -> ((File) source).getName().endsWith(".jpg"))
	        		.transform(transformer, "getBase64String")
	        		.handle(config.fileWriter())
	                .get();
	    	
	    }
	

}
