package com.example.spring.kafka.consumer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
public class LibEventConsumerManualOffset implements AcknowledgingMessageListener<Integer,String> {


    @Override
    @KafkaListener(topics = "libEventTopic")
    public void onMessage(ConsumerRecord<Integer, String> consumerRecord, Acknowledgment acknowledgment)
    {
        log.info("Consumer records {}",consumerRecord);
        //acknowledgment.nack(1000); // retry after sleep is over
        acknowledgment.acknowledge(); // send positive acknowledgement
    }
}
