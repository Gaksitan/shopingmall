package com.green.shopingmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.shopingmall.entity.Product;
import com.green.shopingmall.repository.AdminRepository;

@Controller
@RequestMapping("/admin")
public class Admin_Controller {
	
	@Autowired
	AdminRepository admin;
	
	@RequestMapping("/regProductForm")
	public String regProductForm() {
		return "/admin/regProductForm";
	}
	
	@RequestMapping("/regProduct")
	public String regProduct() {
		
		return "redirect:/admin/productList";
	}
	
	@RequestMapping("/productList")
	public String productList(Model model) {
		
		List<Product> list = admin.findAll();
		model.addAttribute("list", list);
		
		return "productList";
	}
	
	
}
