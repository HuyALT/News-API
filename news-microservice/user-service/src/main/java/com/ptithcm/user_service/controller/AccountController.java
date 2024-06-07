package com.ptithcm.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptithcm.user_service.Exception.PasswordException;
import com.ptithcm.user_service.Service.UserService;
import com.ptithcm.user_service.dto.PasswordChange;
import com.ptithcm.user_service.dto.UserDTO;

@RestController
@RequestMapping("/api/v1/account/setting")
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/getinfo")
	public ResponseEntity<?> getInfomation(@RequestHeader("Authorization") String authorizationHeader){
		 if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            String token = authorizationHeader.substring(7);
	            UserDTO responseData = userService.getInfo(token);
	            if (responseData!=null)
	            	return ResponseEntity.status(HttpStatus.OK).body(responseData);
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data");
		 }
		 return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not authorization");
	}
	
	@PostMapping("/changePassword")
	public ResponseEntity<?> changePassword(
			@RequestHeader("Authorization") String authorizationHeader,
			@RequestBody PasswordChange request) throws PasswordException{
		 if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            String token = authorizationHeader.substring(7);
	            if (userService.changePassword(token, request.getOldPassword(), request.getNewPassword())) {
	            	return ResponseEntity.status(HttpStatus.OK).body("Success");
	            }
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data change");
	        }
		 return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not authorization");
	}
	
	@PostMapping("/changeInfo")
	public ResponseEntity<?> changeInfo(
			@RequestHeader("Authorization") String authorizationHeader,
			@RequestBody UserDTO request){
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            UserDTO responseData = userService.updateUserInfo(token, request);
            if (responseData!=null) {
            	return ResponseEntity.status(HttpStatus.OK).body(responseData);
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data change");
		}
		 return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not authorization");
	}
	
	@GetMapping("/canlogin")
	public ResponseEntity<?> getActive(@RequestParam String token){
            if (userService.isLockedOrActive(token)) {
            	return ResponseEntity.status(HttpStatus.OK).body("Success"); 
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not authorization");
	}
	
	@GetMapping("/another")
	public ResponseEntity<?> getUserInfo(@RequestParam Long userId){
		UserDTO responseData = userService.getuserInfoByid(userId);
		if (responseData!=null)
			return ResponseEntity.status(HttpStatus.OK).body(responseData);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail");
	}
	
	
	@ExceptionHandler(PasswordException.class)
	public ResponseEntity<?> handlePasswordException(PasswordException ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
}
