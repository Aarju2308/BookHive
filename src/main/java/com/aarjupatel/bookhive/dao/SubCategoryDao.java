package com.aarjupatel.bookhive.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aarjupatel.bookhive.entity.Category;
import com.aarjupatel.bookhive.entity.SubCategory;

import java.util.List;
import java.util.Optional;



public interface SubCategoryDao extends JpaRepository<SubCategory, Integer> {
	Optional<SubCategory> findByName(String name);
	
	Optional<SubCategory> findByNameAndSubCatIdNot(String name, int id);
	
	List<SubCategory> findByCategory(Category category);
}
