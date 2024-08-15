package com.micro.question.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.micro.question.entity.Question;
import com.micro.question.repo.QuestionRepo;

@Service
public class QuestionServiceImpl {

	private QuestionRepo questionRepo;

	public QuestionServiceImpl(QuestionRepo questionRepo) {
		this.questionRepo = questionRepo;
	}

	public Question getQuestion(Integer id) {
		return questionRepo.findById(id).orElseThrow(() -> new RuntimeException("Question not found !!"));
	}

	public List<Question> getAllQuestionByQuizID(Integer quizId) {
		return questionRepo.findByQuizId(quizId);
	}
	
	
	
}
