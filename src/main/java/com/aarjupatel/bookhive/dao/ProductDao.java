package com.aarjupatel.bookhive.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aarjupatel.bookhive.entity.Product;
import java.util.Optional;


public interface ProductDao extends JpaRepository<Product, Integer> {
	Optional<Product> findByTitle(String title);
	
	Optional<Product> findByTitleAndProductIdNot(String title,int id);
}
