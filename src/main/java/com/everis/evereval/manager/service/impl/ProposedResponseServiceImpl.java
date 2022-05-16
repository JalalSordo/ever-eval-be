package com.everis.evereval.manager.service.impl;

import org.springframework.stereotype.Service;

import com.everis.evereval.dao.entity.ProposedResponse;
import com.everis.evereval.manager.dto.ProposedResponseDTO;
import com.everis.evereval.manager.service.ProposedResponseService;
import com.everis.evereval.manager.transformer.ProposedResponseTransformer;
import com.everis.evereval.manager.transformer.Transformer;

@Service
public class ProposedResponseServiceImpl extends GenericServiceImpl<ProposedResponse, ProposedResponseDTO, Long>
		implements ProposedResponseService {

	private static Transformer<ProposedResponse, ProposedResponseDTO> t = new ProposedResponseTransformer();

	public ProposedResponseServiceImpl() {

		super(t);

	}

}
