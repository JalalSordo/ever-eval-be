package com.everis.evereval.web.controller;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everis.evereval.manager.dto.StaffDTO;
import com.everis.evereval.manager.service.StaffService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StaffController {
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private StaffService staffService;

	@GetMapping(path = "/findStaf", produces = "application/json")
	public StaffDTO findStaffByNameAndPassword(@RequestParam("name") String firstName,
			@RequestParam("password") String password) {
		return staffService.findByMailAndPassword(firstName, password);
	}

	@GetMapping(path = "/getStaff")
	public StaffDTO getStaff(@RequestParam Long id) {
		StaffDTO staffDTOFound = null;
		Optional<StaffDTO> optionalStaffDTO = staffService.findById(id);

		if (optionalStaffDTO.isPresent()) {
			staffDTOFound = optionalStaffDTO.get();
		}
		return staffDTOFound;
	}

	@GetMapping(path = "/getStaffByMail")
	public StaffDTO getStaffbymail(@RequestParam String mail) {
		return staffService.findByMail(mail);
	}

	@PostMapping(path = "/addStaff")
	public StaffDTO addStaff(@RequestBody StaffDTO sdto) {
		sdto.setQuizes(Arrays.asList());
		String password=sdto.getPassword();
		sdto.setPassword(bcryptEncoder.encode(password));
		return staffService.save(sdto);
	}

	@GetMapping(path = "/findAllStaff")
	public Iterable<StaffDTO> findAllStaff() {
		return staffService.findAll();

	}

	// delete staff by id
	@GetMapping(path = "/deleteStaffById")
	public String deleteById(@RequestParam Long id) {
		staffService.deleteById(id);
		return "Staff has been deleted";
	}

	@PostMapping(path = "/editStaff")
	public StaffDTO editStaff(@RequestBody StaffDTO dto) {

		StaffDTO editedStaffDTO = null;
		Optional<StaffDTO> optionalStaffDTO = staffService.findById(dto.getId());

		if (optionalStaffDTO.isPresent()) {
			StaffDTO staffDTOToEdit = optionalStaffDTO.get();
			staffDTOToEdit.setFirstName(dto.getFirstName());
			staffDTOToEdit.setLastName(dto.getLastName());
			staffDTOToEdit.setMail(dto.getMail());
			staffDTOToEdit.setRole(dto.getRole());
			staffDTOToEdit.setPassword(dto.getPassword());

			editedStaffDTO = staffService.save(staffDTOToEdit);
		}

		return editedStaffDTO;
	}

}
