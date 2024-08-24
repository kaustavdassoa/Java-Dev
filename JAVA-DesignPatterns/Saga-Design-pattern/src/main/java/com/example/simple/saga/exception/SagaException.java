package com.example.simple.saga.exception;

public class SagaException extends RuntimeException{
    public SagaException(String e) {
        super(e);
    }
}
