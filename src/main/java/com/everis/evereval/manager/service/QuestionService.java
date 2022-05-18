package com.everis.evereval.manager.service;

import com.everis.evereval.dao.entity.enums.Level;
import com.everis.evereval.dao.entity.enums.Techno;
import com.everis.evereval.manager.dto.QuestionDTO;

import java.util.List;

public interface QuestionService extends GenericService<QuestionDTO, Long> {
    List<QuestionDTO> findByLevelAndTechno(Level level, Techno techno);

    QuestionDTO findByContent(String content);

}
