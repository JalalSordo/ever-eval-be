package com.everis.evereval.dao.repository;

import org.springframework.data.repository.CrudRepository;

import com.everis.evereval.dao.entity.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Long>{
	Iterable<Answer> findAllByScore(double score);
}
