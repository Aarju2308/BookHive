package com.aarjupatel.bookhive.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.aarjupatel.bookhive.entity.Category;
import com.aarjupatel.bookhive.service.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/Category")
public class CategoryController {
	
	private CategoryService catservice;
	
	public CategoryController(CategoryService theCatServ) {
		catservice = theCatServ;
	}

	@GetMapping("/list")
	public String getAllCategories(Model theModel) {
		
		List<Category> allCategories = catservice.getAll();
		
		theModel.addAttribute("Categories", allCategories);
		return "Admin/categoryList";
	}
	
	@GetMapping("/addForm")
	public String getAddForm(Model theModel) {
		
		theModel.addAttribute("Category",new Category());
		
		return "Admin/CategoryForm";
	}
	
	@PostMapping("/save")
	public String postSaveOrUpdateCategory(@ModelAttribute("Category") Category theCategory, RedirectAttributes redirectAttr) {
		
		Category temp = catservice.save(theCategory);
		
		if (temp == null) {
			
			redirectAttr.addFlashAttribute("status","exist");
			
			return "redirect:/Category/addForm";
		}else {
			redirectAttr.addFlashAttribute("status","success");
			
			return "redirect:/Category/addForm";
		}
		
	}
	
	@GetMapping("/updateForm")
	public String getUpdateForm(@RequestParam int catId, Model theModel) {
		
		Optional<Category> theCategory = catservice.getSingleCat(catId);
		
		theModel.addAttribute("Category",theCategory);
		
		return "Admin/CategoryForm";
	}
	
	@GetMapping("/delete")
	public String getMethodName(@RequestParam int catId) {
		
		catservice.deleteCategory(catId);
		
		return "redirect:/Category/list";
	}
	
		
}
