package com.nlp.sentiment.analysis.app.service;

/*
 * "Very negative" = 0 
 * "Negative" = 1 
 * "Neutral" = 2 
 * "Positive" = 3
 * "Very positive" = 4
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.nlp.sentiment.analysis.app.componet.Pipeline;
import com.nlp.sentiment.analysis.app.componet.SentimentResults;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;

import java.util.ArrayList;
import java.util.List;

import org.ejml.simple.SimpleMatrix;
import edu.stanford.nlp.pipeline.CoreSentence;

@Component
public class SentimentAnalyzer {

	 private static final Logger logger = LoggerFactory.getLogger(SentimentAnalyzer.class);
	
	 
	 
	 
     public SentimentResults getSentimentResult(String text) 
     {
    	SentimentResults sentimentResults=new SentimentResults();
		logger.info("Inside SentimentAnalyzer method ()");
    	StanfordCoreNLP stanfordCoreNlp=Pipeline.getPipeline();
    	String result="";
    	
    	Annotation annotation = stanfordCoreNlp.process(text);
    	for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
			// this is the parse tree of the current sentence
			Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
			SimpleMatrix sm = RNNCoreAnnotations.getPredictions(tree);
			String sentimentType = sentence.get(SentimentCoreAnnotations.SentimentClass.class);

			sentimentResults.setText(sentimentResults.getText()+""+sentence.toString());
			sentimentResults.setSentiment(sentimentResults.getSentiment()+"||"+sentimentType.toUpperCase());
			sentimentResults.setVeryPositiveScore(sentimentResults.getVeryPositiveScore()+(double)Math.round(sm.get(4) * 100d));
			sentimentResults.setPositiveScore(sentimentResults.getPositiveScore()+(double)Math.round(sm.get(3) * 100d));
			sentimentResults.setNeutralScore(sentimentResults.getNeutralScore()+(double)Math.round(sm.get(2) * 100d));
			sentimentResults.setNegativeScore(sentimentResults.getNegativeScore()+(double)Math.round(sm.get(1) * 100d));
			sentimentResults.setVeryNegativeScore(sentimentResults.getVeryNegativeScore()+(double)Math.round(sm.get(0) * 100d));
			
		}
    	double score=(sentimentResults.getVeryPositiveScore()+sentimentResults.getPositiveScore())-(sentimentResults.getVeryNegativeScore()+sentimentResults.getNegativeScore())+sentimentResults.getNeutralScore();
    	logger.info("SCORE = "+score);
    	sentimentResults.setScore(score);
    
    	if(score < 0)
    	{
    		sentimentResults.setCollectiveSetiment("NEGATIVE");
    	}
    	else if (score == 0)
    	{	
    		sentimentResults.setCollectiveSetiment("NEUTRAL");
    	}	
    	else 
    	{	
    		sentimentResults.setCollectiveSetiment("POSITIVE");
    	}	
    	
		return sentimentResults;
		
		
	}//getSentimentResult
     
    
}
