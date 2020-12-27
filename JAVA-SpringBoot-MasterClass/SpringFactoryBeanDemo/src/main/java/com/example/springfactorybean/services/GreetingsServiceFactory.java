package com.example.springfactorybean.services;

import org.springframework.stereotype.Component;

@Component
public class GreetingsServiceFactory {

	GreetingsRepository greetingsRepository;
	
	public GreetingsServiceFactory(GreetingsRepository greetingsRepository)
	{
		this.greetingsRepository=greetingsRepository;
	}
	
	public GreetingService createGreetingService(String lang)
	{
		switch (lang) {
		case "en":
			return new EnglishGreetingService(greetingsRepository);
		case "es":
			return new SpanishGreetingService(greetingsRepository);	
		case "de":
			return new GermanGreetingService(greetingsRepository);	
		case "in":
			return new HindiGreetingService(greetingsRepository);	
		default:
			return new EnglishGreetingService(greetingsRepository);
		}
		
	}
	
}
