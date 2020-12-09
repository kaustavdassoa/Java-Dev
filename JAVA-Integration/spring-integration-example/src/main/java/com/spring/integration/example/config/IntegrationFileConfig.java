package com.spring.integration.example.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.GenericSelector;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileNameGenerator;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.messaging.MessageHandler;

import com.spring.integration.example.transformations.SimpleFileTransformation;

@Configuration
public class IntegrationFileConfig {
	
	@Bean
	public GenericSelector<File> onlyTxt() {
		return new GenericSelector<File>() {

			@Override
			public boolean accept(File source) {
				return source.getName().endsWith(".txt");
			}
		};
	}

	@Bean
	public FileWritingMessageHandler fileWriter() {
		FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("destination"));
		handler.setExpectReply(false);
		handler.setFileNameGenerator(message -> message.getHeaders().getId().toString()+".txt");
		return handler;
	}

	@Bean
	public FileReadingMessageSource fileReader() {
		FileReadingMessageSource source = new FileReadingMessageSource();
		source.setDirectory(new File("source"));
		return source;
	}

	

	
	
}
