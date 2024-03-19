package com.aarjupatel.bookhive.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aarjupatel.bookhive.entity.Category;
import com.aarjupatel.bookhive.entity.SubCategory;
import com.aarjupatel.bookhive.entity.WrapperModel;
import com.aarjupatel.bookhive.service.CategoryService;
import com.aarjupatel.bookhive.service.SubCategoryService;

@Controller
@RequestMapping("/SubCategory")
public class SubCategoryController {
	private SubCategoryService subcatservice;
	
	private CategoryService catService;
	
	public SubCategoryController(SubCategoryService theSubCatServ, CategoryService theCatServ) {
		subcatservice = theSubCatServ;
		catService = theCatServ;
	}
	
	@GetMapping("/list")
	public String getAllSubCat(Model theModel) {
		
		List<SubCategory> allSubCats = subcatservice.allSubCat();
		
		theModel.addAttribute("subCategories",allSubCats);
		
		return "Admin/subCategoryList";
	}

	@GetMapping("/addForm")
	public String getAddForm(Model theModel) {
		
		// CREATING A WRAPPER MODEL TO SEND MULTIPLE MODELS 
		WrapperModel sendModel = new WrapperModel();
		
		List<Category> allCategories = catService.getAll();
		
		// SETTING BOTH THE MODELS 
		sendModel.setSingleSubCategory(new SubCategory());
		sendModel.setAllCategories(allCategories);
		
		theModel.addAttribute("WrapperModel", sendModel);
		
		return "Admin/subCategoryForm";
	}
	
	@PostMapping("/save")
	public String postSaveSubCategory(@ModelAttribute("WrapperModel") WrapperModel theWrapperModel, RedirectAttributes redirectAttr) {
		
		// GETTING THE CATEGORY ID FROM WRAPPER MODEL
		int catId = theWrapperModel.getSingleSubCategory().getCategory().getCatId();
		
		// GETTING THE CATEGORY FROM THE CATSERVICE
		Category theCategory = catService.getSingleCat(catId).get();
		
		// GETTING SUBCATEGORY TO SAVE FROM THE WRAPPER MODEL
		SubCategory theSubCategory = theWrapperModel.getSingleSubCategory();
		
		// SETTING THE CATEGORY TO THE SUB CATEGORY
		theSubCategory.setCategory(theCategory);
		
		// GETTING THE RESOUNSE FROM THE SUB-CAT-SERVICE
		SubCategory test = subcatservice.saveSubCategory(theSubCategory);
		
		if (test == null) {
			redirectAttr.addFlashAttribute("status","exist");
			return "redirect:/SubCategory/addForm";
		}
		else {
			redirectAttr.addFlashAttribute("status","success");
			return "redirect:/SubCategory/addForm";
		}
		
	}
	
	@GetMapping("/updateForm")
	public String getMethodName(@RequestParam int subCatId, Model theModel) {
		
		Optional<SubCategory> test = subcatservice.getById(subCatId);
		
		// CREATING A WRAPPER MODEL TO SEND MULTIPLE MODELS 
		WrapperModel sendModel = new WrapperModel();
		
		List<Category> allCategories = catService.getAll();
		
		// SETTING BOTH THE MODELS 
		sendModel.setSingleSubCategory(test.get());
		sendModel.setAllCategories(allCategories);
		
		theModel.addAttribute("WrapperModel", sendModel);
		
		return "Admin/subCategoryForm";
	}
	
	@GetMapping("/delete")
	public String getMethodName(@RequestParam int subCatId) {
		
		subcatservice.deleteSubCategory(subCatId);
		
		return "redirect:SubCategory/list";
	}
	
}
