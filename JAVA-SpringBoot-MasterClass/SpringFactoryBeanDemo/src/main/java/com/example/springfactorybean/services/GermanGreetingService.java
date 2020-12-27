package com.example.springfactorybean.services;

public class GermanGreetingService implements GreetingService{
	
	GreetingsRepository greetingsRepository;
	
	GermanGreetingService(GreetingsRepository greetingsRepository)
	{
		this.greetingsRepository=greetingsRepository;
	}
	

	@Override
	public String sayGreeting() {
		return greetingsRepository.getGermanGreeting();
	}

}
