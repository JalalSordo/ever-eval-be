package com.everis.evereval.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everis.evereval.config.JwtTokenUtil;
import com.everis.evereval.dao.entity.JwtResponse;
import com.everis.evereval.dao.entity.Staff;
import com.everis.evereval.dao.repository.StaffRepository;
import com.everis.evereval.manager.dto.StaffDTO;
import com.everis.evereval.manager.service.MyUserDetailsService;
import com.everis.evereval.manager.service.StaffService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HomeController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private StaffService stfService;
	
	@Autowired
	private StaffRepository userRepository;
	
	

	@GetMapping(path = "/findStaff", produces = "application/json")
	public ResponseEntity<?> validateLogin(@RequestParam String mail, @RequestParam String password) throws Exception{
		StaffDTO sdto = new StaffDTO();

		
        authenticate(mail, password);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(mail);

		final String token = jwtTokenUtil.generateToken(userDetails);

         Staff user = userRepository.findByMail(mail);
		//StaffDTO user = stfService.findByMail(mail);
        return ResponseEntity.ok(new JwtResponse(user.getId(),user.getFirstName(),user.getLastName(),token,user.getRole().toString(),user.getMail()));
		
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
	
		}
	}
	}
