package com.everis.evereval.dao.repository;

import com.everis.evereval.dao.entity.Quiz;
import org.springframework.data.repository.CrudRepository;

public interface QuizRepository extends CrudRepository<Quiz, Long> {

    Iterable<Quiz> findAllByDoneAndEvaluated(boolean done, boolean evaluated);

    Iterable<Quiz> findAllByDoneTrueAndEvaluatedFalse();


}
