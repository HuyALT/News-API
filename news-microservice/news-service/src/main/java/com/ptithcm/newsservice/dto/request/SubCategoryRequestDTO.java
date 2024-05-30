package com.ptithcm.newsservice.dto.request;

public class SubCategoryRequestDTO {
	private String name;
	private String sortName;
	private Long categoryid;
	public SubCategoryRequestDTO() {
		super();
	}
	public SubCategoryRequestDTO(String name, String sortName, Long categoryid) {
		super();
		this.name = name;
		this.sortName = sortName;
		this.categoryid = categoryid;
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
	public Long getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}
	
	
}
