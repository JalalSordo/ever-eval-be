package com.everis.evereval.manager.transformer;

import com.everis.evereval.dao.entity.Staff;
import com.everis.evereval.manager.dto.StaffDTO;

public class StaffTransformer extends Transformer<Staff, StaffDTO> {
	QuizTransformer quizTransformer = new QuizTransformer();

	@Override
	public Staff toEntity(StaffDTO dto) {
		if (dto == null) {
			return null;
		}
		return new Staff(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getMail(), dto.getRole(),
				dto.getPassword(), quizTransformer.toEntityList(dto.getQuizes()));
	}

	@Override
	public StaffDTO toDTO(Staff entity) {
		if (entity == null) {
			return null;
		}
		return new StaffDTO(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getMail(),
				entity.getRole(), entity.getPassword(),quizTransformer.toDTOList(entity.getQuizes()));
	}

}
