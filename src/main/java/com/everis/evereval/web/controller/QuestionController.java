package com.everis.evereval.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everis.evereval.dao.entity.enums.Level;
import com.everis.evereval.dao.entity.enums.Techno;
import com.everis.evereval.manager.dto.QuestionDTO;
import com.everis.evereval.manager.service.QuestionService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class QuestionController {
	@Autowired
	private QuestionService questionService;

	// find question by level and techno
	@GetMapping(path = "findQbylevel&tech")
	public List<QuestionDTO> findByLevelAndTechno(@RequestParam Level level, @RequestParam Techno techno) {
		return questionService.findByLevelAndTechno(level, techno);
	}

	// add Question
	@PostMapping(path = "/addQuestion")
	public QuestionDTO addQuestion(@RequestBody QuestionDTO qdto) {
		return questionService.save(qdto);
	}
	
	// Add Many Questions
	@PostMapping(path = "/addManyQuestions")
	public Iterable<QuestionDTO> addManyQuestions(@RequestBody Iterable<QuestionDTO> questions) {
		return questionService.saveAll(questions);
	}

	// get All question
	@GetMapping(path = "/getAllQUESTION")
	public Iterable<QuestionDTO> getAll() {
		return questionService.findAll();
	}

	@PostMapping(path = "/editQuestion")
	public QuestionDTO editQuestion(@RequestBody QuestionDTO q) {
		QuestionDTO qdto = questionService.findById(q.getId()).get();
		qdto.setContent(q.getContent());
		qdto.setLevel(q.getLevel());
		qdto.setTechno(q.getTechno());
		qdto.setType(q.getType());
		qdto.setProposedResponses(q.getProposedResponses());
		qdto.setCountdown(q.getCountdown());
		qdto.setScore(q.getScore());	

		return questionService.save(qdto);
	}

	// find by content
	@GetMapping(path = "/findbycontent")
	public QuestionDTO findQuestionbyContent(@RequestParam String content) {
		return questionService.findByContent(content);

	}

	// delete by id
	@GetMapping(path = "/deleteQuestionById")
	public String deleteById(@RequestParam Long id) {
		questionService.deleteById(id);
		return "Staff has been deleted";
	}

}
