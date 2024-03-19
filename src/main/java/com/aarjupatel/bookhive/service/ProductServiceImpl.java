package com.aarjupatel.bookhive.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aarjupatel.bookhive.dao.ProductDao;
import com.aarjupatel.bookhive.entity.Product;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService{
	
	private ProductDao productdao;
	
	public ProductServiceImpl(ProductDao theProductDao) {
		productdao = theProductDao;
	}
	

	@Override
	public List<Product> getAllProducts() {
		return productdao.findAll();
	}

	@Transactional
	@Override
	public Product save(Product theProduct) {
		
		Optional<Product> test;
		
		if (theProduct.getProductId() == 0) {
			test = checkIfExists(theProduct.getTitle());
		}else {
			test = checkIfExistsInUpdate(theProduct.getTitle(),theProduct.getProductId());
		}
		
		
		if (test.isEmpty()) {
			return productdao.save(theProduct);
		}
		
		return null;
		
	}


	@Override
	public Optional<Product> getById(int id) {
		return productdao.findById(id);
	}


	@Override
	@Transactional
	public void deleteProduct(int id) {
		productdao.deleteById(id);
	}


	@Override
	public Optional<Product> checkIfExists(String title) {
		return productdao.findByTitle(title);
	}


	@Override
	public Optional<Product> checkIfExistsInUpdate(String title, int id) {
		return productdao.findByTitleAndProductIdNot(title, id);
	}

	
	
	
}
