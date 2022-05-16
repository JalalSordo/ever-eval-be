package com.everis.evereval.dao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.everis.evereval.dao.entity.Question;
import com.everis.evereval.dao.entity.enums.Level;
import com.everis.evereval.dao.entity.enums.Techno;



public interface QuestionRepository extends CrudRepository<Question, Long>{
	List<Question>  findByLevelAndTechno(Level level,Techno techno);
	Question findByContent(String content);

}
