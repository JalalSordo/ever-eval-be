package com.everis.evereval.manager.transformer;

import com.everis.evereval.dao.entity.Candidate;
import com.everis.evereval.manager.dto.CandidateDTO;

public class CandidateTransformer extends Transformer<Candidate, CandidateDTO> {

	QuizTransformer quizTransformer = new QuizTransformer();

	@Override
	public Candidate toEntity(CandidateDTO dto) {
		if (dto == null) {
			return null;
		}

		Candidate candidate = new Candidate();
		candidate.setId(dto.getId());
		candidate.setName(dto.getName());
		candidate.setMail(dto.getMail());
		candidate.setLevel(dto.getLevel());
		candidate.setTechno(dto.getTechno());
		candidate.setQuiz(quizTransformer.toEntity(dto.getQuiz()));
		candidate.setScore(dto.getScore());
		candidate.setConvoked(dto.isConvoked());

		return candidate;
	}

	@Override
	public CandidateDTO toDTO(Candidate entity) {
		if (entity == null) {
			return null;
		}
		return new CandidateDTO(entity.getId(), entity.getName(), entity.getMail(), entity.getLevel(),
				entity.getTechno(), quizTransformer.toDTO(entity.getQuiz()), entity.getScore(),entity.isConvoked());
	}

}
