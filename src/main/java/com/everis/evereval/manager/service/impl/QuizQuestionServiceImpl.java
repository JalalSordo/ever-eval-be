package com.everis.evereval.manager.service.impl;

import org.springframework.stereotype.Service;

import com.everis.evereval.dao.entity.QuizQuestion;
import com.everis.evereval.manager.dto.QuizQuestionDTO;
import com.everis.evereval.manager.service.QuizQuestionService;
import com.everis.evereval.manager.transformer.QuizQuestionTransformer;
import com.everis.evereval.manager.transformer.Transformer;

@Service
public class QuizQuestionServiceImpl extends GenericServiceImpl<QuizQuestion, QuizQuestionDTO, Long>
		implements QuizQuestionService {

	private static Transformer<QuizQuestion, QuizQuestionDTO> t = new QuizQuestionTransformer();

	public QuizQuestionServiceImpl() {
		super(t);
	}

}
