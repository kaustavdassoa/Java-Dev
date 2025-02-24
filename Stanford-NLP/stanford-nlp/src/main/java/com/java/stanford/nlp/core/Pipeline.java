package com.java.stanford.nlp.core;

import java.util.Properties;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class Pipeline {
	
	private static Properties properties;
	private static String propertiesName ="tokenize, ssplit, pos, lemma, parse, ner , sentiment";

	private static StanfordCoreNLP stanfordCoreNlp;

	//Private Constructor 
	private Pipeline()
	{
		
	}
	
	//static block
	static {
		properties = new Properties();
	    properties.setProperty("annotators", propertiesName);
		
				
	}
	
	//accessors method : getPipeline
	public static StanfordCoreNLP getPipeline()
	{
		if(stanfordCoreNlp == null)
		{
			stanfordCoreNlp=new StanfordCoreNLP(properties);
		}
		
		return stanfordCoreNlp;
	}
	
	
}
