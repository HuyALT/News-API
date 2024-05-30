package com.ptithcm.newsservice.dto.response;

public class SubCategoryResponeDTO {
	private Long id;
	private String name;
	private String sort_name;
	private Long category_id;
	public SubCategoryResponeDTO() {
		super();
	}
	public SubCategoryResponeDTO(Long id, String name, String sort_name, Long category_id) {
		super();
		this.id = id;
		this.name = name;
		this.sort_name = sort_name;
		this.category_id = category_id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSort_name() {
		return sort_name;
	}
	public void setSort_name(String sort_name) {
		this.sort_name = sort_name;
	}
	public Long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}
	
	
}
