package com.everis.evereval.web.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everis.evereval.mailsender.MailConfig;
import com.everis.evereval.manager.dto.CandidateDTO;
import com.everis.evereval.manager.dto.QuizDTO;
import com.everis.evereval.manager.service.CandidateService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CandidateController {

	@Autowired
	private CandidateService candidateService;

	@Autowired
	private MailConfig emailConfig;

	// add candidate
	@PostMapping(path = "/addCandidate")
	public CandidateDTO addCandidate(@RequestBody CandidateDTO cdto) {

		return candidateService.save(cdto);
	}

	// update Candidate
	@PostMapping(path = "/updateCandidate")
	public CandidateDTO updateCandidate(@RequestBody CandidateDTO c) {
		CandidateDTO cdto = candidateService.findById(c.getId()).get();
		cdto.setName(c.getName());
		cdto.setMail(c.getMail());
		cdto.setLevel(c.getLevel());
		cdto.setTechno(c.getTechno());
		cdto = c;

		candidateService.save(cdto);

		return candidateService.save(cdto);
	}

	// find all candidate
	@GetMapping(path = "/findAllC")
	public Iterable<CandidateDTO> findAllCandidate() {
		return candidateService.findAll();

	}

	// delete candidate by id
	@GetMapping(path = "/deleteCandidateById")
	public String deleteById(@RequestParam Long id) {
		candidateService.deleteById(id);
		return "Success";
	}

	// Get candidate by mail = "/getCandidateByMail")
	@GetMapping(path = "/getCandidateByMail")
	public CandidateDTO getCandidatebyMail(@RequestParam String mail) {
		return candidateService.findByMail(mail);
	}

	// Get candidate by quiz id getCandidateByQuizId
	@GetMapping("/getCandidateByQuizId/{id}")
	public CandidateDTO getCandidateByQuizId(@PathVariable Long id) {
		return this.candidateService.findByQuizId(id);
	}

	// Get candidate by token getCandidateByToken
	@RequestMapping(value = "/getCandidateByToken", method = { RequestMethod.GET, RequestMethod.POST })
	public CandidateDTO getCandidateByToken(@RequestParam("token") String token) {

		return this.candidateService.findByToken(token);

	}

	// Add quiz to candidate
	@PostMapping(path = "addQuizToC")
	public String addQuizToCandidate(@RequestBody CandidateDTO cdto) throws MessagingException {
		CandidateDTO c = candidateService.findById(cdto.getId()).get();
		c.setQuiz(cdto.getQuiz());
		candidateService.save(c);
		candidateService.generateTokenForCandidate(cdto.getId());
		candidateService.sendConvocationToCandidate(cdto.getId());

		return "Success";
	}

	// Add score to candidate
	@PostMapping(path = "addScoreToCandidate")
	public String addScoreToCandidate(@RequestBody QuizDTO qdto, @RequestParam double score) {
		CandidateDTO c = candidateService.findByQuiz(qdto);
		c.setScore(score);
		c.getQuiz().setEvaluated(true);
		candidateService.save(c);
		return "Success";
	}

	@GetMapping(path = "getCandidatewithoutquiz")
	public Iterable<CandidateDTO> getCandidatewithoutquiz() {
		Iterable<CandidateDTO> c = candidateService.findAllByQuizNull();

		return c;
	}

	@GetMapping(path = "getAllCandidateWithEvaluatedQuiz")
	public Iterable<CandidateDTO> getAllCandidateWithEvaluatedQuiz() {
		return candidateService.findAllByQuizEvaluated(true);

	}

	@PostMapping(path = "sendEmail")
	public String sendEmail(@RequestParam String subject, @RequestParam String text, @RequestParam String to)
			throws Exception {
		emailConfig.sendEmail(to, subject, text);

		return "";
	}
	
	@GetMapping("getAverageQuizResults")
	public String getAverageQuizResults() {
		Iterable<CandidateDTO> candidatesWithQuizEvaluated = candidateService.findAllByQuizEvaluated(true);
		double resultsSum = 0;
		double candidatesWithQuizEvaluatedNumber = 0;
		
		for (CandidateDTO candidateDTO : candidatesWithQuizEvaluated) {
			resultsSum += candidateDTO.getScore();
			candidatesWithQuizEvaluatedNumber++;
		}
		
		if(candidatesWithQuizEvaluatedNumber == 0) return "0";
		
		return (resultsSum/candidatesWithQuizEvaluatedNumber)+"";
	}
	
	@GetMapping("getAllConvokedCandidates")
	public Iterable<CandidateDTO> getAllConvokedCandidates() {
		return candidateService.findAllByConvoked(true);
	}
}
