package com.everis.evereval.manager.dto;

import com.everis.evereval.dao.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

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
