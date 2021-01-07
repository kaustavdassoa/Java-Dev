package com.example.HelloWorldDemo.configuration;

import com.example.HelloWorldDemo.domain.Greetings;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GreetingConfig {

    @Bean
    public Greetings getGreetings()
    {
        return new Greetings();
    }

}
