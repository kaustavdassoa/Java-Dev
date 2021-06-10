package com.example.spring.testing.review.controller.test;

import com.example.spring.testing.review.model.Review;
import com.example.spring.testing.review.model.ReviewEntry;
import com.example.spring.testing.review.repository.ReviewRepository;
import com.example.spring.testing.review.service.ReviewService;
import com.example.spring.testing.review.web.ReviewController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReviewControllerTest {

    @MockBean
    private ReviewService service;

    @Autowired
    private MockMvc mockMvc;

    /**
     * Create a DateFormat that we can use to compare SpringMVC returned dates to expected values.
     */
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSZ");

    @BeforeAll
    static void beforeAll() {
        // Spring's dates are configured to GMT, so adjust our timezone accordingly
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
    }


    @Test
    @DisplayName("GET /review/reviewId - Found")
    void testGetReviewByIdFound() throws Exception {
        // Setup our mocked service
        Review mockReview = new Review("reviewId", 1, 1);
        Date now = new Date();
        mockReview.getEntries().add(new ReviewEntry("test-user", now, "Great product"));
        doReturn(Optional.of(mockReview)).when(service).findById("reviewId");

        // Execute the GET request
        mockMvc.perform(get("/review/{id}", "reviewId"))

                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate the headers
                .andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
                .andExpect(header().string(HttpHeaders.LOCATION, "/review/reviewId"))

                // Validate the returned fields
                .andExpect(jsonPath("$.id", is("reviewId")))
                .andExpect(jsonPath("$.productId", is(1)))
                .andExpect(jsonPath("$.entries.length()", is(1)))
                .andExpect(jsonPath("$.entries[0].username", is("test-user")))
                .andExpect(jsonPath("$.entries[0].review", is("Great product")));
               //.andExpect(jsonPath("$.entries[0].date", is(df.format(now))));
    }
}
