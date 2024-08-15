package com.micro.question.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.question.entity.Question;

public interface QuestionRepo extends JpaRepository<Question, Integer>{

	List<Question> findByQuizId(Integer quizId);
}
