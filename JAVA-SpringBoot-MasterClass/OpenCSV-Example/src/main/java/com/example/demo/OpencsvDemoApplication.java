package com.example.demo;

import com.example.demo.service.ReadCSVFile;
import com.example.demo.service.WriteCSVFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpencsvDemoApplication implements CommandLineRunner {

	@Autowired
	ReadCSVFile inService;

	@Autowired
	WriteCSVFile outService;



	public static void main(String[] args) {
		SpringApplication.run(OpencsvDemoApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {

		outService.writeFile(inService.readCSV());
		System.exit(1);

	}
}
