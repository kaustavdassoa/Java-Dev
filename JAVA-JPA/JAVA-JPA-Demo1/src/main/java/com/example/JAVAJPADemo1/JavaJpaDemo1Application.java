package com.example.JAVAJPADemo1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class JavaJpaDemo1Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JavaJpaDemo1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Command Line interface is executed successfully");



	}
}
