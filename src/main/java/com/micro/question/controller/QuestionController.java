package com.micro.question.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.micro.question.entity.Question;
import com.micro.question.service.QuestionServiceImpl;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	public QuestionController(QuestionServiceImpl questionServiceImpl) {
		this.questionServiceImpl = questionServiceImpl;
	}

	private QuestionServiceImpl questionServiceImpl;

	@GetMapping("/{id}")
	public Question getQuestion(@PathVariable Integer id ) {
		return questionServiceImpl.getQuestion(id);
	}
	
	@GetMapping("Quiz/{quizId}")
	public List<Question> getAllQuestionByQuizID(@PathVariable Integer quizId ) {
		return questionServiceImpl.getAllQuestionByQuizID(quizId);
	}
	
	
}
