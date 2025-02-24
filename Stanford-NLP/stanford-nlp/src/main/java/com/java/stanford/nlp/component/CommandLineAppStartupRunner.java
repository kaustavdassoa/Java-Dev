package com.java.stanford.nlp.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.java.stanford.nlp.examples.SentimentAnalyzer;
import com.java.stanford.nlp.examples.SimpleExample;
import com.java.stanford.nlp.examples.SimpleExample;


@Component
public class CommandLineAppStartupRunner implements CommandLineRunner{

	
	private static final Logger logger = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);
    @Override
    public void run(String...args) throws Exception 
    {
        logger.info("Starting CommandLineAppStartupRunner.run()");
       // SimpleExample.start();
        String text="Never expected my office party to be hosted here. Ample space and live screening with a good glass of beer made the evening to be remembered. The rooftop part where we went to smoke was awesome too. Situated just on the main road, this place can't be missed.";
        SentimentAnalyzer.getSentimentResult(text);
    }
}
