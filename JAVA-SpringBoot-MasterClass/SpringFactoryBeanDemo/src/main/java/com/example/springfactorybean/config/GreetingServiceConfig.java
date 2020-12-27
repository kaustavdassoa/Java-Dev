package com.example.springfactorybean.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.example.springfactorybean.services.GreetingService;
import com.example.springfactorybean.services.GreetingsRepository;
import com.example.springfactorybean.services.GreetingsServiceFactory;

@Configuration
public class GreetingServiceConfig {

	GreetingsServiceFactory greetingsServiceFactory;
	

	GreetingServiceConfig(GreetingsRepository greetingsRepository)
	{
		this.greetingsServiceFactory= new GreetingsServiceFactory(greetingsRepository);
	}
	
	@Bean
	@Primary
	@Profile ({"default","en"})
	public GreetingService createEnglishGreetingService(GreetingsServiceFactory greetingsServiceFactory)
	{
		return greetingsServiceFactory.createGreetingService("en");
	}
	
	@Bean
	@Primary
	@Profile ("es")
	public GreetingService createSpanishGreetingService(GreetingsServiceFactory greetingsServiceFactory)
	{
		return greetingsServiceFactory.createGreetingService("es");
	}
	
	
	@Bean
	@Primary
	@Profile ("de")
	public GreetingService createGermanGreetingService(GreetingsServiceFactory greetingsServiceFactory)
	{
		return greetingsServiceFactory.createGreetingService("de");
	}
	
	
	@Bean
	@Primary
	@Profile ("in")
	public GreetingService createHindiGreetingService(GreetingsServiceFactory greetingsServiceFactory)
	{
		return greetingsServiceFactory.createGreetingService("in");
	}
}
