package com.springdemo.cqrs.spring.oauth20.repositories;

import com.springdemo.cqrs.user.core.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepositories extends MongoRepository<User,String> {

    @Query("{'account.userName': ?0 }")
    Optional<User> findByUserName(String username);
}
