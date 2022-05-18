package com.everis.evereval.dao.repository;


import com.everis.evereval.dao.entity.Candidate;
import com.everis.evereval.dao.entity.Quiz;
import org.springframework.data.repository.CrudRepository;

public interface CandidateRepository extends CrudRepository<Candidate, Long> {
    Candidate findByMail(String mail);

    Candidate findByQuiz(Quiz quiz);

    Candidate findByQuizId(Long id);

    Candidate findByToken(String token);

    Iterable<Candidate> findAllByQuizEvaluated(boolean evaluated);

    Iterable<Candidate> findAllByQuizNull();

    Iterable<Candidate> findAllByConvoked(boolean convoked);

}
