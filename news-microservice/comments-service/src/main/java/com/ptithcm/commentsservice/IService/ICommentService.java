package com.ptithcm.commentsservice.IService;

import java.util.List;

import com.ptithcm.commentsservice.dto.request.CommentRequestDTO;
import com.ptithcm.commentsservice.dto.request.CommentRequestUpdate;
import com.ptithcm.commentsservice.dto.response.CommentResponseDTO;

public interface ICommentService {
	CommentResponseDTO addComment(CommentRequestDTO request, String token);
	List<CommentResponseDTO> getCommentNewsId(Long newid);
	List<CommentResponseDTO> getCommentByUserIdAndNewId(String token, Long newid);
	boolean deleteComment(Long commentid, String token);
	CommentResponseDTO updateComment(CommentRequestUpdate request, String token);
	boolean deleteCommentByUserID(Long userId);
	
}
