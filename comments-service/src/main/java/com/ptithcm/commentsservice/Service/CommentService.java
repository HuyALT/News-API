package com.ptithcm.commentsservice.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ptithcm.commentsservice.IService.ICommentService;
import com.ptithcm.commentsservice.dto.request.CommentRequestDTO;
import com.ptithcm.commentsservice.dto.request.CommentRequestUpdate;
import com.ptithcm.commentsservice.dto.response.CommentResponseDTO;
import com.ptithcm.commentsservice.entity.CommentsEntity;
import com.ptithcm.commentsservice.repository.CommentRepository;
import com.ptithcm.commentsservice.util.JwtUtil;

@Service
public class CommentService implements ICommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JwtUtil jwtUtil;
	
    private final String newsServiceUrl = "http://News-news-service/api/v1/news";
    
    

	@Override
	public CommentResponseDTO addComment(CommentRequestDTO request, String token) {
		if (!isNewsExist(request.getNewsId())) {
            throw new IllegalArgumentException("News ID does not exist");
        }
		
		CommentsEntity entity = requestToEntity(request, token);
		
		return entityToRespone(commentRepository.save(entity));
	}

	@Override
	public List<CommentResponseDTO> getCommentNewsId(Long newid) {
		if (!isNewsExist(newid)) {
            throw new IllegalArgumentException("News ID does not exist");
        }
		return commentRepository.findByNewsId(newid)
				.stream()
				.map(this::entityToRespone)
				.collect(Collectors.toList());
	}

	
	@Override
	public List<CommentResponseDTO> getCommentByUserIdAndNewId(String token, Long newid){
		return commentRepository.findByUserIdAndNewsId(jwtUtil.extractUserId(token), newid)
				.stream()
				.map(this::entityToRespone)
				.collect(Collectors.toList());
	}

	@Override
	public boolean deleteComment(Long commentid, String token){
		Optional<CommentsEntity> commentEntity = commentRepository.findById(commentid);
		if (commentEntity.isPresent()) {
			if (commentEntity.get().getUserId()==jwtUtil.extractUserId(token)) {
				commentRepository.delete(commentEntity.get());
				return true;
			}
			else {
				return false;
			}
		}
		
		return false;
	}

	@Override
	public CommentResponseDTO updateComment(CommentRequestUpdate request, String token){
		if (request.getCommentsid()==null||request.getContent()==null) return null;
		Optional<CommentsEntity> commentEntity = commentRepository.findById(request.getCommentsid());
		if (commentEntity.isPresent()) {
			if (commentEntity.get().getUserId()==jwtUtil.extractUserId(token)) {
				commentEntity.get().setContent(request.getContent());
				return entityToRespone(commentRepository.save(commentEntity.get()));
			}
			else {
				return null;
			}
		}
		return null;
	}
	
	

	@Override
	public boolean deleteCommentByUserID(Long userId) {
		List<CommentsEntity> commentsList = commentRepository.findByUserId(userId);
		if (!commentsList.isEmpty()) {
			commentsList.forEach(comment->commentRepository.delete(comment));
			return true; 
		}
		
		return false;
	}

	private CommentResponseDTO entityToRespone(CommentsEntity entity) {
		CommentResponseDTO dto = new CommentResponseDTO();
		dto.setContent(entity.getContent());
		dto.setNewsId(entity.getNewsId());
		dto.setContent(entity.getContent());
		dto.setCreateAt(entity.getCreatedAt());
		dto.setUpdateAt(entity.getUpdatedAt());
		dto.setId(entity.getId());
		dto.setUserId(entity.getUserId());
		return dto;
	}
	
	private CommentsEntity requestToEntity(CommentRequestDTO request, String token) {
		CommentsEntity entity = new CommentsEntity();
		entity.setContent(request.getContent());
		entity.setNewsId(request.getNewsId());
		entity.setUserId(jwtUtil.extractUserId(token));
		return entity;
	}
	
	private boolean isNewsExist(Long newsId) {
        try {
            String url = newsServiceUrl + "/" + newsId;
            ResponseEntity<?> response = restTemplate.getForEntity(url, Object.class);
            HttpStatusCode statusCode = response.getStatusCode();
            if (statusCode==HttpStatus.OK) {
            	return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
	
}
