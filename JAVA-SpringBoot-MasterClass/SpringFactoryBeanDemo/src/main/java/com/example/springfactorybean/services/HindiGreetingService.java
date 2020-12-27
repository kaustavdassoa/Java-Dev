package com.example.springfactorybean.services;

public class HindiGreetingService implements GreetingService{
	
	GreetingsRepository greetingsRepository;
	
	HindiGreetingService(GreetingsRepository greetingsRepository)
	{
		this.greetingsRepository=greetingsRepository;
	}

	@Override
	public String sayGreeting() {
		return greetingsRepository.getHindiGreeting();
	}

}
