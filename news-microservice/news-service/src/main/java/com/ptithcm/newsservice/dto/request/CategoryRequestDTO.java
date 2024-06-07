package com.ptithcm.newsservice.dto.request;

public class CategoryRequestDTO {
	private String name;
	private String sort_name;
	public CategoryRequestDTO() {
		super();
	}
	public CategoryRequestDTO(String name, String sort_name) {
		super();
		this.name = name;
		this.sort_name = sort_name;
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
	
	
}
