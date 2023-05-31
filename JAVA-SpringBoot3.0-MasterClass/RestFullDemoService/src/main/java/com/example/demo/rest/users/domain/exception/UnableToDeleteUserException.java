package com.example.demo.rest.users.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class UnableToDeleteUserException extends RuntimeException{

    public UnableToDeleteUserException() {
    }

    public UnableToDeleteUserException(String message) {
        super(message);
    }
}
