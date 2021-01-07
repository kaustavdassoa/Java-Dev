package com.example.HelloWorldDemo.controller;

import com.example.HelloWorldDemo.domain.Greetings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/v01")
public class HelloWorldController {

    @Autowired
    Greetings greetings;

    @GetMapping (path = "/sayHello")
    public Greetings helloWorld()
    {
        greetings.setGreetingString("Hello World");
        greetings.setGreetingBy("Admin User");

        return greetings;
    }//
}
