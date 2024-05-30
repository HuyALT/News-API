package com.ptithcm.commentsservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptithcm.commentsservice.Service.CommentService;
import com.ptithcm.commentsservice.dto.response.CommentResponseDTO;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("")
	public ResponseEntity<?> getCommentByNewsId(@RequestParam Long newsid) {
		List<CommentResponseDTO> response = commentService.getCommentNewsId(newsid);
		if (!response.isEmpty())
			return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentNewsId(newsid));
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	 @ExceptionHandler(IllegalArgumentException.class)
	 public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	 }
}
