package com.ptithcm.identity_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptithcm.identity_service.Service.OtpService;


@RestController
@RequestMapping("/api/v1/auth/otp")
public class OtpController {
	
	@Autowired
	private OtpService otpService;
	
	
	@PostMapping("/send-otp")
	public ResponseEntity<?> sendOtp(@RequestParam String email){
		if (otpService.sendOtp(email))
			return ResponseEntity.ok("Email is send");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email invalid");
	}
	
	@PostMapping("/verify-otp")
	public ResponseEntity<?> verifyOtp(@RequestParam String email, @RequestParam String otp){
		boolean isValid = otpService.verifyOtp(email, otp);
        if (isValid) {
            return ResponseEntity.ok("Verify");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not valid");
        }
	}
}
