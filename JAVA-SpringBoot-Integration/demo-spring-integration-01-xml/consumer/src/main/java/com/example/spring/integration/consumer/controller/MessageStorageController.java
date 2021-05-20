package com.example.spring.integration.consumer.controller;


import com.example.spring.integration.common.message.SampleMessageV1;
import com.example.spring.integration.consumer.data.SampleMessage;
import com.example.spring.integration.consumer.service.StorageService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( path = "/messagesender/v01")
@Log
public class MessageStorageController {


    @Autowired
    StorageService storageService;


    @PostMapping("/savemessage")
    public String saveMessage(@RequestBody SampleMessage message)
    {

        log.info("Recived Message [SampleMessage]: "+message.toString());
        SampleMessageV1 sampleMessageV1=new SampleMessageV1();
        sampleMessageV1.setMessageID(message.getMessageID());
        sampleMessageV1.setCosumer(message.getCosumerName());
        sampleMessageV1.setSender(message.getSenderName());
        sampleMessageV1.setMessage(message.getMessage());
        storageService.storeMessage(sampleMessageV1);

        return sampleMessageV1.getMessageID();

    }

}
