package com.example.spring.testing.review.service.test;

import com.example.spring.testing.review.model.Review;
import com.example.spring.testing.review.model.ReviewEntry;
import com.example.spring.testing.review.repository.ReviewRepository;
import com.example.spring.testing.review.service.ReviewService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ReviewServiceTest {

    @MockBean
    ReviewRepository repository;

    @Autowired
    ReviewService service;

    @Test
    @DisplayName("Test findById")
    public void validate_findById()
    {
        //GIVEN
        Review mockReview = new Review("reviewId", 1, 1);
        Date now = new Date();
        mockReview.getEntries().add(new ReviewEntry("test-user", now, "Great product"));

        //WHEN
        doReturn(Optional.of(mockReview)).when(repository).findById("reviewId");

        assertAll(() ->assertTrue(service.findById("reviewId").isPresent()),
                  () -> assertEquals(1,service.findById("reviewId").stream().count()),
                  () -> assertEquals(mockReview,service.findById("reviewId").get()) );
    }

    @Test
    @DisplayName("Test findById Not Found")
    void testFindByIdNotFound() {
        // Setup our mock
        doReturn(Optional.empty()).when(repository).findById("1");

        // Execute the service call
        Optional<Review> returnedReview = service.findById("1");

        // Assert the response
        assertFalse(returnedReview.isPresent(), "Review was found, when it shouldn't be");
    }

    @Test
    @DisplayName("Test findAll")
    void testFindAll() {
        // Setup our mock
        Review mockReview = new Review("reviewId", 1, 1);
        Review mockReview2 = new Review("reviewId2", 2, 1);
        doReturn(Arrays.asList(mockReview, mockReview2)).when(repository).findAll();

        // Execute the service call
        List<Review> reviews = service.findAll();

        assertEquals(2, reviews.size(), "findAll should return 2 reviews");
    }

    @Test
    @DisplayName("Test save review")
    void testSave() {
        Review mockReview = new Review("reviewId", 1, 1);
        doReturn(mockReview).when(repository).save(any());

        Review returnedReview = service.save(mockReview);

        assertNotNull(returnedReview, "The saved review should not be null");
        assertEquals(1, returnedReview.getVersion().intValue(),
                "The version for a new review should be 1");
    }

}

