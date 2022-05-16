package com.everis.evereval.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProposedResponseDTO {

	private Long id;

	private String content;

	private boolean state;
	
	private double score;
}
