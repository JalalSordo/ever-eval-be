package com.everis.evereval.manager.dto;

import java.util.ArrayList;
import java.util.List;

import com.everis.evereval.dao.entity.enums.Level;
import com.everis.evereval.dao.entity.enums.Techno;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class QuizDTO {
	private Long id;
	private Level level;
	private Techno techno;

	private List<QuizQuestionDTO> quizQuestions = new ArrayList<>();
	private boolean done;
	private boolean evaluated;
	private int totalQuestion;
}
