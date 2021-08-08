package com.example.spring.kafka.consumer.jpa;

import com.example.spring.kafka.consumer.entity.LibraryEvent;
import org.springframework.data.repository.CrudRepository;

public interface LibraryEventsRepository extends CrudRepository<LibraryEvent,Integer> {
}
