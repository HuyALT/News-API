package com.ptithcm.commentsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptithcm.commentsservice.Service.CommentService;
import com.ptithcm.commentsservice.dto.request.CommentRequestDTO;
import com.ptithcm.commentsservice.dto.request.CommentRequestUpdate;
import com.ptithcm.commentsservice.dto.response.CommentResponseDTO;

@RestController
@RequestMapping("/api/v1/user/comments")
public class UserCommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("")
	public ResponseEntity<?> addComment(
			@RequestHeader("Authorization") String authorizationHeader,
			@RequestBody CommentRequestDTO request){
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            return ResponseEntity.status(HttpStatus.CREATED).body(commentService.addComment(request, token));
        }
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not authorization");
	}
	
	@DeleteMapping("")
	public ResponseEntity<?> deleteComment(
			@RequestHeader("Authorization") String authorizationHeader,
			@RequestParam Long commentid){
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            if (commentService.deleteComment(commentid, token)) {
            	return ResponseEntity.status(HttpStatus.OK).body("Delete Success");
            }
            else {
            	return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Can not delete");
            }
        }
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not authorization");
	}
	
	@GetMapping("")
	public ResponseEntity<?> getUserCommentwithNewsId(
			@RequestHeader("Authorization") String authorizationHeader,
			@RequestParam Long newsid){
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentByUserIdAndNewId(token, newsid));
        }
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not authorization");
	}
	
	@PutMapping("")
	public ResponseEntity<?> updateComment(
			@RequestHeader("Authorization") String authorizationHeader,
			@RequestBody(required = true) CommentRequestUpdate request) {
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            CommentResponseDTO responseData = commentService.updateComment(request, token);
            if (responseData!=null)
            	return ResponseEntity.status(HttpStatus.OK).body(responseData);
            else
            	return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data change");
        }
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not authorization");
	}
	
	 @ExceptionHandler(IllegalArgumentException.class)
	 public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	 }
}
