package com.everis.evereval.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.evereval.dao.entity.Quiz;
import com.everis.evereval.dao.entity.enums.Type;
import com.everis.evereval.dao.repository.QuizRepository;
import com.everis.evereval.manager.dto.QuizDTO;
import com.everis.evereval.manager.dto.QuizQuestionDTO;
import com.everis.evereval.manager.service.QuizService;
import com.everis.evereval.manager.transformer.QuizTransformer;
import com.everis.evereval.manager.transformer.Transformer;

@Service
public class QuizServiceImpl extends GenericServiceImpl<Quiz, QuizDTO, Long> implements QuizService {
	private static Transformer<Quiz, QuizDTO> t = new QuizTransformer();
	@Autowired
	private QuizRepository quizRepository;

	public QuizServiceImpl() {
		super(t);

	}

	@Override
	public Iterable<QuizDTO> findAllByDoneAndEvaluated(boolean done, boolean evaluated) {
		return t.toDTOList(quizRepository.findAllByDoneAndEvaluated(done, evaluated));
	}

	@Override
	public boolean quizContainsTextAnswers(QuizDTO quizdto) {
		for (QuizQuestionDTO quizQuestionDTO : quizdto.getQuizQuestions()) {
			if (quizQuestionDTO.getType() == Type.TEXTFIELD || quizQuestionDTO.getType() == Type.TEXTAREA) {
				return true;
			}
		}
		return false;
	}

}
