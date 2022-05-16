
package com.everis.evereval.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everis.evereval.manager.dto.EmailDTO;
import com.everis.evereval.manager.service.EmailService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping(path = "addEmail")
	public EmailDTO addEmail(@RequestBody EmailDTO email) {
		return emailService.save(email);
	}

	@PostMapping(path = "editMail")
	public EmailDTO editEmail(@RequestBody EmailDTO email) {
		EmailDTO editedEmailDTO = null;

		Optional<EmailDTO> optionalMail = emailService.findById(email.getId());

		if (optionalMail.isPresent()) {
			EmailDTO mail = optionalMail.get();
			mail.setSubject(email.getSubject());
			mail.setMessageText(email.getMessageText());
			editedEmailDTO = emailService.save(mail);
		}
		return editedEmailDTO;
	}

	@GetMapping(path = "getAllMails")
	public Iterable<EmailDTO> getAll() {
		return emailService.findAll();
	}

	@GetMapping(path = "deleteMail")
	public String deleteById(@RequestParam long id) {
		emailService.deleteById(id);
		return "Success";
	}

	@GetMapping(path = "getBySubject")
	public EmailDTO getBySubject(@RequestParam String subject) {
		return emailService.findBySubject(subject);
	}

}
