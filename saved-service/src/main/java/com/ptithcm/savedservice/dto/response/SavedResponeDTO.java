package com.ptithcm.savedservice.dto.response;

import java.time.LocalDateTime;

public class SavedResponeDTO {
	private Long id;
	private Long newsid;
	private LocalDateTime createAt;
	public SavedResponeDTO() {
		super();
	}
	public SavedResponeDTO(Long id, Long newsid, LocalDateTime createAt) {
		super();
		this.id = id;
		this.newsid = newsid;
		this.createAt = createAt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNewsid() {
		return newsid;
	}
	public void setNewsid(Long newsid) {
		this.newsid = newsid;
	}
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	
	
}
