package com.aarjupatel.bookhive.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aarjupatel.bookhive.dao.SubCategoryDao;
import com.aarjupatel.bookhive.entity.Category;
import com.aarjupatel.bookhive.entity.SubCategory;

import jakarta.transaction.Transactional;
@Service
public class SubCategoryServiceImpl implements SubCategoryService {

	private SubCategoryDao subcatdao;
	
	public SubCategoryServiceImpl(SubCategoryDao theSubCatDao) {
		subcatdao = theSubCatDao;
	}
	
	
	@Transactional
	@Override
	public SubCategory saveSubCategory(SubCategory theSubCat) {
		Optional<SubCategory> test;
		SubCategory temp = null;
		
		if(theSubCat.getSubCatId() == 0) {
			test = checkIfExists(theSubCat);
		}else {
			test = checkIfExistsInUpdate(theSubCat);
		}
		
		if(test.isEmpty()) {
			temp = subcatdao.save(theSubCat);
		}
		
		return temp;
		
	}

	@Override
	public Optional<SubCategory> checkIfExists(SubCategory theSubCat) {
		return subcatdao.findByName(theSubCat.getName());
	}


	@Override
	public Optional<SubCategory> checkIfExistsInUpdate(SubCategory theSubCat) {
		return subcatdao.findByNameAndSubCatIdNot(theSubCat.getName(), theSubCat.getSubCatId());
	}


	@Override
	public List<SubCategory> allSubCat() {
		return subcatdao.findAll();
	}


	@Override
	public Optional<SubCategory> getById(int id) {
		return subcatdao.findById(id);
	}


	@Override
	@Transactional
	public void deleteSubCategory(int id) {
		subcatdao.deleteById(id);
		
	}


	@Override
	public List<SubCategory> allSubCatByCategory(Category theCat) {
		return subcatdao.findByCategory(theCat);
	}

}
