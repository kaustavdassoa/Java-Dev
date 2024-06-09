package com.example.productservice.exception;

import org.springframework.http.HttpStatus;

public class InsufficientProductQuantityException extends RuntimeException {
   HttpStatus statusCode;

    public InsufficientProductQuantityException(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public InsufficientProductQuantityException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
