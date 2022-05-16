package com.everis.evereval.dao.repository;

import org.springframework.data.repository.CrudRepository;

import com.everis.evereval.dao.entity.Quiz;

public interface QuizRepository extends CrudRepository<Quiz, Long>{
	
	Iterable<Quiz> findAllByDoneAndEvaluated(boolean done,boolean evaluated);
	
	Iterable<Quiz> findAllByDoneTrueAndEvaluatedFalse();


}
