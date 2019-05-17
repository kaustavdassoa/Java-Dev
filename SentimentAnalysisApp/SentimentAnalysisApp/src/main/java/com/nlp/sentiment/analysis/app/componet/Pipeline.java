package com.nlp.sentiment.analysis.app.componet;

import java.util.Properties;

import org.springframework.stereotype.Component;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

@Component
public class Pipeline {

	
	private static Properties properties;
	private static String propertiesName ="tokenize, ssplit, pos, lemma, parse, ner , sentiment";
	private static StanfordCoreNLP stanfordCoreNlp;
	
	
	//Constructor 
	Pipeline()
	{
			
	}
		
	//static block
	static 
	{
		properties = new Properties();
	    properties.setProperty("annotators", propertiesName);
	}
		

	//getter method
	public static StanfordCoreNLP getPipeline()
	{
		if(stanfordCoreNlp == null)
		{
				stanfordCoreNlp=new StanfordCoreNLP(properties);
		}
			
		return stanfordCoreNlp;
	}
}
