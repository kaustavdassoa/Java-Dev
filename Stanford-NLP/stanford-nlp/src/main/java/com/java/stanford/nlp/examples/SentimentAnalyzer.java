package com.java.stanford.nlp.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.java.stanford.nlp.core.Pipeline;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import org.ejml.simple.SimpleMatrix;



/*
 * "Very negative" = 0 
 * "Negative" = 1 
 * "Neutral" = 2 
 * "Positive" = 3
 * "Very positive" = 4
 */


public class SentimentAnalyzer {
	
	private static final Logger logger = LoggerFactory.getLogger(SentimentAnalyzer.class);
	public static void getSentimentResult(String text) {
		
		logger.info("Inside SentimentAnalyzer method ()");
    	StanfordCoreNLP stanfordCoreNlp=Pipeline.getPipeline();
    	
    	Annotation annotation = stanfordCoreNlp.process(text);
    	for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
			// this is the parse tree of the current sentence
			Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
			SimpleMatrix sm = RNNCoreAnnotations.getPredictions(tree);
			String sentimentType = sentence.get(SentimentCoreAnnotations.SentimentClass.class);

			
			logger.info("sentence :"+sentence.toString());
			logger.info("sentimentClass.setVeryPositive = "+(double)Math.round(sm.get(4) * 100d));
			logger.info("sentimentClass.setPositive ="+(double)Math.round(sm.get(3) * 100d));
			logger.info("sentimentClass.setNeutral ="+(double)Math.round(sm.get(2) * 100d));
			logger.info("sentimentClass.setNegative = "+(double)Math.round(sm.get(1) * 100d));
			logger.info("sentimentClass.setVeryNegative= "+(double)Math.round(sm.get(0) * 100d));
			
			logger.info("sentimentResult.setSentimentScore= "+RNNCoreAnnotations.getPredictedClass(tree));
			logger.info("sentimentResult.setSentimentType ="+sentimentType);
			
		}
		
		
	}//getSentimentResult

}
