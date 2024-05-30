package com.ptithcm.newsservice.dto.request;

public class NewsRequestDTO {
	private String title;
	private String image;
	private Integer type;
	private String link;
	private String summary;
	private String sort_title;
	private String content;
	private Long category_id;
	private Long subcategory_id;
	private Integer active;
	public NewsRequestDTO() {
		super();
	}
	public NewsRequestDTO(String title, String image, Integer type, String link, String summary, String sort_title,
			String content, Long category_id, Long subcategory_id, Integer active) {
		super();
		this.title = title;
		this.image = image;
		this.type = type;
		this.link = link;
		this.summary = summary;
		this.sort_title = sort_title;
		this.content = content;
		this.category_id = category_id;
		this.subcategory_id = subcategory_id;
		this.active = active;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	
	
}
