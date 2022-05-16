package com.everis.evereval.manager.service.impl;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.everis.evereval.dao.entity.Candidate;
import com.everis.evereval.dao.entity.Quiz;
import com.everis.evereval.dao.entity.Staff;
import com.everis.evereval.dao.entity.enums.Role;
import com.everis.evereval.dao.repository.CandidateRepository;
import com.everis.evereval.dao.repository.QuizRepository;
import com.everis.evereval.dao.repository.StaffRepository;
import com.everis.evereval.mailsender.MailConfig;

@Component
public class BatchService {

	@Autowired
	private QuizRepository quizService;

	@Autowired
	private StaffRepository staffRepo;

	@Autowired
	private CandidateRepository candidateService;

	@Autowired
	private MailConfig mailConfig;

	//@Scheduled(fixedRate = 50000)
	
	// this method Fires at 10:15 AM every day
	@Scheduled(cron = "0 15 10 * * ? ")
	public void sendEvaluationMailToEvaluators() throws MessagingException {

		Iterable<Quiz> quize = quizService.findAllByDoneTrueAndEvaluatedFalse();

		List<Staff> evaluators = staffRepo.findByRole(Role.EVALUATOR);

		Candidate candidate;

		String subject = "Everis Quiz Evaluation";
		
		String content = "Hi, \n \n some quizzes in EverEval are done and they  must be evaluated.";

		System.out.println("//////////////////");
		int cpt  = 0;
		for (Quiz quiz : quize) {

			candidate = candidateService.findByQuiz(quiz);

			String emailBody = "\n\n The quiz description:\n Candidate: "
					+ candidate.getName() + "\n Technologie: " + quiz.getTechno() + "\n Level: " + quiz.getLevel()
					+ "\n Number of questions: " + quiz.getTotalQuestion();

			content += emailBody;
			cpt++;
		}
		
		content += "\n\n Kind regards,\n EverEval.";
		if(cpt>0)
		{
			for (Staff staff : evaluators) {

				mailConfig.sendEmail(staff.getMail(), subject, content);
				System.out.println("email sent Seccesfully");
			}
		}
		
		
		System.out.println("//////////////////");

	}

}
