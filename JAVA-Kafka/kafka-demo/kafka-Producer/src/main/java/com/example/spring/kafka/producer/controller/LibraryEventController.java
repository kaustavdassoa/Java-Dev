package com.example.spring.kafka.producer.controller;

import com.example.spring.kafka.producer.domain.LibraryEvenType;
import com.example.spring.kafka.producer.domain.LibraryEvent;
import com.example.spring.kafka.producer.service.LibEventProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/v1/libraryevent")
public class LibraryEventController {


    @Autowired
    LibEventProducer libEventProducer;

    @PostMapping("/addBook")
    public ResponseEntity<LibraryEvent> addBook(@RequestBody @Valid  LibraryEvent libraryEvent) throws JsonProcessingException {
        log.info("inside addBook() method");

        libraryEvent.setLibraryEvenType(LibraryEvenType.NEW);
        //libEventProducer.sendLibraryEvent(libraryEvent);//this method executed async-method
        SendResult<Integer, String> sendResult= libEventProducer.sendLibraryEventSync(libraryEvent);// this method execute sync-method

        if(sendResult != null)
        {
            log.info("sendResult {}",sendResult.toString());
        }

        log.info("completed addBook() method");
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);

    }

    @PutMapping("/updateBook")
    public ResponseEntity<?> updateBook(@RequestBody @Valid  LibraryEvent libraryEvent) throws JsonProcessingException {
        log.info("inside updateBook() method");
        if(libraryEvent.getLibraryEventId() == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("libraryEventID can't be null");
        }
        libraryEvent.setLibraryEvenType(LibraryEvenType.UPDATE);
        SendResult<Integer, String> sendResult= libEventProducer.sendLibraryEventSync(libraryEvent);// this method execute sync-method

        if(sendResult != null)
        {
            log.info("sendResult {}",sendResult.toString());

        }
        log.info("completed update() method");
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }
}
