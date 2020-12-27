package com.example.springfactorybean.services;

public class EnglishGreetingService implements GreetingService{
	
	GreetingsRepository greetingsRepository;
	
	EnglishGreetingService(GreetingsRepository greetingsRepository)
	{
		this.greetingsRepository=greetingsRepository;
	}
	

	@Override
	public String sayGreeting() {
		return greetingsRepository.getEnglishGreeting();
	}

}
