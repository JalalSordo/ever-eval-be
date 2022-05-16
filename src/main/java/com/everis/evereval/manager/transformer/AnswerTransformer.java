package com.everis.evereval.manager.transformer;

import com.everis.evereval.dao.entity.Answer;
import com.everis.evereval.manager.dto.AnswerDTO;

public class AnswerTransformer extends Transformer<Answer, AnswerDTO> {

	@Override
	public Answer toEntity(AnswerDTO dto) {
		if (dto == null) {
			return null;
		}
		return new Answer(dto.getId(), dto.getScore(), dto.getContent());

	}

	@Override
	public AnswerDTO toDTO(Answer entity) {
		if (entity == null) {
			return null;
		}
		return new AnswerDTO(entity.getId(), entity.getScore(), entity.getContent());
	}

}
