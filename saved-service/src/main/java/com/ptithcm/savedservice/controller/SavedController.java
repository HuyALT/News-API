package com.ptithcm.savedservice.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptithcm.savedservice.Service.SavedService;
import com.ptithcm.savedservice.dto.response.SavedResponeDTO;

@RestController
@RequestMapping("/api/v1/user/saved")
public class SavedController {
	
	@Autowired
	private SavedService savedService;
	
	@GetMapping("")
	public ResponseEntity<?> getSavedByUserid(@RequestHeader("Authorization") String authorizationHeader) {
		 if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            String token = authorizationHeader.substring(7);
	            List<SavedResponeDTO> reponseData = savedService.findByUserId(token);
	            if (!reponseData.isEmpty())
	            	return ResponseEntity.status(HttpStatus.OK).body(reponseData);
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	        }
		 return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not authorization");
	}
	
	 @ExceptionHandler(IllegalArgumentException.class)
	 public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	 }
	 
	 @ExceptionHandler(RuntimeException.class)
	 public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	 }
	 
	 @PostMapping("")
	 public ResponseEntity<?> savedNews(@RequestHeader("Authorization") String authorizationHeader,@RequestParam Long newsid) {
		  if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            String token = authorizationHeader.substring(7);
	            return ResponseEntity.status(HttpStatus.CREATED).body(savedService.savedNews(newsid, token));
	        }
		  return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not authorization");
	 }
	 
	 @DeleteMapping("")
	 public ResponseEntity<?> deleteNews(@RequestHeader("Authorization") String authorizationHeader,@RequestParam Long id){
		 if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            String token = authorizationHeader.substring(7);
	            if (savedService.deleteSaved(id, token)) {
	            	return ResponseEntity.status(HttpStatus.OK).body("Delete Success");
	            }
	            else {
	            	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cant delete");
	            }
	        }
		 return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not authorization");
	 }
}
