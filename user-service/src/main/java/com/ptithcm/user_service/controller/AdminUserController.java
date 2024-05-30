package com.ptithcm.user_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptithcm.user_service.Service.AdminUserService;
import com.ptithcm.user_service.dto.UserDTO;

@RestController
@RequestMapping("/api/v1/admin/user")
public class AdminUserController {
	
	@Autowired
	private AdminUserService adminUserService;
	
	@GetMapping
	public ResponseEntity<?> getAllUser(){
		List<UserDTO> responseData = adminUserService.getAllUser();
		if (!responseData.isEmpty())
			return ResponseEntity.ok(responseData);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping("{userid}")
	public ResponseEntity<?> lockedUser(@PathVariable Long userid){
		if (adminUserService.lockedUser(userid)) {
			return ResponseEntity.ok("Locked success");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can not locked");
	}
	
	
	@DeleteMapping("{userid}")
	public ResponseEntity<?> deleteUser(@PathVariable Long userid){
		if (adminUserService.delteUser(userid)) {
			return ResponseEntity.ok("Delete success");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete fail");
	}
	
}
