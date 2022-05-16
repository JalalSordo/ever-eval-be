package com.everis.evereval.dao.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.everis.evereval.dao.entity.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Staff {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String mail;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private String password;

	
	//private String picture;
	
//	private String token;


	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Quiz> quizes = new ArrayList<>();


	
	
	
	

}
