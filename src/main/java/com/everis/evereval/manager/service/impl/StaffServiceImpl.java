package com.everis.evereval.manager.service.impl;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.evereval.dao.entity.Staff;
import com.everis.evereval.dao.entity.enums.Role;
import com.everis.evereval.dao.repository.StaffRepository;
import com.everis.evereval.mailsender.MailConfig;
import com.everis.evereval.manager.dto.CandidateDTO;
import com.everis.evereval.manager.dto.QuizDTO;
import com.everis.evereval.manager.dto.StaffDTO;
import com.everis.evereval.manager.service.StaffService;
import com.everis.evereval.manager.transformer.StaffTransformer;
import com.everis.evereval.manager.transformer.Transformer;

@Service
public class StaffServiceImpl extends GenericServiceImpl<Staff, StaffDTO, Long> implements StaffService {

	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private MailConfig mailConfig;

	private static Transformer<Staff, StaffDTO> t = new StaffTransformer();

	public StaffServiceImpl() {
		super(t);
	}

	@Override
	public StaffDTO findByMailAndPassword(String mail, String password) {
		return t.toDTO(staffRepository.findByMailAndPassword(mail, password));
	}

	@Override
	public StaffDTO findByMail(String mail) {
		return t.toDTO(staffRepository.findByMail(mail));
	}

	@Override
	public void sendEvaluationMailToEvaluator(QuizDTO quizDTO, String candidateName) throws MessagingException {

		List<Staff> evaluators = staffRepository.findByRole(Role.EVALUATOR);

		String subject = "Everis Quiz Evaluation";

		String content = "Hi, \n\nA quiz in EverEval is done and it must be evaluated.\n\n The quiz description:\n Candidate: "
				+ candidateName + "\n Technologie: " + quizDTO.getTechno() + "\n Level: " + quizDTO.getLevel()
				+ "\n Number of questions: " + quizDTO.getTotalQuestion() + "\n\n Kind regards,\n EverEval.";

		for (Staff staff : evaluators) {

			mailConfig.sendEmail(staff.getMail(), subject, content);
		}

	}

	@Override
	public void sendScoreMail(QuizDTO quizDTO, CandidateDTO candidate) throws MessagingException {

		Iterable<Staff> staffes = staffRepository.findAll();

		String subject = "Everis Quiz Result";

		String content = "Hi, \n\nA quiz in EverEval is evaluated and its score is " + candidate.getScore()
				+ ".\n\n The quiz description:\n Candidate: " + candidate.getName() + "\n Technologie: "
				+ quizDTO.getTechno() + "\n Level: " + quizDTO.getLevel() + "\n Number of questions: "
				+ quizDTO.getTotalQuestion() + "\n\n Kind regards,\n EverEval.";

		for (Staff staff : staffes) {

			mailConfig.sendEmail(staff.getMail(), subject, content);
		}

	}

}
