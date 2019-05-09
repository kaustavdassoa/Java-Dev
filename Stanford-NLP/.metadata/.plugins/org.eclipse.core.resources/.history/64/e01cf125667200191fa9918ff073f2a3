package com.java.stanford.nlp.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.java.stanford.nlp.examples.TokenizedExample;


@Component
public class CommandLineAppStartupRunner implements CommandLineRunner{

	
	private static final Logger logger = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);
    @Override
    public void run(String...args) throws Exception 
    {
        logger.info("Starting CommandLineAppStartupRunner.run()");
        TokenizedExample.start();
    }
}
