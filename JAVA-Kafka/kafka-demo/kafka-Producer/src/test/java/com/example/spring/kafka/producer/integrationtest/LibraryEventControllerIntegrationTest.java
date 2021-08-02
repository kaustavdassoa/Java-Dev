package com.example.spring.kafka.producer.integrationtest;

import com.example.spring.kafka.producer.domain.Book;
import com.example.spring.kafka.producer.domain.LibraryEvenType;
import com.example.spring.kafka.producer.domain.LibraryEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EmbeddedKafka(topics = {"libEventTopic"}, partitions = 3)
@TestPropertySource(properties = {"spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
        "spring.kafka.admin.properties.bootstrap.servers=${spring.embedded.kafka.brokers}"})
public class LibraryEventControllerIntegrationTest {

    /*
    NOTE : Do not autowired RestTemplate, use TestRestTemplate
    for using SpringBootTest.WebEnvironment.RANDOM_PORT
     */
    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    EmbeddedKafkaBroker embeddedKafkaBroker;


    private Consumer consumer;


    @BeforeEach
    @Timeout(5 )
    void setUp() {
        Map<String,Object> configs=new HashMap<>(KafkaTestUtils.consumerProps("group1", "true", embeddedKafkaBroker));
        consumer=new DefaultKafkaConsumerFactory<>(configs,new IntegerDeserializer(),new StringDeserializer()).createConsumer();
        embeddedKafkaBroker.consumeFromAllEmbeddedTopics(consumer);

    }

    @AfterEach
    void tearDown() {
        consumer.close();
    }

    @Test
    @DisplayName("POST ENDPOINT - Positive Test - Send Validate LibraryEvent ")
    void postLibraryEvent_post_endpint() throws JsonProcessingException {

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
        HttpHeaders headers=new HttpHeaders();
        headers.set("content-type", MediaType.APPLICATION_JSON.toString());

        HttpEntity<LibraryEvent> request= new HttpEntity<>(libraryEvent,headers);

        //Then
        ResponseEntity<LibraryEvent> response= restTemplate.exchange("/v1/libraryevent/addBook", HttpMethod.POST,request, LibraryEvent.class);

        //When
        assertEquals(HttpStatus.CREATED,response.getStatusCode(), () -> "Status code should be equal to HttpStatus.CREATED");
        ConsumerRecord<Integer,String> consumerRecord=KafkaTestUtils.getSingleRecord(consumer,"libEventTopic");
        String recievedMessage=consumerRecord.value();
        ObjectMapper objectMapper=new ObjectMapper();
        String actualMessage=objectMapper.writeValueAsString(libraryEvent);
        assertEquals(recievedMessage,actualMessage,() -> "Validate payload");
    }


    @Test
    @DisplayName("PUT ENDPOINT - Positive Test - Send Validate LibraryEvent ")
    void postLibraryEvent_put_endpint() throws JsonProcessingException {

        //Given
        Book book = Book.builder()
                .isbnId(123456)
                .bookName("Spring Dev")
                .bookAuthor("kaustav")
                .build();
        LibraryEvent libraryEvent=LibraryEvent.builder().
                libraryEventId(1)
                .book(book)
                .build();

        LibraryEvent expectedLibraryEvent=LibraryEvent.builder().
                libraryEventId(1)
                .libraryEvenType(LibraryEvenType.UPDATE)
                .book(book)
                .build();
        HttpHeaders headers=new HttpHeaders();
        headers.set("content-type", MediaType.APPLICATION_JSON.toString());

        HttpEntity<LibraryEvent> request= new HttpEntity<>(libraryEvent,headers);

        //Then
        ResponseEntity<LibraryEvent> response= restTemplate.exchange("/v1/libraryevent/updateBook", HttpMethod.PUT,request, LibraryEvent.class);

        //When
        assertEquals(HttpStatus.CREATED,response.getStatusCode(), () -> "Status code should be equal to HttpStatus.CREATED");
        ConsumerRecord<Integer,String> consumerRecord=KafkaTestUtils.getSingleRecord(consumer,"libEventTopic");
        String recievedMessage=consumerRecord.value();
        ObjectMapper objectMapper=new ObjectMapper();
        String actualMessage=objectMapper.writeValueAsString(expectedLibraryEvent);
        assertEquals(recievedMessage,actualMessage,() -> "Validate payload");
    }
}
