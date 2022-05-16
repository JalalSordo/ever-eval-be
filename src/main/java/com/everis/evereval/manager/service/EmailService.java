package com.everis.evereval.manager.service;

import com.everis.evereval.manager.dto.EmailDTO;

public interface EmailService extends GenericService<EmailDTO, Long>{
	EmailDTO findBySubject(String subject);

}
