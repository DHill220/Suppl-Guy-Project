package com.aca.supplyguy.model;

import java.time.LocalDateTime;

import com.aca.supplyguy.model.*;

public class Product {
	private Integer id; 
	private String title;
	private Category category;
	private LocalDateTime updateDateTime;
	private LocalDateTime createDateTime;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer product_id) {
		this.id = product_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}
	public int getNewProductId(Integer Id) {
		return id;
	}

}
