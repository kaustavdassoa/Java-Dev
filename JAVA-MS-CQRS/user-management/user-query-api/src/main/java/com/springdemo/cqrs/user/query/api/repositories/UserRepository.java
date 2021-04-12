package com.springdemo.cqrs.user.query.api.repositories;

import com.springdemo.cqrs.user.core.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

}
