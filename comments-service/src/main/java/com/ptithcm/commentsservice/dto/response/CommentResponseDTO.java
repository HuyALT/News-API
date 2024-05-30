package com.ptithcm.commentsservice.dto.response;

import java.time.LocalDateTime;

public class CommentResponseDTO {
	private Long id;
	private Long newsId;
	private Long userId;
	private String content;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;
	public CommentResponseDTO() {
		super();
	}
	public CommentResponseDTO(Long id, Long newsId, Long userId, String content, LocalDateTime createAt,
			LocalDateTime updateAt) {
		super();
		this.id = id;
		this.newsId = newsId;
		this.userId = userId;
		this.content = content;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNewsId() {
		return newsId;
	}
	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	public LocalDateTime getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
