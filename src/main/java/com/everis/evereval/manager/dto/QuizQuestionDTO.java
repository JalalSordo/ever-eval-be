package com.everis.evereval.manager.dto;

import java.util.ArrayList;
import java.util.List;

import com.everis.evereval.dao.entity.enums.Level;
import com.everis.evereval.dao.entity.enums.Techno;
import com.everis.evereval.dao.entity.enums.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class QuizQuestionDTO {

	private Long id;
	
	private String content;
	private Level level;
	private Techno techno;
	private Type type;
	private int countdown;
	private double score;

	private List<ProposedResponseDTO> proposedResponses = new ArrayList<>();
	
	private List<AnswerDTO> answers = new ArrayList<>();

}
