package com.everis.evereval.manager.service;

import javax.mail.MessagingException;

import com.everis.evereval.manager.dto.CandidateDTO;
import com.everis.evereval.manager.dto.QuizDTO;

public interface CandidateService extends GenericService<CandidateDTO, Long> {
	CandidateDTO findByMail(String mail);

	CandidateDTO findByToken(String Token);

	CandidateDTO findByQuiz(QuizDTO quiz);

	CandidateDTO findByQuizId(Long id);

	Iterable<CandidateDTO> findAllByQuizEvaluated(boolean evaluated);

	Iterable<CandidateDTO> findAllByQuizNull();

	Iterable<CandidateDTO> findAllByConvoked(boolean convoked);

	void generateTokenForCandidate(Long candidateId);

	void sendConvocationToCandidate(Long candidateId) throws MessagingException;

}
