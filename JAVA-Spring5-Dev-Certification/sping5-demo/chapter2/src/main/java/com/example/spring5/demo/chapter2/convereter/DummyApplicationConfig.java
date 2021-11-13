package com.example.spring5.demo.chapter2.convereter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class ApplicationDummyConfig {

    @Bean
    public ApplicationDummyClass getDummyObject(LocalDate date)
    {
        return new ApplicationDummyClass(date);
    }
}
