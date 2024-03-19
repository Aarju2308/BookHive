package com.aarjupatel.bookhive.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aarjupatel.bookhive.dao.CategoryDao;
import com.aarjupatel.bookhive.entity.Category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;


@Service
public class CategoryServiceImpl implements CategoryService {
	private CategoryDao catdao;
	
	private EntityManager entityManager;
	
	public CategoryServiceImpl(CategoryDao theCatDao, EntityManager theEntityManager) {
			catdao = theCatDao;
			entityManager = theEntityManager;
	}

	@Transactional
	@Override
	public Category save(Category theCategory) {
		
		Optional<Category> test;
		Category temp = null;
		
		if(theCategory.getCatId() == 0) {
			test = checkIfExists(theCategory.getName());
		}else {
			test = checkIfExistsInUpdate(theCategory.getName(), theCategory.getCatId());
		}
		
		if(test.isEmpty()) {
			temp = catdao.save(theCategory);
		}
		
		return temp;
		
		
	}

	@Override
	public Optional<Category> checkIfExists(String name) {
		return catdao.findByName(name);
	}

	@Override
	public Optional<Category> checkIfExistsInUpdate(String name, int id) {
		return catdao.findByNameAndCatIdNot(name, id);
	}

	@Override
	public List<Category> getAll() {
		return catdao.findAll();
	}

	@Override
	public Optional<Category> getSingleCat(int id) {
		return catdao.findById(id);
	}

	@Override
	@Transactional
	public void deleteCategory(int id) {
		catdao.deleteById(id);
		
	}
	
}
