package com.everis.evereval.manager.service;

import com.everis.evereval.manager.dto.QuizDTO;

public interface QuizService extends GenericService<QuizDTO, Long> {
	Iterable<QuizDTO> findAllByDoneAndEvaluated(boolean done,boolean evaluated);

	boolean quizContainsTextAnswers(QuizDTO quizdto);

}
