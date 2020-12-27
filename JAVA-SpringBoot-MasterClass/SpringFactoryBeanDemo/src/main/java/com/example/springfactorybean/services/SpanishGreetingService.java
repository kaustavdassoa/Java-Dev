package com.example.springfactorybean.services;

public class SpanishGreetingService implements GreetingService{
	
	GreetingsRepository greetingsRepository;
	
	SpanishGreetingService(GreetingsRepository greetingsRepository)
	{
		this.greetingsRepository=greetingsRepository;
	}
	

	@Override
	public String sayGreeting() {
		return greetingsRepository.getSpanishGreeting();
	}

}
