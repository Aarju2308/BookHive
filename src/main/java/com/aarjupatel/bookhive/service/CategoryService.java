package com.aarjupatel.bookhive.service;

import java.util.List;
import java.util.Optional;

import com.aarjupatel.bookhive.entity.Category;

public interface CategoryService {
	
	Category save(Category theCategory);
	
	List<Category> getAll();
	
	Optional<Category> getSingleCat(int id);
	
	void deleteCategory(int id);
	
	Optional<Category> checkIfExists(String name);
	
	Optional<Category> checkIfExistsInUpdate(String name, int id);
	
}
