package com.green.shopingmall.controller;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.green.shopingmall.entity.Product;
import com.green.shopingmall.repository.AdminRepository;
import com.green.shopingmall.repository.ProductRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class Admin_Controller {
	
	@Autowired
	AdminRepository aRepository;
	
	@Autowired
	ProductRepository pRepository;
	
	@RequestMapping("/regProductForm")
	public String regProductForm(HttpSession session) {
		String role = (String) session.getAttribute("role");
		if(!role.equals("ROLE_ADMIN")) {
			return "/error403";
		}
		
		return "/admin/regProductForm";
	}
	
	@RequestMapping("/regProduct")
	public String regProduct(HttpServletRequest request, @RequestParam(value = "pimg_path", required = false) MultipartFile file) {
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		if(!role.equals("ROLE_ADMIN")) {
			return "/error403";
		}
		
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
			aRepository.save(product);
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
	
	@RequestMapping("editProductForm")
	public String editProductForm(@RequestParam("pno") Long pno, Model model) {
		
		Product prod = pRepository.findByPno(pno);
		model.addAttribute("product", prod);
		
		return "admin/editProductForm";
	}
	
	@RequestMapping("/editProduct")
	public String editProduct(HttpServletRequest request) {
		String pno_ = request.getParameter("pno");
		Long pno = Long.parseLong(pno_);
		Product prod = pRepository.findByPno(pno);
		String pname = request.getParameter("pname");
		Long price = Long.parseLong(request.getParameter("price"));
		String pintro = request.getParameter("pintro");
		String ptype = request.getParameter("ptype");
		Long pstock = Long.parseLong(request.getParameter("pstock"));
		boolean pstate = Boolean.parseBoolean(request.getParameter("pstate"));
		String manufacturingCompany = request.getParameter("manufacturingCompany");
		
		prod.setPname(pname);
		prod.setPrice(price);
		prod.setPintro(pintro);
		prod.setPtype(ptype);
		prod.setPstock(pstock);
		prod.setPstate(pstate);
		prod.setManufacturingCompany(manufacturingCompany);
		
		pRepository.save(prod);
		
		return "redirect:/productDetail?pno=" + pno;
	}
	
	
	
	
}
