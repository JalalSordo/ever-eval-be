package com.everis.evereval.manager.transformer;



import com.everis.evereval.dao.entity.Email;
import com.everis.evereval.manager.dto.EmailDTO;

public class EmailTransformer extends Transformer<Email, EmailDTO> {

	@Override
	public Email toEntity(EmailDTO dto) {
		if (dto == null) {
			return null;
		}
		return new Email(dto.getId(), dto.getSubject(), dto.getMessageText());
	}

	@Override
	public EmailDTO toDTO(Email entity) {
		if (entity == null) {
			return null;
		}
		return new EmailDTO(entity.getId(), entity.getSubject(), entity.getMessageText());
	}

}
