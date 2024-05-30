package com.green.shopingmall.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.shopingmall.entity.Member;
import com.green.shopingmall.entity.OrderInfo;
import com.green.shopingmall.entity.Product;
import com.green.shopingmall.repository.MemberRepository;
import com.green.shopingmall.repository.OrderInfoRepository;
import com.green.shopingmall.repository.ProductRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyController {
	
	@Autowired
	MemberRepository memRepository;
	
	@Autowired
	ProductRepository pRepository;
	
	@Autowired
	OrderInfoRepository oRepository;
	
	@RequestMapping({"/", "/index"})
	public String index() {
		return "/index";
	}
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		
		return "/loginForm";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model) {
		
		String user_name = request.getParameter("user_name");
		String user_password = request.getParameter("user_password");
		Member member = memRepository.findByUserName(user_name);
		HttpSession session = request.getSession();
		boolean tf;
		
		if(member != null && member.getUserName().equals(user_name) && member.getUserPassword().equals(user_password)) {
			session.setAttribute("user_name", member.getUserName());
			session.setAttribute("role", member.getUserRole());
			session.setAttribute("member", member);
			tf = true;
		}else {
			tf = false;
			model.addAttribute("tf", tf);
			return "/loginForm";
		}
		
		model.addAttribute("tf", tf);
		
		return "/index";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "/index";
	}
	
	
	@RequestMapping("/regForm")
	public String regForm() {
		
		return "/regForm";
	}
	
	@RequestMapping("/regist")
	public String regist(HttpServletRequest request, Model model) {
		String user_name = request.getParameter("user_name");
		String user_password = request.getParameter("user_password");
		String name = request.getParameter("name");
		String user_birth_date_ = request.getParameter("user_birth_date");
		LocalDate user_birth_date = LocalDate.parse(user_birth_date_);
		String user_email = request.getParameter("user_email");
		String user_tel1 = request.getParameter("user_tel1");
		String user_tel2 = request.getParameter("user_tel2");
		String zipCode = request.getParameter("zip_code");
		String user_addr1 = request.getParameter("user_addr1");
		String detail_addr = request.getParameter("detail_addr");
		String user_addr2 = request.getParameter("user_addr2");
		String user_gender = request.getParameter("user_gender");
		LocalDate user_reg_date = LocalDate.now();
		String user_role = "ROLE_MEMBER"; // ROLE_ADMIN
		Member member = new Member(user_name, user_password, user_email, user_tel1, user_tel2, user_birth_date, name, zipCode, user_addr1, user_addr2, detail_addr, user_reg_date, user_role, user_gender);
		memRepository.save(member);
		
		return "redirect:/loginForm";
	}
	
	@RequestMapping("/productDetail")
	public String productDetail(@RequestParam("pno") Long pno, Model model) {
		
		Product product = pRepository.findByPno(pno);
		List<OrderInfo> order = oRepository.findByPno(product);
		int amount = 0;
		for(int i = 0; i < order.size(); i++) {
			amount = amount + order.get(i).getAmount();
		}
		model.addAttribute("product", product);
		model.addAttribute("amount", amount);
		
		return "/productDetail";
	}
	
	@RequestMapping("/productList")
	public String productList(Model model) {
		
		List<Product> list = pRepository.findAll();
		model.addAttribute("productList", list);
		
		return "/productList";
	}
	
	@RequestMapping("/searchResult")
	public String searchResult(@RequestParam("search") String search, Model model) {
		
		List<Product> prodList = pRepository.findByPnameLike(search);
		System.out.println(prodList);
		model.addAttribute("list", prodList);
		
		return "/searchResult";
	}
	
	
	
}
