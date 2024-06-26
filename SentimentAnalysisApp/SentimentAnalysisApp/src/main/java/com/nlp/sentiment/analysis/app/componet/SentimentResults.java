package com.nlp.sentiment.analysis.app.componet;

import org.springframework.stereotype.Component;

@Component
public class SentimentResults {
	
	private String text;
	private String sentiment;
	private double veryPositiveScore;
	private double positiveScore;
	private double neutralScore;
	private double negativeScore;
	private double veryNegativeScore;
	private double score;
	private String collectiveSetiment;
	
	public SentimentResults()
	{
		this.text="";
		this.sentiment="";
		this.collectiveSetiment="";		
	}
	
	public String getText() {
		return text;
	}
	public String getSentiment() {
		return sentiment;
	}
	public double getVeryPositiveScore() {
		return veryPositiveScore;
	}
	public double getPositiveScore() {
		return positiveScore;
	}
	public double getNeutralScore() {
		return neutralScore;
	}
	public double getNegativeScore() {
		return negativeScore;
	}
	public double getVeryNegativeScore() {
		return veryNegativeScore;
	}
	public double getScore() {
		return score;
	}
	public String getCollectiveSetiment() {
		return collectiveSetiment;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}
	public void setVeryPositiveScore(double veryPositiveScore) {
		this.veryPositiveScore = veryPositiveScore;
	}
	public void setPositiveScore(double positiveScore) {
		this.positiveScore = positiveScore;
	}
	public void setNeutralScore(double neutralScore) {
		this.neutralScore = neutralScore;
	}
	public void setNegativeScore(double negativeScore) {
		this.negativeScore = negativeScore;
	}
	public void setVeryNegativeScore(double veryNegativeScore) {
		this.veryNegativeScore = veryNegativeScore;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public void setCollectiveSetiment(String collectiveSetiment) {
		this.collectiveSetiment = collectiveSetiment;
	}
	
	
	

}
