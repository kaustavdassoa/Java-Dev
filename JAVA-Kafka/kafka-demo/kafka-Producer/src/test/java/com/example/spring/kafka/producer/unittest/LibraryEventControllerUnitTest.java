package com.example.spring.kafka.producer.unittest;

import com.example.spring.kafka.producer.controller.LibraryEventController;
import com.example.spring.kafka.producer.domain.Book;
import com.example.spring.kafka.producer.domain.LibraryEventType;
import com.example.spring.kafka.producer.domain.LibraryEvent;
import com.example.spring.kafka.producer.service.LibEventProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(LibraryEventController.class)
@AutoConfigureMockMvc
public class LibraryEventControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    LibEventProducer libEventProducer;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper=new ObjectMapper();
    }

    @Test
    @DisplayName("POST ENDPOINT - Positive Test - valid input")
    @Timeout(5)
    void validate_libraryController_post_ep_validInput() throws Exception {

        //Given
        Book book = Book.builder()
                .isbnId(123456)
                .bookName("Spring Dev")
                .bookAuthor("kaustav")
                .build();
        LibraryEvent libraryEvent=LibraryEvent.builder().
                libraryEventId(null)
                .libraryEventType(LibraryEventType.NEW)
                .book(book)
                .build();

        //When
        when(libEventProducer.sendLibraryEventSync(libraryEvent)).thenReturn(null);


        //Then
        mockMvc.perform(post("/v1/libraryevent/addBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(libraryEvent))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());


    }


    @Test
    @DisplayName("POST ENDPOINT - Negative Test - book object is null")
    @Timeout(5)
    void validate_libraryController_post_ep_invalidInput() throws Exception {

        //Given
        Book book = Book.builder()
                .isbnId(123456)
                .bookName("Spring Dev")
                .bookAuthor("kaustav")
                .build();
        LibraryEvent libraryEvent=LibraryEvent.builder().
                libraryEventId(null)
                .libraryEventType(LibraryEventType.NEW)
                .book(null)
                .build();

        //When
        when(libEventProducer.sendLibraryEventSync(libraryEvent)).thenReturn(null);

        String expectedContent="book - must not be null";

        //Then
        mockMvc.perform(post("/v1/libraryevent/addBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(libraryEvent))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(expectedContent));
                //.andExpect(content().string("Please pass the LibraryEventId"));


    }


    @Test
    @DisplayName("PUT ENDPOINT - Positive Test - valid input")
    @Timeout(5)
    void validate_libraryController_put_ep_validInput() throws Exception {

        //Given
        Book book = Book.builder()
                .isbnId(123456)
                .bookName("Spring Dev")
                .bookAuthor("kaustav")
                .build();
        LibraryEvent libraryEvent=LibraryEvent.builder().
                libraryEventId(1)
                .libraryEventType(LibraryEventType.NEW)
                .book(book)
                .build();

        //When
        when(libEventProducer.sendLibraryEventSync(libraryEvent)).thenReturn(null);


        //Then
        mockMvc.perform(put("/v1/libraryevent/updateBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(libraryEvent))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());


    }


    @Test
    @DisplayName("PUT ENDPOINT - Negative Test - libraryEventID null")
    @Timeout(5)
    void validate_libraryController_put_ep_invalidInput() throws Exception {

        //Given
        Book book = Book.builder()
                .isbnId(123456)
                .bookName("Spring Dev")
                .bookAuthor("kaustav")
                .build();
        LibraryEvent libraryEvent=LibraryEvent.builder().
                libraryEventId(null)
                .libraryEventType(LibraryEventType.NEW)
                .book(book)
                .build();

        //When
        when(libEventProducer.sendLibraryEventSync(libraryEvent)).thenReturn(null);

        String expectedContent="libraryEventID can't be null";

        //Then
        mockMvc.perform(put("/v1/libraryevent/updateBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(libraryEvent))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(expectedContent));
        //.andExpect(content().string("Please pass the LibraryEventId"));


    }
}
