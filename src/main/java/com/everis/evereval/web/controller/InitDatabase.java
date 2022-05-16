package com.everis.evereval.web.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.evereval.dao.entity.Staff;
import com.everis.evereval.dao.entity.enums.Level;
import com.everis.evereval.dao.entity.enums.Role;
import com.everis.evereval.dao.entity.enums.Techno;
import com.everis.evereval.dao.entity.enums.Type;
import com.everis.evereval.dao.repository.StaffRepository;
import com.everis.evereval.manager.dto.AnswerDTO;
import com.everis.evereval.manager.dto.CandidateDTO;
import com.everis.evereval.manager.dto.EmailDTO;
import com.everis.evereval.manager.dto.ProposedResponseDTO;
import com.everis.evereval.manager.dto.QuestionDTO;
import com.everis.evereval.manager.dto.QuizDTO;
import com.everis.evereval.manager.dto.QuizQuestionDTO;
import com.everis.evereval.manager.dto.StaffDTO;
import com.everis.evereval.manager.service.CandidateService;
import com.everis.evereval.manager.service.EmailService;
import com.everis.evereval.manager.service.QuestionService;
import com.everis.evereval.manager.service.StaffService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class InitDatabase {
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private CandidateService candidateService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private StaffService staffRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private StaffRepository staffRepo;

	@GetMapping(path = "initdb")
	public String initDB() {

		Staff st = new Staff();
		st.setFirstName("admin");
		st.setLastName("admin");
		st.setMail("chaimaa.elhaddad@everis.com");
		st.setPassword(bcryptEncoder.encode("admin"));
		st.setRole(Role.ADMIN);
		// st.setPicture("https://yinnepal.files.wordpress.com/2017/11/admin.png?w=640");
		staffRepo.save(st);

		// Email
		EmailDTO emaildto = new EmailDTO();
		emaildto.setSubject("Convocation to everis");
		emaildto.setMessageText("Convocation to everis");
		emailService.save(emaildto);
		EmailDTO emaildto1 = new EmailDTO();
		emaildto1.setSubject("Everis");
		emaildto1.setMessageText("Everis");
		emailService.save(emaildto1);
		EmailDTO emaildto2 = new EmailDTO();
		emaildto2.setSubject("Convocation to TEST");
		emaildto2.setMessageText("Convocation to TEST");
		emailService.save(emaildto2);

		// ---Test staff

		StaffDTO staff = new StaffDTO();
		staff.setFirstName("Chaimaa");
		staff.setLastName("EL HADDAD");
		staff.setMail("chaimaahaddad7@gmail.com");
		staff.setRole(Role.HR);

		staff.setPassword(bcryptEncoder.encode("chaimaa"));
		// staff.setPicture("https://cdn.dribbble.com/users/541780/screenshots/2515468/tiendas-hr-dribbble.gif");

		// staff.setPassword("chaimaa");
		// staff.setPicture("https://cdn.dribbble.com/users/541780/screenshots/2515468/tiendas-hr-dribbble.gif");

		staff.setQuizes(Arrays.asList());

		StaffDTO staff1 = new StaffDTO();
		staff1.setFirstName("ikram");
		staff1.setLastName("elk");
		staff1.setMail("evereval.everis@gmail.com");
		staff1.setRole(Role.EVALUATOR);
		staff1.setPassword(bcryptEncoder.encode("evereval"));
		// staff1.setPicture(
		// "http://www.agoraentreprise.com/wp-content/uploads/2014/07/evaluation-dentreprise-300x219-180x120.jpg");

		// staff1.setPassword("evereval");
		// staff1.setPicture("http://www.agoraentreprise.com/wp-content/uploads/2014/07/evaluation-dentreprise-300x219-180x120.jpg");

		staff1.setQuizes(Arrays.asList());

		staffRepository.save(staff);
		staffRepository.save(staff1);

		// ---Test ProposedResponse
		ProposedResponseDTO pr = new ProposedResponseDTO();
		pr.setContent("hello");
		pr.setState(true);
		pr.setScore(5);

		ProposedResponseDTO pr2 = new ProposedResponseDTO();
		pr2.setContent("hi");
		pr2.setState(false);

		ProposedResponseDTO pr3 = new ProposedResponseDTO();
		pr3.setContent("say hi");
		pr3.setState(true);
		pr3.setScore(15);

		ProposedResponseDTO pr4 = new ProposedResponseDTO();
		pr4.setContent("am from somewhere");
		pr4.setState(false);

		// ---Test Question

		QuestionDTO q = new QuestionDTO();
		q.setContent("who are you?");
		q.setLevel(Level.CLD);
		q.setTechno(Techno.JAVAEE);
		q.setType(Type.CHECKBOX);
		q.getProposedResponses().add(pr);
		q.getProposedResponses().add(pr2);
		q.getProposedResponses().add(pr3);
		q.setCountdown(10);
		q.setScore(20);

		QuestionDTO q2 = new QuestionDTO();
		q2.setContent("where are you from?");
		q2.setLevel(Level.CLD);
		q2.setTechno(Techno.JAVAEE);
		q2.setType(Type.RADIO);
		q2.getProposedResponses().add(pr3);
		q2.getProposedResponses().add(pr4);
		q2.setCountdown(5);
		q2.setScore(15);
		questionService.save(q);
		QuestionDTO q3 = new QuestionDTO();
		q3.setContent("question1");
		q3.setLevel(Level.CLD);
		q3.setTechno(Techno.JAVAEE);
		q3.setType(Type.RADIO);
		q3.getProposedResponses().add(pr3);
		q3.getProposedResponses().add(pr4);
		q3.setCountdown(15);
		q3.setScore(15);
		questionService.save(q3);
		QuestionDTO q4 = new QuestionDTO();
		q4.setContent("question2");
		q4.setLevel(Level.CLD);
		q4.setTechno(Techno.JAVAEE);
		q4.setType(Type.TEXTAREA);
		q4.setCountdown(20);
		q4.setScore(20);
		questionService.save(q4);
		QuestionDTO q5 = new QuestionDTO();
		q5.setContent("question3");
		q5.setLevel(Level.CLD);
		q5.setTechno(Techno.JAVAEE);
		q5.setType(Type.TEXTFIELD);
		q5.setCountdown(10);
		q5.setScore(20);
		questionService.save(q5);
		QuestionDTO q6 = new QuestionDTO();
		q6.setContent("question4");
		q6.setLevel(Level.CLD);
		q6.setTechno(Techno.JAVAEE);
		q6.setType(Type.RADIO);
		q6.getProposedResponses().add(pr3);
		q6.getProposedResponses().add(pr4);
		q6.setCountdown(10);
		q6.setScore(15);
		questionService.save(q6);
		QuestionDTO q7 = new QuestionDTO();
		q7.setContent("question5?");
		q7.setLevel(Level.CLD);
		q7.setTechno(Techno.JAVAEE);
		q7.setType(Type.TEXTAREA);
		q7.setCountdown(8);
		q7.setScore(25);
		questionService.save(q7);
		QuestionDTO q8 = new QuestionDTO();
		q8.setContent("question6");
		q8.setLevel(Level.CLD);
		q8.setTechno(Techno.JAVAEE);
		q8.setType(Type.RADIO);
		q8.getProposedResponses().add(pr3);
		q8.getProposedResponses().add(pr4);
		q8.setCountdown(5);
		q8.setScore(15);
		questionService.save(q8);
		QuestionDTO q9 = new QuestionDTO();
		q9.setContent("question7");
		q9.setLevel(Level.CLD);
		q9.setTechno(Techno.JAVAEE);
		q9.setType(Type.RADIO);
		q9.getProposedResponses().add(pr3);
		q9.getProposedResponses().add(pr4);
		q9.setCountdown(10);
		q9.setScore(15);
		questionService.save(q9);
		QuestionDTO q10 = new QuestionDTO();
		q10.setContent("question8");
		q10.setLevel(Level.CLD);
		q10.setTechno(Techno.JAVAEE);
		q10.setType(Type.RADIO);
		q10.getProposedResponses().add(pr3);
		q10.getProposedResponses().add(pr4);
		q10.setCountdown(20);
		q10.setScore(15);
		questionService.save(q10);
		QuestionDTO q11 = new QuestionDTO();
		q11.setContent("question9");
		q11.setLevel(Level.CLD);
		q11.setTechno(Techno.JAVAEE);
		q11.setType(Type.RADIO);
		q11.getProposedResponses().add(pr3);
		q11.getProposedResponses().add(pr4);
		q11.setCountdown(5);
		q11.setScore(15);
		questionService.save(q11);
		questionService.save(q2);

		// ---Test Answer

		AnswerDTO answer1 = new AnswerDTO();
		AnswerDTO answer2 = new AnswerDTO();
		AnswerDTO answer3 = new AnswerDTO();
		AnswerDTO answer4 = new AnswerDTO();
		AnswerDTO answer5 = new AnswerDTO();
		answer1.setContent(questionService.findById(1l).get().getProposedResponses().get(1).getContent());
		answer2.setContent(questionService.findById(1l).get().getProposedResponses().get(2).getContent());
		answer3.setContent(questionService.findById(2l).get().getProposedResponses().get(1).getContent());
		answer4.setContent("Answer for question with TEXTFIELD");
		answer5.setContent("Answer for question with TEXTarea");

		// ---Test QuizQuestion

		QuizQuestionDTO qq = new QuizQuestionDTO();
		qq.setContent("who are you?");
		qq.setLevel(Level.CLD);
		qq.setTechno(Techno.JAVAEE);
		qq.setType(Type.CHECKBOX);
		qq.getProposedResponses().add(pr);
		qq.getProposedResponses().add(pr2);
		qq.getProposedResponses().add(pr3);
		qq.setCountdown(10);
		qq.setScore(20);
		qq.getAnswers().add(answer1);
		qq.getAnswers().add(answer2);
		QuizQuestionDTO qq2 = new QuizQuestionDTO();
		qq2.setContent("where are you from?");
		qq2.setLevel(Level.CLD);
		qq2.setTechno(Techno.JAVAEE);
		qq2.setType(Type.RADIO);
		qq2.getProposedResponses().add(pr3);
		qq2.getProposedResponses().add(pr4);
		qq2.setCountdown(5);
		qq2.setScore(15);
		qq2.getAnswers().add(answer3);
		QuizQuestionDTO qq3 = new QuizQuestionDTO();
		qq3.setContent("question1");
		qq3.setLevel(Level.CLD);
		qq3.setTechno(Techno.JAVAEE);
		qq3.setType(Type.RADIO);
		qq3.getProposedResponses().add(pr3);
		qq3.getProposedResponses().add(pr4);
		qq3.setCountdown(15);
		qq3.setScore(15);
		qq3.getAnswers().add(answer5);
		QuizQuestionDTO qq4 = new QuizQuestionDTO();
		qq4.setContent("question2");
		qq4.setLevel(Level.CLD);
		qq4.setTechno(Techno.JAVAEE);
		qq4.setType(Type.TEXTAREA);
		qq4.setCountdown(20);
		qq4.setScore(20);
		qq4.getAnswers().add(answer4);

		// ---Test Answers
		QuizDTO quiz = new QuizDTO();
		quiz.setLevel(Level.CLD);
		quiz.setTechno(Techno.JAVAEE);
		quiz.getQuizQuestions().add(qq);
		quiz.getQuizQuestions().add(qq2);
		quiz.getQuizQuestions().add(qq3);
		quiz.getQuizQuestions().add(qq4);
		quiz.setDone(true);
		quiz.setEvaluated(false);
		quiz.setTotalQuestion(4);

		// ---Test Candidate
		CandidateDTO cdto = new CandidateDTO();
		cdto.setName("imane");
		cdto.setMail("imane.elkouch19@gmail.com");
		cdto.setLevel(Level.CLD);
		cdto.setTechno(Techno.JAVAEE);
		cdto.setQuiz(quiz);
		cdto.setConvoked(false);
		candidateService.save(cdto);

		return "database is initialized successfully";

	}

}
