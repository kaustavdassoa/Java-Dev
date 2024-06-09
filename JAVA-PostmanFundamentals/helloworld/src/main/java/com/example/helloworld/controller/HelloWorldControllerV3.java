package com.example.helloworld.controller;


import com.example.helloworld.dto.HelloWordRequest;
import com.example.helloworld.dto.HelloWordResponse;
import com.example.helloworld.dto.HelloWordResponseV2;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v3/hello-world")
public class HelloWorldControllerV3 {

    private final Random random = new Random();


    // GET request
    @GetMapping
    public ResponseEntity<HelloWordResponse> getHello(@RequestParam(name ="lang",required = false) String language) {

        String greetings= "Hello, World!";
        if(language !=null && !language.isEmpty()) {
            switch (language.trim().toUpperCase()){
                case "EN": break;
                case "FR": greetings="Bonjour le monde!"; break;
                case "ES": greetings="Hola Mundo!";
                default: break;
            }

        }
        HelloWordResponse response=new HelloWordResponse();
        response.setGreetings(greetings);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping (path = "/{serviceType}/api")
    public ResponseEntity<HelloWordResponseV2> getHelloPremium(@RequestParam(name ="lang",required = false) String language, @PathVariable String serviceType)
    {

        String greetings= "Hello, World!";
        if(language !=null && !language.isEmpty()) {
            switch (language.trim().toUpperCase()){
                case "EN": break;
                case "FR": greetings="Bonjour le monde!"; break;
                case "ES": greetings="Hola Mundo!";
                default: break;
            }

        }

        HelloWordResponseV2 response=new HelloWordResponseV2("",serviceType, "SYSTEM",LocalDateTime.now());
        response.setGreetings(greetings);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



    @PostMapping
    public ResponseEntity<HelloWordResponse> fastPostHello(@RequestBody HelloWordRequest request) {
        HelloWordResponse response=new HelloWordResponse();
        response.setGreetings(request.getGreetings()+" - created");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(path = "/slow")
    public ResponseEntity<HelloWordResponse> slowPostHello(@RequestBody HelloWordRequest request) {
        HelloWordResponse response=new HelloWordResponse();
        response.setGreetings(request.getGreetings()+" - created");
        if (random.nextBoolean()) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



    // POST request
    @PostMapping(path = "/random-slow")
    public ResponseEntity<HelloWordResponse> randomSlowPostHello(@RequestBody HelloWordRequest request) {
        HelloWordResponse response=new HelloWordResponse();
        response.setGreetings(request.getGreetings()+" - created");

        if (random.nextBoolean()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
