package com.everis.evereval.manager.service;

import com.everis.evereval.manager.dto.AnswerDTO;

public interface AnswerService extends GenericService<AnswerDTO, Long> {
	Iterable<AnswerDTO> findAllByScore(double score);

}
