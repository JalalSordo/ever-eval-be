package com.everis.evereval.manager.transformer;

import com.everis.evereval.dao.entity.ProposedResponse;
import com.everis.evereval.manager.dto.ProposedResponseDTO;

public class ProposedResponseTransformer extends Transformer<ProposedResponse, ProposedResponseDTO> {

	@Override
	public ProposedResponse toEntity(ProposedResponseDTO dto) {
		if (dto == null) {
			return null;
		}

		return new ProposedResponse(dto.getId(), dto.getContent(), dto.isState(),dto.getScore());

	}

	@Override
	public ProposedResponseDTO toDTO(ProposedResponse entity) {
		if (entity == null) {
			return null;
		}

		return new ProposedResponseDTO(entity.getId(), entity.getContent(), entity.isState(),entity.getScore());
	}

}
