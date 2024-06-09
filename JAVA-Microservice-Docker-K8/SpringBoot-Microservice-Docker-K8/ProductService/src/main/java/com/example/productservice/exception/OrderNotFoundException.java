package com.example.orderservice.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class OrderNotFoundException extends RuntimeException{

    HttpStatus statusCode;

    public OrderNotFoundException() {
    }

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message,HttpStatus statusCode) {
        super(message);
        this.statusCode=statusCode;
    }
}
