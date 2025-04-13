package com.questionAnswer.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionAnswerService {
	private static final int MAX_LENGTH = 255;
	private final Map<String, List<String>> questionAnswers = new HashMap<>();
	
	public QuestionAnswerService() {
		
	}
	
	public List<String> askQuestion(String question) {
		question = question.trim();
		if(questionAnswers.containsKey(question)) {
			return questionAnswers.get(question);
		} 
		return Arrays.asList("the answer to life, universe and everything is 42");
	}
	
	public boolean addQuestion(String questionAnswer) {
		if(!questionAnswer.contains("?")) {
			System.out.println("Invalid format. Missing ? seperator.");
			return false;
		}
		int questionMarkIndex = questionAnswer.indexOf("?");
		String question = questionAnswer.substring(0, questionMarkIndex + 1);
		if(question.length() > MAX_LENGTH) {
			System.out.println("Your question is too long! (max. 255 chars)");
			return false;
		}
		
		String answers = questionAnswer.substring(questionMarkIndex + 1).trim();
		if(answers.length() < 1) {
			System.out.println("You need to add at least one answer!");
			return false;
		}
		List<String> parsedAnswers = parseAnswers(answers.trim());
		
		for(String answer: parsedAnswers) {
			if(answer.length() > MAX_LENGTH) {
				System.out.println("One or more of the answers is too long! (max. 255 chars)");
				return false;
			}
		}
		
		questionAnswers.put(question, parsedAnswers);
		return true;
	}
	
	private List<String> parseAnswers(String questionAnswer){
		List<String> answers = new ArrayList<>();
		StringBuilder buildAnswer = new StringBuilder();
		boolean insideAnswer = false;
		char bulletPointSymbol = '\u2022';
		
		for(char c : questionAnswer.trim().toCharArray()) {
			if(c == '"') {
				if (insideAnswer) {
					answers.add(bulletPointSymbol + " " + buildAnswer.toString());
					buildAnswer.setLength(0);
				}
				insideAnswer = !insideAnswer;
			} else if(insideAnswer) {
				buildAnswer.append(c);
			}
		}
		
		return answers;
	}

}
