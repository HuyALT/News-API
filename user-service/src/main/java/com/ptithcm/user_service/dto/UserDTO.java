package com.ptithcm.user_service.dto;

import java.time.LocalDateTime;

public class UserDTO {
	private Long id;
	private String username;
	private String image;
	private String email;
	private int active;
	private int locked;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;
	public UserDTO() {
		super();
	}
	public UserDTO(Long id,String username, String image, String email, int active, int locked, LocalDateTime createAt,
			LocalDateTime updateAt) {
		super();
		this.id = id;
		this.username = username;
		this.image = image;
		this.email = email;
		this.active = active;
		this.locked = locked;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public int getLocked() {
		return locked;
	}
	public void setLocked(int locked) {
		this.locked = locked;
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
	
}
