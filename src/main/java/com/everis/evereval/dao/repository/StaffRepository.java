package com.everis.evereval.dao.repository;

import com.everis.evereval.dao.entity.Staff;
import com.everis.evereval.dao.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {

	Staff findByMailAndPassword(String mail, String password);

	Staff findByMail(String mail);

	List<Staff> findByRole(Role role);

}
