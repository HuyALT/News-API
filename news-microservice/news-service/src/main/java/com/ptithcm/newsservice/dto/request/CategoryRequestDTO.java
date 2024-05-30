package com.ptithcm.newsservice.dto.request;

public class CategoryRequestDTO {
	private String name;
	private String sortName;
	public CategoryRequestDTO() {
		super();
	}
	public CategoryRequestDTO(String name, String sortName) {
		super();
		this.name = name;
		this.sortName = sortName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	
	
}
