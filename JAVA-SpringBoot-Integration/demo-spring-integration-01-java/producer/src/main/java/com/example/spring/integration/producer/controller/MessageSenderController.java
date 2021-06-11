package com.example.spring.integration.producer.controller;

import com.example.spring.integration.common.message.SampleMessageV1;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.support.MessageBuilder;

import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping( path = "/messagesender/v01")
@Log
public class MessageSenderController {

    private final MessageChannel inQueueRequestChannel;

    public MessageSenderController(@Qualifier("INQUEUEREQUESTCHANNEL") MessageChannel inQueueRequestChannel)
    {
        this.inQueueRequestChannel = inQueueRequestChannel;
    }

    @PostMapping("/sendmessage")
    public String sendMessage(@RequestBody String message)
    {

        log.info("Recived Message [text message]: "+message);
        SampleMessageV1 sampleMessage=new SampleMessageV1(UUID.randomUUID().toString(),"sender","consumer",message);


        Message<SampleMessageV1> inQueueMessage = MessageBuilder.withPayload(sampleMessage)
                .setHeader("dateTime", OffsetDateTime.now())
                .build();

        log.info("Generated inqueue request "+inQueueMessage.toString());
        inQueueRequestChannel.send(inQueueMessage);
        log.info("Inqueue request sent successfully [messageID :]"+sampleMessage.getMessageID());

        return sampleMessage.getMessageID();

    }

}
