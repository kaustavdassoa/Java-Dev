package com.example.spring.kafka.producer.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class LibraryEventControllerAdvice {


    ResponseEntity<?> handdle_MethodArgumentNotValidException(MethodArgumentNotValidException ex)
    {
        List<FieldError> fieldErrorList=ex.getBindingResult().getFieldErrors();
        String errorMessage= fieldErrorList.stream()
                .map(fieldError -> fieldError.getField()+" - "+fieldError.getDefaultMessage())
                .sorted()
                .collect(Collectors.joining(","));
        log.error("Error Message : "+errorMessage);
        return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);

    }
}
