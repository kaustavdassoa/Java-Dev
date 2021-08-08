package com.example.spring.kafka.consumer.service;


import com.example.spring.kafka.consumer.entity.LibraryEvent;
import com.example.spring.kafka.consumer.entity.LibraryEventType;
import com.example.spring.kafka.consumer.jpa.LibraryEventsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Optional;

@Slf4j
@Service
public class LibEventService {

    private ObjectMapper objectMapper=new ObjectMapper();

    @Autowired
    LibraryEventsRepository repository;

    @Autowired
    KafkaTemplate<Integer,String> kafkaTemplate;

    public void processLibraryEvent(ConsumerRecord<Integer,String> consumerRecord) throws JsonProcessingException {
        LibraryEvent libraryEvent=objectMapper.readValue(consumerRecord.value(),LibraryEvent.class);
        log.info("libraryEvent {}",libraryEvent);

        switch (libraryEvent.getLibraryEventType())
        {
            case NEW:
                 log.info("libraryEvent.getLibraryEventType() = {}",libraryEvent.getLibraryEventType());
                 saveLibraryEvent(libraryEvent);
                 break;
            case UPDATE:
                 log.info("libraryEvent.getLibraryEventType() = {}",libraryEvent.getLibraryEventType());
                 if(validateLibraryEvent(libraryEvent))
                 {
                     saveLibraryEvent(libraryEvent);
                 }
                 break;
            case ERROR:
                log.warn("Re-Processing error message");
                log.warn("EventID :{}, EventType :{} ",libraryEvent.getLibraryEventId(),libraryEvent.getLibraryEventType());

                //TODO: Error Reprocessing
                break;
            default:
                log.error("libraryEvent.getLibraryEventType() = {} not allowed ",libraryEvent.getLibraryEventType());
        }
    }

    public void recoverFailedMessages(ConsumerRecord <Integer,String> record) throws JsonProcessingException {
        log.info("Inside recoverFailedMessages method");
        Integer key=record.key();
        String value=record.value();
        LibraryEvent errorLibraryEvent=null;
        try {
            errorLibraryEvent=objectMapper.readValue(value,LibraryEvent.class);
        } catch (JsonProcessingException e) {
            log.error("Error in fetching the value from recoveryContext");
            e.printStackTrace();
        }
        errorLibraryEvent.setLibraryEventType(LibraryEventType.ERROR);

        ListenableFuture<SendResult<Integer,String>> listenableFuture= kafkaTemplate.sendDefault(key,objectMapper.writeValueAsString(errorLibraryEvent));
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


    }











    private boolean validateLibraryEvent(LibraryEvent libraryEvent) {


        if(libraryEvent.getLibraryEventId()==null)
        {
            throw new IllegalArgumentException("Library event ID cann't be null");
        }

        if(libraryEvent.getLibraryEventId() == 0)
        {
            throw new RecoverableDataAccessException("Libary eventID cann't be 0");
        }

        Optional<LibraryEvent> fetchedLibEvent =repository.findById(libraryEvent.getLibraryEventId());
        if(! fetchedLibEvent.isPresent())
        {
            log.error("libraryEvent with libraryEventID = {} not found ",libraryEvent.getLibraryEventId());
            throw new RuntimeException("libraryEvent with libraryEventID = "+libraryEvent.getLibraryEventId()+" not found ");
        }
        return true;
    }

    private void saveLibraryEvent(LibraryEvent libraryEvent) {
        libraryEvent.getBook().setLibraryEvent(libraryEvent);
        repository.save(libraryEvent);
    }

    private void handleFailuerMessage(Integer key, String value, Throwable error) throws Throwable {
        log.error("Failed to send messages Message Key :{} Message Value :{} - Error :{}",key,value,error.getMessage());
        throw error;

    }

    private void handleSuccessMessage(Integer key, String value, SendResult<Integer, String> result) {
        log.info("Message sent successfully - Message Key :{} Message Value :{} - Message Partition :{} ",key,value,result.getRecordMetadata().partition());
    }


}
