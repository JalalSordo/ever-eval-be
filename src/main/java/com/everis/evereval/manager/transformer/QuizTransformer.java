package com.everis.evereval.manager.transformer;

import com.everis.evereval.dao.entity.Quiz;
import com.everis.evereval.dao.entity.QuizQuestion;
import com.everis.evereval.manager.dto.QuizDTO;
import com.everis.evereval.manager.dto.QuizQuestionDTO;

public class QuizTransformer extends Transformer<Quiz, QuizDTO> {
	private Transformer<QuizQuestion, QuizQuestionDTO> quizQuestionTransformer = new QuizQuestionTransformer();

	@Override
	public Quiz toEntity(QuizDTO dto) {
		if (dto == null) {
			return null;
		}
		return new Quiz(dto.getId(), dto.getLevel(), dto.getTechno(),
				quizQuestionTransformer.toEntityList(dto.getQuizQuestions()),dto.isDone(), dto.isEvaluated(), dto.getTotalQuestion());
	}

	@Override
	public QuizDTO toDTO(Quiz entity) {
		if (entity == null) {
			return null;
		}
		return new QuizDTO(entity.getId(), entity.getLevel(), entity.getTechno(),
				quizQuestionTransformer.toDTOList(entity.getQuizQuestions()), entity.isDone(),entity.isEvaluated(), entity.getTotalQuestion());
	}

}
