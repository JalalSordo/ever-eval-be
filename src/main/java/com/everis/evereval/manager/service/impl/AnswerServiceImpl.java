package com.everis.evereval.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.evereval.dao.entity.Answer;
import com.everis.evereval.dao.repository.AnswerRepository;
import com.everis.evereval.manager.dto.AnswerDTO;
import com.everis.evereval.manager.service.AnswerService;
import com.everis.evereval.manager.transformer.AnswerTransformer;
import com.everis.evereval.manager.transformer.Transformer;

@Service
public class AnswerServiceImpl extends GenericServiceImpl<Answer, AnswerDTO, Long> implements AnswerService {

	private static Transformer<Answer, AnswerDTO> t = new AnswerTransformer();
	@Autowired
	private AnswerRepository answerRepository;

	public AnswerServiceImpl() {
		super(t);
	}

	@Override
	public Iterable<AnswerDTO> findAllByScore(double score) {
		return t.toDTOList(answerRepository.findAllByScore(score));
	}

}
