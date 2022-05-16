package com.everis.evereval.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.everis.evereval.dao.entity.Staff;
import com.everis.evereval.dao.entity.enums.Role;

public interface StaffRepository extends JpaRepository<Staff, Long> {

	Staff findByMailAndPassword(String mail, String password);

	Staff findByMail(String mail);

	List<Staff> findByRole(Role role);

}
