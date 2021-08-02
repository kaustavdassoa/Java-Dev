package com.example.spring.kafka.producer.unittest;

import com.example.spring.kafka.producer.domain.Book;
import com.example.spring.kafka.producer.domain.LibraryEvenType;
import com.example.spring.kafka.producer.domain.LibraryEvent;
import com.example.spring.kafka.producer.service.LibEventProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SettableListenableFuture;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LibEventProducerUnitTest {

    @Mock
    KafkaTemplate<Integer,String> kafkaTemplate;

    @InjectMocks
    LibEventProducer libEventProducer;

    @Spy
    ObjectMapper objectMapper=new ObjectMapper();

    @Test
    @DisplayName("Negative Test - sendLibraryEvent onFailure : Unable to connect to Kafka borker")
    void sendLibraryEvent_onFailure() throws JsonProcessingException {

        //Given
        Book book = Book.builder()
                .isbnId(123456)
                .bookName("Spring Dev")
                .bookAuthor("kaustav")
                .build();
        LibraryEvent libraryEvent=LibraryEvent.builder().
                libraryEventId(null)
                .libraryEvenType(LibraryEvenType.NEW)
                .book(book)
                .build();


        //TODO: Setting setting listenableFuture is NOT working.
        SettableListenableFuture listenableFuture = new SettableListenableFuture();
        listenableFuture.setException(new RuntimeException("Unable to connect to Kafka borker"));

        //When
        when(kafkaTemplate.sendDefault(null,objectMapper.writeValueAsString(libraryEvent))).thenThrow(new RuntimeException("Unable to connect to Kafka borker"));

        //Then
        assertThrows(RuntimeException.class, () ->libEventProducer.sendLibraryEvent(libraryEvent));

    }

    @Test
    @DisplayName("Positive Test - sendLibraryEvent onSuccess : send valid SendResults.")
    void sendLibraryEvent_onSuccess() throws JsonProcessingException, ExecutionException, InterruptedException {


        //Given
        Book book = Book.builder()
                .isbnId(123456)
                .bookName("Spring Dev")
                .bookAuthor("kaustav")
                .build();
        LibraryEvent libraryEvent=LibraryEvent.builder().
                libraryEventId(null)
                .libraryEvenType(LibraryEvenType.NEW)
                .book(book)
                .build();

        SettableListenableFuture listenableFuture = new SettableListenableFuture();

        ProducerRecord<Integer, String> producerRecord = new ProducerRecord("library-events", libraryEvent.getLibraryEventId(),objectMapper.writeValueAsString(libraryEvent) );
        RecordMetadata recordMetadata = new RecordMetadata(new TopicPartition("library-events", 1),
                1,1,342,System.currentTimeMillis(), 1, 2);

        SendResult<Integer, String> sendResult = new SendResult<Integer, String>(producerRecord,recordMetadata);
        listenableFuture.set(sendResult);

        //when
        when(kafkaTemplate.sendDefault(null,objectMapper.writeValueAsString(libraryEvent))).thenReturn(listenableFuture);

        //Then
        ListenableFuture<SendResult<Integer,String>> recieved_listenableFuture =   libEventProducer.sendLibraryEvent(libraryEvent);
        SendResult<Integer,String> recieved_sendResult=recieved_listenableFuture.get();
        assert(sendResult.getRecordMetadata().partition() == 1);

    }
}
