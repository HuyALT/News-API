package com.ptithcm.identity_service.Service;

import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ptithcm.identity_service.dto.request.UserRequest;
import com.ptithcm.identity_service.dto.response.UserResponse;
import com.ptithcm.identity_service.entity.UserEntity;
import com.ptithcm.identity_service.repository.RoleRepository;
import com.ptithcm.identity_service.repository.UserRepository;
import com.ptithcm.identity_service.token.UserToken;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	

	public UserResponse registerUser(UserRequest request) {
		try {
			UserEntity entityPreSave = requestToEntity(request);
			Optional<UserEntity> entityCheck = userRepository.findByEmail(request.getEmail());
			if (entityCheck.isPresent()&&entityCheck.get().getActicve()==0) {
				entityPreSave.setId(entityCheck.get().getId());
				
			}
			UserEntity entitySave = userRepository.save(entityPreSave);
			return new UserResponse(entitySave.getId(), entitySave.getUsername(), entitySave.getEmail());
		} catch (DataIntegrityViolationException | ConstraintViolationException ex) {
			return null;
		}
	}
	
	public String generateToken(UserRequest request) {
		UserToken token = new UserToken();
		Optional<UserEntity> entity = userRepository.findByUsername(request.getUsername());
		
		token.setUsername(request.getUsername());
		token.setRole(entity.get().getRole().getName());
		token.setId(entity.get().getId());
		
		
		return jwtService.generateToken(token);
	}
	
	public void validateToken(String token) {
		jwtService.validateToken(token);
	}
	
	
	public boolean authenticationCheck(UserRequest request) {
		Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		if (authentication.isAuthenticated())
			return true;
		return false;
	}
	
	
	
	private UserEntity requestToEntity(UserRequest request) {
		UserEntity entity = new UserEntity();
		entity.setActicve(0);
		entity.setLocked(1);
		entity.setEmail(request.getEmail());
		entity.setPassword(passwordEncoder.encode(request.getPassword()));
		entity.setRole(roleRepository.findById(2L).get());
		entity.setUsername(request.getUsername());
		entity.setImage("https://ik.imagekit.io/dx1lgwjws/News/default-avatar.jpg?updatedAt=1716483707937");
		return entity;
		
	}
}
