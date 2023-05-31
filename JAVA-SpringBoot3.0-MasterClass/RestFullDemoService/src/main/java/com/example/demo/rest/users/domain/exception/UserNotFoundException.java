package com.example.demo.rest.users.domain;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {


    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {

    }
}
