package com.aarjupatel.bookhive.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aarjupatel.bookhive.entity.Category;


public interface CategoryDao extends JpaRepository<Category, Integer>{
	
	Optional<Category> findByNameAndCatIdNot(String name , int id);
	
	Optional<Category> findByName(String name);
	
}