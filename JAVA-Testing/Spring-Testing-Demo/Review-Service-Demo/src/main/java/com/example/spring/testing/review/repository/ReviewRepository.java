package com.example.spring.testing.review.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.spring.testing.review.model.Review;
import java.util.Optional;

public interface ReviewRepository extends MongoRepository<Review, String> {

    Optional<Review> findByProductId(Integer productId);
}
