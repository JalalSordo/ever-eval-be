package com.everis.evereval.dao.entity;


import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class JwtResponse implements Serializable {
		
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final String role;
	private final Long id;
	//private final String picture;
	private final String firstName;
	private final String lastName;
	private final String mail;

	public JwtResponse(Long id,String firstName,String lastName,String jwttoken, String role,String mail) {
		this.id=id;	
		this.firstName=firstName;
		this.lastName=lastName;
		this.jwttoken = jwttoken;
		this.role=role;
		//this.picture=picture;
		this.mail=mail;
	}

	
		
}
