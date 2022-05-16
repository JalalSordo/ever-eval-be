package com.everis.evereval.dao.repository;

import org.springframework.data.repository.CrudRepository;

import com.everis.evereval.dao.entity.Email;


public interface EmailRepository extends CrudRepository<Email, Long>{
	Email findBySubject(String subject);

}
