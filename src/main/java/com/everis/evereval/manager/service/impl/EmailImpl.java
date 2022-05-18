package com.everis.evereval.manager.service.impl;


import com.everis.evereval.dao.entity.Email;
import com.everis.evereval.dao.repository.EmailRepository;
import com.everis.evereval.manager.dto.EmailDTO;
import com.everis.evereval.manager.service.EmailService;
import com.everis.evereval.manager.transformer.EmailTransformer;
import com.everis.evereval.manager.transformer.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailImpl extends GenericServiceImpl<Email, EmailDTO, Long> implements EmailService {

    private static Transformer<Email, EmailDTO> t = new EmailTransformer();
    @Autowired
    private EmailRepository emailRepository;

    public EmailImpl() {
        super(t);
        // TODO Auto-generated constructor stub
    }

    @Override
    public EmailDTO findBySubject(String subject) {
        return t.toDTO(emailRepository.findBySubject(subject));
    }

}
