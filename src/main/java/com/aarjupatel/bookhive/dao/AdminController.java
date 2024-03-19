package com.aarjupatel.bookhive.dao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {
	@GetMapping("/")
	public String getAdminDashboard() {
		return "Admin/dashboard";
	}
	
}
