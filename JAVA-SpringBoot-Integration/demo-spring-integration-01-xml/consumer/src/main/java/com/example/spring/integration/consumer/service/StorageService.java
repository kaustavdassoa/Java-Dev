package com.example.spring.integration.consumer.service;

import com.example.spring.integration.common.message.SampleMessageV1;
import com.example.spring.integration.consumer.data.SampleMessage;
import com.example.spring.integration.consumer.repository.SampleMessageRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@Log
public class StorageService {

    @Autowired
    SampleMessageRepository messageRepos;


    public boolean storeMessage(SampleMessageV1 sampleMessage)
    {

        log.info("Recived Message [sampleMessage]: "+sampleMessage.toString());
        SampleMessage persistantMessage=new SampleMessage();
        persistantMessage.setMessageID(sampleMessage.getMessageID());
        persistantMessage.setCosumerName(sampleMessage.getCosumer());
        persistantMessage.setSenderName(sampleMessage.getSender());
        persistantMessage.setMessage(sampleMessage.getMessage());
        log.info("Coverted message : "+persistantMessage.toString());
        messageRepos.save(persistantMessage);
        log.info("Message Saved successfully [Message ID] : "+persistantMessage.getMessageID());
        return true;
    }


    public void getMessage(@Header("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dateTime,
                           @Payload SampleMessageV1 sampleMessage) {

        log.info("Message received at: "+dateTime+" , message [text] :"+sampleMessage.getMessage().toString() );
        storeMessage(sampleMessage);

    }
}
