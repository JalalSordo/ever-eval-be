package com.everis.evereval.manager.service;

import java.util.List;

import com.everis.evereval.dao.entity.enums.Level;
import com.everis.evereval.dao.entity.enums.Techno;
import com.everis.evereval.manager.dto.QuestionDTO;

public interface QuestionService extends GenericService<QuestionDTO, Long> {
	List<QuestionDTO> findByLevelAndTechno(Level level, Techno techno);

	QuestionDTO findByContent(String content);

}
