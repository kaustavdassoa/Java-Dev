package com.example.productservice.exception;

import org.springframework.http.HttpStatus;

public class ProuctNotFoundException extends RuntimeException{

    HttpStatus statusCode;
    public ProuctNotFoundException(String s) {
        super(s);
    }
}
