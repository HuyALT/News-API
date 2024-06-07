package com.ptithcm.newsservice.dto.request;

public class SubCategoryRequestDTO {
	private String name;
	private String sort_name;
	private Long category_id;
	public SubCategoryRequestDTO() {
		super();
	}
	public SubCategoryRequestDTO(String name, String sort_name, Long category_id) {
		super();
		this.name = name;
		this.sort_name = sort_name;
		this.category_id = category_id;
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
