package com.everis.evereval.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.evereval.dao.entity.Question;
import com.everis.evereval.dao.entity.enums.Level;
import com.everis.evereval.dao.entity.enums.Techno;
import com.everis.evereval.dao.repository.QuestionRepository;
import com.everis.evereval.manager.dto.QuestionDTO;
import com.everis.evereval.manager.service.QuestionService;
import com.everis.evereval.manager.transformer.QuestionTransformer;
import com.everis.evereval.manager.transformer.Transformer;

@Service
public class QuestionServiceImpl extends GenericServiceImpl<Question, QuestionDTO, Long> implements QuestionService {

	private static Transformer<Question, QuestionDTO> t = new QuestionTransformer();
	@Autowired
	private QuestionRepository questionRepository;

	public QuestionServiceImpl() {
		super(t);
	}

	@Override
	public List<QuestionDTO> findByLevelAndTechno(Level level, Techno techno) {
		return t.toDTOList(questionRepository.findByLevelAndTechno(level, techno));
	}

	@Override
	public QuestionDTO findByContent(String content) {
		return t.toDTO(questionRepository.findByContent(content));
	}
}
