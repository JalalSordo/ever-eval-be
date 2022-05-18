package com.everis.evereval.dao.repository;

import com.everis.evereval.dao.entity.Question;
import com.everis.evereval.dao.entity.enums.Level;
import com.everis.evereval.dao.entity.enums.Techno;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface QuestionRepository extends CrudRepository<Question, Long> {
    List<Question> findByLevelAndTechno(Level level, Techno techno);

    Question findByContent(String content);

}
