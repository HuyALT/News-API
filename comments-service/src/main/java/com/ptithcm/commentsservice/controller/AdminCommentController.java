package com.ptithcm.commentsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptithcm.commentsservice.Service.CommentService;

@RestController
@RequestMapping("/api/v1/admin/comments")
public class AdminCommentController {
	
	@Autowired
	private CommentService commentService;
	
	@DeleteMapping("")
	public ResponseEntity<?> deleteAllcommentUser(@RequestParam Long userId){
		if (commentService.deleteCommentByUserID(userId)) {
			return ResponseEntity.ok("Delete Successs");
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data change");
	}
}
