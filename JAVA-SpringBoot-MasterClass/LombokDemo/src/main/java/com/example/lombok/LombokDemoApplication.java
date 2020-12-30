package com.example.lombok;

import com.example.lombok.service.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LombokDemoApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(LombokDemoApplication.class, args);
		CustomerService customerServiceInstance = (CustomerService) ctx.getBean("customerService");
		customerServiceInstance.printCustomerDetails();
	}

}
