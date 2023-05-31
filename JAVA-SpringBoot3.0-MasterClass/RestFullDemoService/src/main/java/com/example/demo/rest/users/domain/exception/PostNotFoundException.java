package com.example.demo.rest.users.domain;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException() {
    }

    public PostNotFoundException(String message) {
        super(message);
    }
}
