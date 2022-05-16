package com.everis.evereval.manager.transformer;

import org.springframework.beans.factory.annotation.Autowired;

import com.everis.evereval.dao.entity.Question;
import com.everis.evereval.manager.dto.QuestionDTO;
import com.everis.evereval.manager.service.ProposedResponseService;

public class QuestionTransformer extends Transformer<Question, QuestionDTO> {

	ProposedResponseTransformer prTransformer = new ProposedResponseTransformer();
	@Autowired
	ProposedResponseService proposedResponseService;

	@Override
	public Question toEntity(QuestionDTO dto) {
		if (dto == null) {
			return null;
		}

		return new Question(dto.getId(), dto.getContent(), dto.getLevel(), dto.getTechno(), dto.getType(),dto.getCountdown(),dto.getScore(),
				prTransformer.toEntityList(dto.getProposedResponses()));
	}

	@Override
	public QuestionDTO toDTO(Question entity) {
		if (entity == null) {
			return null;
		}

		return new QuestionDTO(entity.getId(), entity.getContent(), entity.getLevel(), entity.getTechno(),
				entity.getType(),entity.getCountdown(),entity.getScore(), prTransformer.toDTOList(entity.getProposedResponses()));
	}

}
