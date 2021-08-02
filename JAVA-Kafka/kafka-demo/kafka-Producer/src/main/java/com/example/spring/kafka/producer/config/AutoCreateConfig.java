package com.example.spring.kafka.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Profile("local")
public class AutoCreateConfig {

    @Bean
    public NewTopic libEventTopic()
    {
        return TopicBuilder.name("libEventTopic")
                            .partitions(1)
                            .replicas(1)
                            .build();
    }

}
