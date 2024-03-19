package com.aarjupatel.bookhive.entity;

import java.util.List;

import lombok.Data;

@Data
public class WrapperModel {

	private List<Category> allCategories;
	
	private List<SubCategory> allSubCategories;
	
	private List<Product> allProducts;
	
	private Category singleCategory;
	
	private SubCategory singleSubCategory;
	
	private Product singleProduct;
	
}