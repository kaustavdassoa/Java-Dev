package com.example.spring.kafka.consumer.consumer;

import com.example.spring.kafka.consumer.service.LibEventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LibEventConsumer {

    @Autowired
    LibEventService libEventService;

    //Kafka listner can read messages from mutiple topics or from a single topic
    @KafkaListener (topics = "libEventTopic")
    public void onMessage(ConsumerRecord<Integer,String> consumerRecord) throws JsonProcessingException {
        log.info("consumer records {}",consumerRecord);
        libEventService.processLibraryEvent(consumerRecord);
    }
}
