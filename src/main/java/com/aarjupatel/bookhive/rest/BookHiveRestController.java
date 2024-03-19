package com.aarjupatel.bookhive.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aarjupatel.bookhive.entity.Category;
import com.aarjupatel.bookhive.entity.SubCategory;
import com.aarjupatel.bookhive.service.CategoryService;
import com.aarjupatel.bookhive.service.SubCategoryService;


@RestController
@RequestMapping("/api")
public class BookHiveRestController {
	
	private CategoryService catservice;
	
	private SubCategoryService subcatserv;
	
	@Autowired
	public BookHiveRestController(CategoryService theCatServ, SubCategoryService theSubCatServ) {
		catservice = theCatServ;
		subcatserv = theSubCatServ;
	}
	
	
	@GetMapping("/category")
	public List<Category> allCategory(){
		
		System.out.println(catservice.getAll());
		
		return catservice.getAll();
		
	}
	
	@GetMapping("/category/{catId}")
	public Optional<Category> singleCategory(@PathVariable int catId){
		return catservice.getSingleCat(catId);
	}
	
	@PostMapping("/category")
	public Category saveCategory(@RequestBody Category theCategory) {
		theCategory.setCatId(0);
		
		Category test = catservice.save(theCategory);
		
		return test;
	}

	@PutMapping("/category")
	public Category updateCategory(@RequestBody Category theCategory) {
		Category test = catservice.save(theCategory);
		
		return test;
	}
	
	@DeleteMapping("/category/{catId}")
	public void deleteCategory(@PathVariable int catId) {
		catservice.deleteCategory(catId);
		
	}
	
	@GetMapping("/subcategory")
	public List<SubCategory> getAllSubCategories() {
		System.out.println(subcatserv.allSubCat());
		return subcatserv.allSubCat();
	}
	
	
	@GetMapping("/subcategory/{subCatId}")
	public Optional<SubCategory> getSubCatById(@PathVariable int subCatId) {
		return subcatserv.getById(subCatId);
	}
	
	
	@GetMapping("/subcategorybycategory/{catId}")
	public List<SubCategory> getAllSubCatByCat(@PathVariable int catId){
		Category theCat = catservice.getSingleCat(catId).get();
		
		return subcatserv.allSubCatByCategory(theCat);
	}
	
	@PostMapping("/subcategory")
	public SubCategory saveSubCat(@RequestBody SubCategory theSubCategory) {
		
		theSubCategory.setSubCatId(0);
		
		int theCatId = theSubCategory.getCategory().getCatId();
		
		Optional<Category> theCat = catservice.getSingleCat(theCatId);
		
		if (theCat.isPresent()) {
			
			Category temp = theCat.get();
			theSubCategory.setCategory(temp);
			
			return subcatserv.saveSubCategory(theSubCategory);
		}
		
		return null;
		 
	}
	
	@PutMapping("/subcategory")
	public SubCategory putMethodName(@RequestBody SubCategory theSubCategory) {
		
		int theCatId = theSubCategory.getCategory().getCatId();
		
		Optional<Category> theCat = catservice.getSingleCat(theCatId);
		
		if (theCat.isPresent()) {
			
			Category temp = theCat.get();
			theSubCategory.setCategory(temp);
			
			return subcatserv.saveSubCategory(theSubCategory);
		}
		
		return null;
	}
	
	@DeleteMapping("/subcategory/{subCatId}")
	public void deleteSubCategory(@PathVariable int subCatId) {
		subcatserv.deleteSubCategory(subCatId);
	}
	
}
