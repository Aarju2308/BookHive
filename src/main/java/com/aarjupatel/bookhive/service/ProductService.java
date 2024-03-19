package com.aarjupatel.bookhive.service;

import java.util.List;
import java.util.Optional;

import com.aarjupatel.bookhive.entity.Product;

public interface ProductService {
	List<Product> getAllProducts();
	
	Product save(Product theProduct);
	
	Optional<Product> getById(int id);
	
	void deleteProduct(int id);
	
	Optional<Product> checkIfExists(String title);

	Optional<Product> checkIfExistsInUpdate(String title, int id);
}
