package com.example.spring.kafka.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaProducerApplication {


	/**
	 *
	 * KafkaStreamProducerApplication code will generate invoice , transforms and send it kafka queue.
	 * Date : 30-09-2021
	 * Author : Kaustav Das
	 **/
	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

}
