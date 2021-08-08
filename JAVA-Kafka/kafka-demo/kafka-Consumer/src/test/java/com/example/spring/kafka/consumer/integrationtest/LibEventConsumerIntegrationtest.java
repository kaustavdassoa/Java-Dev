package com.example.spring.kafka.consumer.integrationtest;

import com.example.spring.kafka.consumer.entity.Book;
import com.example.spring.kafka.consumer.entity.LibraryEvent;
import com.example.spring.kafka.consumer.entity.LibraryEventType;
import com.example.spring.kafka.consumer.jpa.LibraryEventsRepository;
import com.example.spring.kafka.consumer.consumer.LibEventConsumer;
import com.example.spring.kafka.consumer.service.LibEventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@EmbeddedKafka(topics = {"libEventTopic"}, partitions = 3)
@TestPropertySource(properties = {"spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}"
        , "spring.kafka.consumer.bootstrap-servers=${spring.embedded.kafka.brokers}"})
public class LibEventConsumerIntegrationtest {

    @Autowired
    EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    KafkaListenerEndpointRegistry endpointRegistry;

    @Autowired
    KafkaTemplate<Integer, String> kafkaTemplate;

    @Autowired
    LibraryEventsRepository repository;

    ObjectMapper objectMapper = new ObjectMapper();


    @SpyBean
    LibEventConsumer libEventConsumer;

    @SpyBean
    LibEventService libEventService;

    @BeforeEach
    void setUp() {

        for (MessageListenerContainer messageListenerContainer : endpointRegistry.getListenerContainers()) {
            //wait for all the consumer to register - before running the test
            ContainerTestUtils.waitForAssignment(messageListenerContainer, embeddedKafkaBroker.getPartitionsPerTopic());
        }
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }


    @Test
    @DisplayName("Positive Test - NEW EventType - Validate LibraryEvent consumer")
    void validateLibraryEvenConsumer_NEW_EventType() throws JsonProcessingException, ExecutionException, InterruptedException {

        //Given
        Book book = Book.builder()
                .isbnId(1000)
                .bookAuthor("kaustav das")
                .bookName("Spring Kafka notes")
                .build();
        LibraryEvent libraryEvent = LibraryEvent.builder()
                .libraryEventId(null)
                .libraryEventType(LibraryEventType.NEW)
                .book(book)
                .build();
        kafkaTemplate.sendDefault(objectMapper.writeValueAsString(libraryEvent)).get();

        //When
        CountDownLatch latch = new CountDownLatch(1);
        latch.await(3, TimeUnit.SECONDS); //TODO : Read about - https://www.baeldung.com/java-countdown-latch


        //then
        verify(libEventConsumer, times(1)).onMessage(isA(ConsumerRecord.class));
        verify(libEventService, times(1)).processLibraryEvent(isA(ConsumerRecord.class));

        //validate database
        List<LibraryEvent> listOfLibaryEvents = (List<LibraryEvent>) repository.findAll();
        assertEquals(1, listOfLibaryEvents.size());

        listOfLibaryEvents.forEach(libEvent ->
        {
            assertNotNull(libEvent.getLibraryEventId()); // eventID is not null
            assertNotNull(libEvent.getBook()); //Book object is not null
            assertEquals(LibraryEventType.NEW, libEvent.getLibraryEventType());
        });


    }


    @Test
    @DisplayName("Positive Test - UPDATE EventType - Validate LibraryEvent consumer")
    void validateLibraryEvenConsumer_UPDATE_EventType() throws JsonProcessingException, ExecutionException, InterruptedException {
        //Given
        Book book = Book.builder()
                .isbnId(1000)
                .bookAuthor("kaustav das")
                .bookName("Spring Kafka notes")
                .build();
        LibraryEvent libraryEvent = LibraryEvent.builder()
                .libraryEventId(null)
                .libraryEventType(LibraryEventType.NEW)
                .book(book)
                .build();

        repository.save(libraryEvent);
        libraryEvent.setLibraryEventType(LibraryEventType.UPDATE);

        //When
        kafkaTemplate.sendDefault(objectMapper.writeValueAsString(libraryEvent)).get();
        CountDownLatch latch = new CountDownLatch(1);
        latch.await(3, TimeUnit.SECONDS); //TODO : Read about - https://www.baeldung.com/java-countdown-latch


        //Then
        verify(libEventConsumer,times(1)).onMessage(isA(ConsumerRecord.class));
        verify(libEventService,times(1)).processLibraryEvent(isA(ConsumerRecord.class));

        Optional<LibraryEvent> libraryEventOptional=repository.findById(1);
        assertNotNull(libraryEventOptional);
        assertTrue(libraryEventOptional.isPresent());
        assertEquals(LibraryEventType.UPDATE,libraryEventOptional.get().getLibraryEventType());


    }


    @Test
    @DisplayName("Negative Test - invalid EventType - Validate LibraryEvent consumer")
    void validateLibraryEvenConsumer_invalid_EventType()
    {
        //TODO: invalid

    }

    @Test
    @DisplayName("Negative Test - UPDATE EventType - EventIDNull - 1 retry")
    void validateLibraryEvenConsumer_UPDATE_EventType_eventIDNull() throws JsonProcessingException, InterruptedException, ExecutionException {

        Integer libraryEventId = null;
        String json = "{\"libraryEventId\":" + libraryEventId + ",\"libraryEventType\":\"UPDATE\",\"book\":{\"isbnId\":456,\"bookName\":\"Kafka Using Spring Boot\",\"bookAuthor\":\"Dilip\"}}";

        //When
        kafkaTemplate.sendDefault(libraryEventId, json).get();
        CountDownLatch latch = new CountDownLatch(1);
        latch.await(3, TimeUnit.SECONDS); //TODO : Read about - https://www.baeldung.com/java-countdown-latch

        //Then
        verify(libEventConsumer,times(1)).onMessage(isA(ConsumerRecord.class));
        verify(libEventService,times(1)).processLibraryEvent(isA(ConsumerRecord.class));

    }


    @Test
    @DisplayName("Negative Test - UPDATE EventType - EventID=0 - 3 retries")
    void validateLibraryEvenConsumer_UPDATE_EventType_eventIDZero() throws JsonProcessingException, InterruptedException, ExecutionException {

        Integer libraryEventId = 0;
        String json = "{\"libraryEventId\":" + libraryEventId + ",\"libraryEventType\":\"UPDATE\",\"book\":{\"isbnId\":456,\"bookName\":\"Kafka Using Spring Boot\",\"bookAuthor\":\"Dilip\"}}";

        //When
        kafkaTemplate.sendDefault(libraryEventId, json).get();
        CountDownLatch latch = new CountDownLatch(1);
        latch.await(3, TimeUnit.SECONDS); //TODO : Read about - https://www.baeldung.com/java-countdown-latch

        //Then
        //verify(libEventConsumer,times(3)).onMessage(isA(ConsumerRecord.class));
        verify(libEventService,times(4)).processLibraryEvent(isA(ConsumerRecord.class));
        verify(libEventService,times(1)).recoverFailedMessages(isA(ConsumerRecord.class));

    }
}
