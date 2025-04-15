package com.api.hospitalmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.hospitalmanagement.repository.AuthRepository;




@Service
public class UserService implements UserDetailsService {

	@Autowired
	private AuthRepository authRepository;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return authRepository.findByUsername(username);
	}

	
	
	
}
