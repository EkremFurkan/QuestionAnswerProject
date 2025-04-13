package com.questionAnswer.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.questionAnswer.service.QuestionAnswerService;

class QuestionAnswerTest {

	@Test
	void testAddQuestion_withValidAndInvalidInput() {
		QuestionAnswerService qaService = new QuestionAnswerService();
		
		assertTrue(qaService.addQuestion("What is Peters favorite food? \"Pizza\" \"Spaghetti\" \"Ice cream\""));
		assertFalse(qaService.addQuestion("Invalid without answers?"));
		assertFalse(qaService.addQuestion("Too long?" + "\"".concat("a".repeat(300)).concat("\"")));
	}
	
	@Test
	void testAddQuestion_withOneValidAnswer() {
		QuestionAnswerService qaService = new QuestionAnswerService();
		
		assertTrue(qaService.addQuestion("What is TypeScript? \"A programming language\""));
	}
	
	@Test
	void testAddQuestion_withMultipleValidAnswers() {
		QuestionAnswerService qaService = new QuestionAnswerService();
		
		boolean question = qaService.addQuestion("What is the secret of life? \"42\" \"32\"");
		assertTrue(question);
		
		List<String> answers = qaService.askQuestion("What is the secret of life? ");
		assertEquals(2, answers.size());
	}
	
	@Test
	void testAddQuestion_WithTooLongQuestion() {
		QuestionAnswerService qaService = new QuestionAnswerService();
		
		String longQuestion = "A".repeat(256) + "? \"answer\"";
		assertFalse(qaService.addQuestion(longQuestion));
	}
	
	@Test
	void testAskQuestion_withUnknownQuestion() {
		QuestionAnswerService qaService = new QuestionAnswerService();
		
		List<String> answers = qaService.askQuestion("Unknown?");
		assertEquals(1, answers.size());
		assertEquals("the answer to life, universe and everything is 42",answers.get(0));
	}
}
