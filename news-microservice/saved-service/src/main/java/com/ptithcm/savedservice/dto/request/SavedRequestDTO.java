package com.ptithcm.savedservice.dto.request;

public class SavedRequestDTO {
	private Long newsId;
	public SavedRequestDTO(Long newsId) {
		super();
		this.newsId = newsId;
	}
	public SavedRequestDTO() {
		super();
	}
	public Long getNewsId() {
		return newsId;
	}
	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}
	
	
}
