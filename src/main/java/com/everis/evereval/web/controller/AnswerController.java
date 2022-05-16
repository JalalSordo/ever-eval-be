package com.everis.evereval.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.evereval.manager.dto.AnswerDTO;
import com.everis.evereval.manager.service.AnswerService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AnswerController {
	@Autowired
	private AnswerService answerService;

	// Get answer without score
	@GetMapping(path = "getAnswerNoScore")
	public Iterable<AnswerDTO> getAnswerNoScore() {
		return answerService.findAllByScore(0);

	}

}
