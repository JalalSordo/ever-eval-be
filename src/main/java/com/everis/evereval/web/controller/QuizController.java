package com.everis.evereval.web.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everis.evereval.mailsender.MailConfig;
import com.everis.evereval.manager.dto.CandidateDTO;
import com.everis.evereval.manager.dto.QuizDTO;
import com.everis.evereval.manager.service.CandidateService;
import com.everis.evereval.manager.service.QuizService;
import com.everis.evereval.manager.service.StaffService;
import com.everis.evereval.manager.service.impl.CandidateServiceImp;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class QuizController {

	@Autowired
	private QuizService quizService;

	@Autowired
	private MailConfig emailConfig;

	@Autowired
	private CandidateServiceImp candidateServiceImp;

	@Autowired
	private CandidateService candidateService;

	@Autowired
	private StaffService staffService;

	// Add Quiz
	@PostMapping(path = "/addQuiz")
	public QuizDTO addQuiz(@RequestBody QuizDTO qzdto) {
		return quizService.save(qzdto);

	}

	@GetMapping(path = "getAllQuizes")
	public Iterable<QuizDTO> getAllQuizes() {
		return quizService.findAll();

	}

	// get quiz by done and evluated
	@GetMapping(path = "getQuizByDoneAndEval")
	public Iterable<QuizDTO> getQuizBy(@RequestParam boolean done, @RequestParam boolean evaluated) {
		return quizService.findAllByDoneAndEvaluated(done, evaluated);
	}

	// Insert the answers
	@PostMapping(path = "postQuizAnswers")
	public String postQuizAnswers(@RequestBody QuizDTO qdto) throws MessagingException {
		QuizDTO quizdto = quizService.findById(qdto.getId()).get();
		quizdto = qdto;
		if (quizdto.isDone() == false) {

			quizdto.setDone(true);
			// TODO: Update the score...
			double score = this.candidateServiceImp.updateScore(quizdto);

			quizService.save(quizdto);

			System.out.println("quiz controller display score: " + score);

//			this.candidateServiceImp.updateScore(quizdto);
			CandidateDTO candidate = candidateService.findByQuiz(qdto);

			candidate.setScore(score);
			

			if (quizService.quizContainsTextAnswers(quizdto)) {

				staffService.sendEvaluationMailToEvaluator(quizdto,candidate.getName());

			} else {
				candidate.getQuiz().setEvaluated(true); 
				staffService.sendScoreMail(quizdto,candidate);

			}
			
			candidateService.save(candidate);

			// send email to candidate after finishing the quiz
			String subject = "Quiz passed";
			String content = "Dear " + candidate.getName()
					+ ",\n\n Thank you for your time the Quiz is done and your answers has been submited successfully \n\n"
					+ " Recruitment team.";

			emailConfig.sendEmail(candidate.getMail(), subject, content);
			return "Success Quiz done and answers has been submited! and the score is : ";
		}
		return "Success Quiz done and answers has been submited! and the score is : ";

	}

	@PostMapping(path = "postEvaluatorReview")
	public String postEvaluatorReview(@RequestBody QuizDTO qdto) throws MessagingException {

		System.out.println("You are inside the postEvaluatorReview");

		QuizDTO quizdto = quizService.findById(qdto.getId()).get();
		quizdto = qdto;
		// TODO: Update the score...
		double score = this.candidateServiceImp.updateScore(quizdto);

		quizService.save(quizdto);

		System.out.println("postEvaluatorReview   score: " + score);

//		this.candidateServiceImp.updateScore(quizdto);

		CandidateDTO candidate = candidateService.findByQuiz(qdto);
		candidate.setScore(score);
		candidate.getQuiz().setEvaluated(true); 
		candidateService.save(candidate);
		staffService.sendScoreMail(quizdto,candidate);

		return "Success Quiz done and answers has been submited! and the score is : ";
	}

}