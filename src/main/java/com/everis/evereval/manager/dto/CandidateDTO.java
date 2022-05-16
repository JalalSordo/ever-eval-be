package com.everis.evereval.manager.dto;

import com.everis.evereval.dao.entity.enums.Level;
import com.everis.evereval.dao.entity.enums.Techno;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDTO {

	private Long id;
	private String name;
	private String mail;
	private Level level;
	private Techno techno;
	private QuizDTO quiz;
	private double score;
	private boolean convoked;

}
