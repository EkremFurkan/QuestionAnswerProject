package com.questionAnswer.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.questionAnswer.service.QuestionAnswerService;

public class QuestionAnswer {
	private static final QuestionAnswerService questionAnswerService = new QuestionAnswerService();
	private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		while(true) {
			System.out.println("\nChoose an option:");
			System.out.println("1. Ask a question");
			System.out.println("2. Add a question with answers");
			System.out.println("3. Exit");
			String choice = "";
			try {
				System.out.print("Your choice: ");
				choice = reader.readLine().trim();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
			
			if(choice.equals("1")) {
				askQuestion();
				
			} else if(choice.equals("2")) {
				addQuestion();
				
			} else if(choice.equals("3")) {
				System.out.println("\nSee you next time!");
				break;
			}
		}
	}
	
	private static void askQuestion() {
		System.out.print("Enter your question: ");
		String question = "";
		try {
			question = reader.readLine().trim();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		List<String> answers = questionAnswerService.askQuestion(question);
		for(String answer: answers) {
			System.out.println("\n" + answer);
		}
	}
	
	private static void addQuestion() {
		System.out.print("Enter your question with at least one answer: ");
		String questionAnswer = "";
		try {
			questionAnswer = reader.readLine().trim();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		boolean success = questionAnswerService.addQuestion(questionAnswer);
		if(success) {
			System.out.println("Question and answers added succesfully!");
		}
	}

}
