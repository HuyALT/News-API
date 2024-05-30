package com.ptithcm.identity_service.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ptithcm.identity_service.entity.UserEntity;
import com.ptithcm.identity_service.repository.UserRepository;

public class CustomUsersDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> user = userRepository.findByUsername(username);
		return user.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("User name invalid"));
	}

}
