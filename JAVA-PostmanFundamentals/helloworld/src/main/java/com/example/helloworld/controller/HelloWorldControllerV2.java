package com.example.helloworld.controller;


import com.example.helloworld.dto.HelloWordRequest;
import com.example.helloworld.dto.HelloWordResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v2/hello-world")
public class HelloWorldControllerV2 {

    // GET request
     @GetMapping
    public ResponseEntity<HelloWordResponse> getHello() {

        HelloWordResponse response=new HelloWordResponse();
        response.setGreetings("Hello, World! - GET");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // POST request
    @PostMapping
    public ResponseEntity<HelloWordResponse> postHello(@RequestBody HelloWordRequest request) {
        HelloWordResponse response=new HelloWordResponse();
        response.setGreetings(request.getGreetings()+" - created");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // PUT request
    @PutMapping
    public ResponseEntity<?> putHello(@RequestBody HelloWordRequest request) {
        HelloWordResponse response=new HelloWordResponse();
        response.setGreetings(request.getGreetings()+" - updated version");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // DELETE request
    @DeleteMapping
    public ResponseEntity<String> deleteHello(@RequestBody HelloWordRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body("Greetings Deleted");
    }

    // PATCH request
    @PatchMapping
    public ResponseEntity<HelloWordResponse> patchHello(@RequestBody HelloWordRequest request) {
        HelloWordResponse response=new HelloWordResponse();
        String originalGreetings=request.getGreetings();
        String[] words=originalGreetings.split(" ");
        String modifiedGreetings = Arrays.stream(words)
                .map(word -> word.equals(words[0]) ? "namaste" : word)
                .collect(Collectors.joining(" "));
        response.setGreetings(modifiedGreetings);


        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // OPTIONS request
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> optionsHello() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello, World! - OPTIONS");
    }

    // HEAD request
    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<?> headHello(HttpRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.addAll(request.getHeaders());
        headers.add("CUSTOM-RESPONSE-HEADER", "VALUE");
        headers.add("CUSTOM-RESPONSE-HEADER2", "VALUE2");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
