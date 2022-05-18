package com.everis.evereval.dao.repository;

import com.everis.evereval.dao.entity.Email;
import org.springframework.data.repository.CrudRepository;


public interface EmailRepository extends CrudRepository<Email, Long> {
    Email findBySubject(String subject);

}
