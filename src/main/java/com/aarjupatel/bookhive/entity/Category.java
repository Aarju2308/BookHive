package com.aarjupatel.bookhive.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "category_tbl")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int catId;
	
	private String name;
	
	private String description;	
	
	private int status;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<SubCategory> subCats;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Product> products;
	
	public Category() {
		
	}
	
	public Category(int catId, String name, String description, int status) {
		super();
		this.catId = catId;
		this.name = name;
		this.description = description;
		this.status = status;
	}
	
	
	

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	

	public List<SubCategory> getSubCats() {
		return subCats;
	}

	public void setSubCats(List<SubCategory> subCats) {
		this.subCats = subCats;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Category [catId=" + catId + ", name=" + name + ", description=" + description + ", status=" + status
				+ "]";
	}


}