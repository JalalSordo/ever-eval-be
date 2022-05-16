package com.everis.evereval.manager.service;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.everis.evereval.dao.entity.Staff;
import com.everis.evereval.dao.repository.StaffRepository;


@Service
public class MyUserDetailsService implements UserDetailsService {

	 @Autowired
	 private StaffRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		Staff user = userRepository.findByMail(mail);
        if (user == null) {
            throw new UsernameNotFoundException(mail);
        }
        final List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+user.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(user.getMail(), user.getPassword(),
        		authorities);
    
	}

}
