package com.ptithcm.newsservice.dto.response;


public class CategoryResponseDTO {
	private Long id;
	private String name;
	private String sort_name;
	public CategoryResponseDTO() {
		super();
	}
	
	public CategoryResponseDTO(Long id, String name, String sort_name) {
		super();
		this.id = id;
		this.name = name;
		this.sort_name = sort_name;
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
}
