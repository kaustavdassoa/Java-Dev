package com.example.spring.kafka.producer.service;

import com.example.spring.kafka.producer.domain.LibraryEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class LibEventProducer {

    @Autowired
    KafkaTemplate<Integer,String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    private final String topic="libEventTopic";

    public ListenableFuture<SendResult<Integer,String>> sendLibraryEvent(LibraryEvent libraryEvent) throws JsonProcessingException
    {
        Integer key=libraryEvent.getLibraryEventId();
        String value=objectMapper.writeValueAsString(libraryEvent);
        ListenableFuture<SendResult<Integer,String>> listenableFuture= kafkaTemplate.sendDefault(key,value);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {

            @SneakyThrows
            @Override
            public void onFailure(Throwable error) {
                log.info("Recieved error : {}", error.getMessage());
                handleFailuerMessage(key,value,error);


            }

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                handleSuccessMessage(key,value,result);

            }
        });

        return listenableFuture;
    }

    public SendResult<Integer, String> sendLibraryEventSync(LibraryEvent libraryEvent) throws JsonProcessingException {
        Integer key=libraryEvent.getLibraryEventId();
        String value=objectMapper.writeValueAsString(libraryEvent);
        SendResult<Integer,String> sendResult=null;

        try {

           // sendResult= kafkaTemplate.sendDefault(key,value).get(1, TimeUnit.SECONDS);
           // sendResult= kafkaTemplate.send(topic,key,value).get(1, TimeUnit.SECONDS);

            List<Header> headers= List.of(new RecordHeader("event-source","scanner".getBytes()),new RecordHeader("event-target","kafka-topic".getBytes()));
            sendResult= kafkaTemplate.send(getProducerRecord(key,value,null,headers)).get(1, TimeUnit.SECONDS);
        } catch (InterruptedException| ExecutionException e) {
            log.info("InterruptedException| ExecutionException - Failed to send messages Message Key :{} Message Value :{} - Error :{}",key,value,e.getMessage());
        } catch (Exception e) {
            log.info("Exception - Failed to send messages Message Key :{} Message Value :{} - Error :{}",key,value,e.getMessage());
        }

        return sendResult;
    }

    private ProducerRecord<Integer, String> getProducerRecord(Integer key,String value,Integer partion,Iterable<Header> header) {
        return new ProducerRecord<Integer,String>(topic,partion,key,value,header);
    }

    private void handleFailuerMessage(Integer key, String value, Throwable error) throws Throwable {
        log.error("Failed to send messages Message Key :{} Message Value :{} - Error :{}",key,value,error.getMessage());
        throw error;

    }

    private void handleSuccessMessage(Integer key, String value,SendResult<Integer, String> result) {
        log.info("Message sent successfully - Message Key :{} Message Value :{} - Message Partition :{} ",key,value,result.getRecordMetadata().partition());
    }

}
