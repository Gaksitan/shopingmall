package com.green.shopingmall.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.green.shopingmall.entity.Product;
import com.green.shopingmall.repository.AdminRepository;

import jakarta.servlet.http.HttpServletRequest;

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
	public String regProduct(HttpServletRequest request, @RequestParam(value = "pimg_path", required = false) MultipartFile file) {
		try {
			String pname = request.getParameter("pname");
			String price_ = request.getParameter("price");
			Long price = Long.parseLong(price_);
			String pintro = request.getParameter("pintro");
			byte[] pimgPath = null;
            if (file != null && !file.isEmpty()) {
                pimgPath = file.getBytes();
            }
			String ptype = request.getParameter("ptype");
			LocalDate pregDate = LocalDate.now();
			Long porder = 0L;
			Long pstock = Long.parseLong(request.getParameter("pstock"));
			boolean pstate;
			if((pstock - porder) > 0L) {
				pstate = true;
			}else {
				pstate = false;
			}
			String manufacturingCompany = request.getParameter("manufacturingCompany");
			Product product = new Product(null, pname, price, pintro, pimgPath, ptype, pregDate, porder, pstock, pstate, manufacturingCompany);
			admin.save(product);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/admin/productList";
	}
	
	/*
	@RequestMapping("/productList")
	public String productList(Model model) {
		
		List<Product> list = admin.findAll();
		model.addAttribute("productList", list);
		
		return "/admin/productList";
	}
	*/
	
	
	
	
}
