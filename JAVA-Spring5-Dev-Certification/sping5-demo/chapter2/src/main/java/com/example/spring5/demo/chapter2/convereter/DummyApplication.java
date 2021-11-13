package com.example.spring5.demo.chapter2.convereter;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

public class ApplicationDummyClass {

    private LocalDate localDate;
    private String DummyApplicationName;

    public ApplicationDummyClass() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");\
        return LocalDate.parse("source", formatter);

    }
}
