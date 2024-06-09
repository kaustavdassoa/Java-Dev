package com.example.helloworld.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/hello-world")
public class HelloWorldControllerV1 {

    // GET request
    @GetMapping
    public ResponseEntity<?> getHello() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello, World! - GET");
    }

    // POST request
    @PostMapping
    public ResponseEntity<?> postHello() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello, World! - POST");
    }

    // PUT request
    @PutMapping
    public ResponseEntity<?> putHello() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello, World! - PUT");
    }

    // DELETE request
    @DeleteMapping
    public ResponseEntity<?>  deleteHello() {
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }

    // PATCH request
    @PatchMapping
    public ResponseEntity<?> patchHello() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello, World! - PATCH");
    }

    // OPTIONS request
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> optionsHello() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello, World! - OPTIONS");
    }

    // HEAD request
    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<?> headHello(HttpRequest request) {
        HttpHeaders headers=new HttpHeaders();
        headers.addAll( request.getHeaders());
        headers.add("CUSTOM-RESPONSE-HEADER","VALUE");
        headers.add("CUSTOM-RESPONSE-HEADER2","VALUE2");
        return new ResponseEntity<>(headers,HttpStatus.OK);
    }
}
