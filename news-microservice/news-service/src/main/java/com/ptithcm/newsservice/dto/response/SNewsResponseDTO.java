package com.ptithcm.newsservice.dto.response;

import java.time.LocalDateTime;

public class SNewsResponseDTO {
	private Long id;
	private String title;
	private String image;
	private int type;
	private String link;
	private String summary;
	private String sort_title;
	private Long userid;
	private Long category_id;
	private Long subcategory_id;
	private int active;
	private LocalDateTime create_at;
	private LocalDateTime update_at;
	public SNewsResponseDTO(Long id, String title, String image, int type, String link, String summary,
			String sort_title, Long userid, Long category_id, Long subcategory_id, int active, LocalDateTime create_at,
			LocalDateTime update_at) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.type = type;
		this.link = link;
		this.summary = summary;
		this.sort_title = sort_title;
		this.userid = userid;
		this.category_id = category_id;
		this.subcategory_id = subcategory_id;
		this.active = active;
		this.create_at = create_at;
		this.update_at = update_at;
	}
	public SNewsResponseDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getSort_title() {
		return sort_title;
	}
	public void setSort_title(String sort_title) {
		this.sort_title = sort_title;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}
	public Long getSubcategory_id() {
		return subcategory_id;
	}
	public void setSubcategory_id(Long subcategory_id) {
		this.subcategory_id = subcategory_id;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public LocalDateTime getCreate_at() {
		return create_at;
	}
	public void setCreate_at(LocalDateTime create_at) {
		this.create_at = create_at;
	}
	public LocalDateTime getUpdate_at() {
		return update_at;
	}
	public void setUpdate_at(LocalDateTime update_at) {
		this.update_at = update_at;
	}
	
}
