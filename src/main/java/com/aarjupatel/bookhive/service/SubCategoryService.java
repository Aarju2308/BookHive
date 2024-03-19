package com.aarjupatel.bookhive.service;

import java.util.List;
import java.util.Optional;

import com.aarjupatel.bookhive.entity.Category;
import com.aarjupatel.bookhive.entity.SubCategory;

public interface SubCategoryService {
	
	List<SubCategory> allSubCat();
	
	List<SubCategory> allSubCatByCategory(Category theCat);
	
	Optional<SubCategory> getById(int id);
	
	void deleteSubCategory(int id);
	
	SubCategory saveSubCategory(SubCategory theSubCat);
	
	Optional<SubCategory> checkIfExists(SubCategory theSubCat);

	Optional<SubCategory> checkIfExistsInUpdate(SubCategory theSubCat);

}
