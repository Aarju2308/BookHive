package com.aarjupatel.bookhive.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aarjupatel.bookhive.entity.Category;
import com.aarjupatel.bookhive.entity.Product;
import com.aarjupatel.bookhive.entity.SubCategory;
import com.aarjupatel.bookhive.entity.WrapperModel;
import com.aarjupatel.bookhive.service.CategoryService;
import com.aarjupatel.bookhive.service.FileUploadUtil;
import com.aarjupatel.bookhive.service.ProductService;
import com.aarjupatel.bookhive.service.SubCategoryService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/Product")
public class ProductController {
	
	private CategoryService catService;
	
	private SubCategoryService subCatServ;
	
	private ProductService productServ;
	
	
	public ProductController(CategoryService theCatServ, SubCategoryService theSubCatSer, ProductService theProductServ) {
		catService = theCatServ;
		subCatServ = theSubCatSer;
		productServ = theProductServ;
	}
	
	@GetMapping("/list")
	public String getProductList(Model theModel) {
		
		List<Product> allProducts = productServ.getAllProducts();
		
		theModel.addAttribute("Products",allProducts);
		
		return "Admin/productList";
	}
	
	@GetMapping("/addForm")
	public String getAddForm(Model theModel) {
		
		WrapperModel wrapperModel = new WrapperModel();
		
		List<Category> allCategories = catService.getAll();
		List<SubCategory> allSubCatUnderCat = subCatServ.allSubCatByCategory(catService.getSingleCat(1).get());
		
		wrapperModel.setAllCategories(allCategories);
		wrapperModel.setAllSubCategories(allSubCatUnderCat);
		wrapperModel.setSingleProduct(new Product());
		
		theModel.addAttribute("Wrapper",wrapperModel);
		
		return "Admin/productForm";
	}
	
	@PostMapping("/save")
	public String postSaveProduct(
			@ModelAttribute WrapperModel theWrapper,
			@RequestParam("coverImage") MultipartFile multipateFile, 
			RedirectAttributes redirectAttr) {
	
		if (!multipateFile.isEmpty()) {
			
			// GETTING CURRENT DATE DIME AND FORMATTING IT 
			DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss");
			
			// RENAMING THE FILE NAME WITH THE CURRENT FORMATTED DATE TIME
			String fileName = multipateFile.getOriginalFilename().replaceAll("^(.*?)\\.", LocalDateTime.now().format(formater) + ".");
			
			// SAVING THE FILE IN THE FOLDER
			FileUploadUtil.saveFile(fileName, multipateFile);
			
			// SAVING THE FILE NAME IN THE ENTITY
			theWrapper.getSingleProduct().setCoverImage(fileName);

			// GETTING CATEGORY FROM THE ID
			Category theCategory = catService.getSingleCat(theWrapper.getSingleProduct().getCategory().getCatId()).get();
			
			// GETTING SUB CATEGORY FROM ID FROM THE FORM
			SubCategory theSubCat = subCatServ.getById(theWrapper.getSingleProduct().getSubCategory().getSubCatId()).get();
			
			//SAVING THE CATEGORY IN PRODUCT ENTITY
			theWrapper.getSingleProduct().setCategory(theCategory);
			
			//SAVING THE SUB-CATEGORY IN PRODUCT ENTITY
			theWrapper.getSingleProduct().setSubCategory(theSubCat);
			
			// SAVING THE ENTITY IN THE DATABASE 
			
			Product temp = productServ.save(theWrapper.getSingleProduct());
			
			if(temp == null) {
				redirectAttr.addFlashAttribute("status","exist");
			}else {
				redirectAttr.addFlashAttribute("status","success");
			}
			
		}else {
			redirectAttr.addFlashAttribute("status","failed");
		}
		
		return "redirect:/Product/addForm";
	}
	
	@GetMapping("/updateForm")
	public String getUpdateForm(@RequestParam int productId, Model theModel) {
		
		WrapperModel wrapperModel = new WrapperModel();
		
		Optional<Product> singlePro = productServ.getById(productId);
		
		List<Category> allCategories = catService.getAll();
		List<SubCategory> allSubCatUnderCat = subCatServ.allSubCatByCategory(catService.getSingleCat(singlePro.get().getCategory().getCatId()).get());
		
		
		wrapperModel.setAllCategories(allCategories);
		wrapperModel.setAllSubCategories(allSubCatUnderCat);
		wrapperModel.setSingleProduct(singlePro.get());
		
		theModel.addAttribute("Wrapper",wrapperModel);
		
		
		return "Admin/productForm";
	}
	
}
