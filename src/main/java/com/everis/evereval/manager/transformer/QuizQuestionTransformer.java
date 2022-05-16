package com.everis.evereval.manager.transformer;

import org.springframework.beans.factory.annotation.Autowired;

import com.everis.evereval.dao.entity.QuizQuestion;
import com.everis.evereval.manager.dto.QuizQuestionDTO;
import com.everis.evereval.manager.service.QuestionService;

public class QuizQuestionTransformer extends Transformer<QuizQuestion, QuizQuestionDTO> {

	AnswerTransformer answerTransformer = new AnswerTransformer();
	QuestionTransformer questionTransformer = new QuestionTransformer();
	ProposedResponseTransformer proposedResponseTransformer = new ProposedResponseTransformer();
	@Autowired
	QuestionService questionService;

	@Override
	public QuizQuestion toEntity(QuizQuestionDTO dto) {

		if (dto == null) {
			return null;
		}

		return new QuizQuestion(dto.getId(), dto.getContent(), dto.getLevel(), dto.getTechno(), dto.getType(),
				dto.getCountdown(), dto.getScore(),
				proposedResponseTransformer.toEntityList(dto.getProposedResponses()),
				answerTransformer.toEntityList(dto.getAnswers()));

	}

	@Override
	public QuizQuestionDTO toDTO(QuizQuestion entity) {

		if (entity == null) {
			return null;
		}

		return new QuizQuestionDTO(entity.getId(), entity.getContent(), entity.getLevel(), entity.getTechno(),
				entity.getType(), entity.getCountdown(), entity.getScore(),
				proposedResponseTransformer.toDTOList(entity.getProposedResponses()),
				answerTransformer.toDTOList(entity.getAnswers()));

	}

}
