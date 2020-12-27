package com.example.springfactorybean.services;

import org.springframework.stereotype.Component;

@Component
public class GreetingsRepositoryImpl implements GreetingsRepository{

	@Override
	public String getEnglishGreeting() {
		return "Hello World";
	}

	@Override
	public String getSpanishGreeting() {
		return "Hola Mundo";
	}

	@Override
	public String getGermanGreeting() {
		return "Hallo Welt";
	}

	@Override
	public String getHindiGreeting() {
		return "Namaste duniya";
	}
	
	

}
