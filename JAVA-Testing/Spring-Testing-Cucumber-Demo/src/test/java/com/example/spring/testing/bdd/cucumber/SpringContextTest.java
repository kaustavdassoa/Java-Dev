package com.example.spring.testing.bdd.cucumber;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CucumberDemoApplication.class)
class SpringContextTest {

	@Test
	@Description("When Spring Context Is BootStrapped then No Exception")
	void  whenSpringContextIsBootstrapped_thenNoExceptions() {
	}

}
