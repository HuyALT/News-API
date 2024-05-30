package com.ptithcm.commentsservice.dto.request;

public class CommentRequestDTO {
	private Long newsId;
	private String content;
	public CommentRequestDTO() {
		super();
	}
	public CommentRequestDTO(Long newsId, String content) {
		super();
		this.newsId = newsId;
		this.content = content;
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
	
	
}
