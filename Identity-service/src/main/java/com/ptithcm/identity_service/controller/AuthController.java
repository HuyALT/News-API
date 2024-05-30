package com.ptithcm.identity_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptithcm.identity_service.Service.AuthService;
import com.ptithcm.identity_service.dto.request.UserRequest;
import com.ptithcm.identity_service.dto.response.UserResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	

	@PostMapping("/register")
	public ResponseEntity<?> addNewUser(@RequestBody UserRequest request){
		UserResponse responeData = authService.registerUser(request);
		if (responeData!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(responeData);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is exist");
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> getToken(@RequestBody UserRequest request){
		if (authService.authenticationCheck(request)) {
			return ResponseEntity.status(HttpStatus.OK).body(authService.generateToken(request));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Access");
	}
	
	@GetMapping("/validate")
	public ResponseEntity<?> validateToken(@RequestParam(required = true) String token ){
		try {
			authService.validateToken(token);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Access");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Invalid");
	}
	
	@PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        request.getSession().invalidate();
        response.setStatus(HttpServletResponse.SC_OK);
    }
	
}
