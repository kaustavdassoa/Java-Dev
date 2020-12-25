package com.example.springbeanlifecycle;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.springbeanlifecycle.service.HelloWorldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@SpringBootApplication
public class SpringBeanLifecycleDemoApplication {

	private final static Logger logger = LoggerFactory.getLogger(SpringBeanLifecycleDemoApplication.class);
	
	public static void main(String[] args) {
	
		logger.info("Inside SpringBeanLifecycleDemoApplication.main()");
		ApplicationContext ctx = SpringApplication.run(SpringBeanLifecycleDemoApplication.class, args);

		HelloWorldService helloWorldBeanInstance = (HelloWorldService) ctx.getBean("helloWorldService");
		helloWorldBeanInstance.SayHello();
		logger.info("Existing from SpringBeanLifecycleDemoApplication");

	}

}
