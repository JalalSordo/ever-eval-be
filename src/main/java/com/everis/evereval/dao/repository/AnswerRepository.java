package com.everis.evereval.dao.repository;

import com.everis.evereval.dao.entity.Answer;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
    Iterable<Answer> findAllByScore(double score);
}
