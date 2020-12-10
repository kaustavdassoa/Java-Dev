package com.spring.integration.example.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.integration.core.GenericSelector;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileNameGenerator;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.transaction.DefaultTransactionSynchronizationFactory;
import org.springframework.integration.transaction.ExpressionEvaluatingTransactionSynchronizationProcessor;
import org.springframework.integration.transaction.PseudoTransactionManager;
import org.springframework.integration.transaction.TransactionSynchronizationFactory;
import org.springframework.messaging.MessageHandler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.beans.factory.annotation.Value;

import com.spring.integration.example.transformations.SimpleFileTransformation;

@Configuration
public class IntegrationFileConfig {

	@Value("${example.file.in}")
	String SOURCE_FOLDER;

	@Value("${example.file.out}")
	String DESTINATION_FOLDER;
	
	@Value("${example.file.processed}")
	String PROCESSED_FOLDER;
	
	@Value("${example.file.error}")
	String ERROR_FOLDER;

	@Value("${example.file.output-file-ext}")
	String OUTPUT_SUFFIX;


	int pool_size=1;
	
	@Autowired
    private ApplicationContext applicationContext;
	
	
	@Bean
	public FileWritingMessageHandler fileWriter() {
		
		FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(DESTINATION_FOLDER));
		handler.setExpectReply(false);
		handler.setFileNameGenerator(message -> message.getHeaders().getId().toString()+OUTPUT_SUFFIX);
		return handler;
	}

	@Bean
//	public FileReadingMessageSource fileReader() 
	public FileReadingMessageSource fileReader(){
		FileReadingMessageSource source = new FileReadingMessageSource();
		source.setDirectory(new File(SOURCE_FOLDER));
		source.setLoggingEnabled(true);
		return source;
	}

	
	 @Bean
	 public TaskExecutor taskExecutor() {
	        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	        taskExecutor.setCorePoolSize(1);
	        return taskExecutor;
	 }
	 
	 @Bean
	 public PseudoTransactionManager transactionManager() {
		
		 return new PseudoTransactionManager();
	    }

  
 
	 
	 
	 @Bean
	 public TransactionSynchronizationFactory transactionSynchronizationFactory() 
	 {
	        ExpressionParser parser = new SpelExpressionParser();
	        ExpressionEvaluatingTransactionSynchronizationProcessor syncProcessor =
	                new ExpressionEvaluatingTransactionSynchronizationProcessor();
	        syncProcessor.setBeanFactory(applicationContext.getAutowireCapableBeanFactory());
	      
	        syncProcessor.setAfterCommitExpression(parser.parseExpression("System.out.println('Inside AfterCommitExpression')"));
	        syncProcessor.setAfterRollbackExpression(parser.parseExpression("System.out.println('Inside AfterRollbackExpression')"));
	        return new DefaultTransactionSynchronizationFactory(syncProcessor);
	  }

	 
	
	 
	 
	
	
}
