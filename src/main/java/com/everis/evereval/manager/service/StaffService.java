package com.everis.evereval.manager.service;

import javax.mail.MessagingException;

import com.everis.evereval.manager.dto.CandidateDTO;
import com.everis.evereval.manager.dto.QuizDTO;
import com.everis.evereval.manager.dto.StaffDTO;

public interface StaffService extends GenericService<StaffDTO, Long> {

	StaffDTO findByMailAndPassword(String mail, String password);

	StaffDTO findByMail(String mail);

	void sendEvaluationMailToEvaluator(QuizDTO quizDTO, String candidateName) throws MessagingException;

	void sendScoreMail(QuizDTO quizDTO, CandidateDTO candidate) throws MessagingException;

}
