package com.example.spring.integration.consumer.repository;

import com.example.spring.integration.consumer.data.SampleMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleMessageRepository extends JpaRepository<SampleMessage, String> {
}
