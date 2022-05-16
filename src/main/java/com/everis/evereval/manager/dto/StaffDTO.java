package com.everis.evereval.manager.dto;

import java.util.List;

import com.everis.evereval.dao.entity.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class StaffDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String mail;
	private Role role;
	private String password;

	List<QuizDTO> quizes;


	
}
