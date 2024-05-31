package com.green.shopingmall.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.shopingmall.domain.BasketJson;
import com.green.shopingmall.entity.Basket;
import com.green.shopingmall.entity.Member;
import com.green.shopingmall.entity.OrderInfo;
import com.green.shopingmall.entity.Product;
import com.green.shopingmall.repository.BasketRepository;
import com.green.shopingmall.repository.MemberRepository;
import com.green.shopingmall.repository.OrderInfoRepository;
import com.green.shopingmall.repository.ProductRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class Member_Controller {
	
	@Autowired
	MemberRepository mRepository;
	
	@Autowired
	ProductRepository pRepository;
	
	@Autowired
	BasketRepository bRepository;
	
	@Autowired
	OrderInfoRepository oRepository;
	
	@RequestMapping("/MyInfo")
	public String loginForm() {
		
		return "thymeleaf/MyInfo";
	}
	// 회원이 주문한 상품 목록 보기
	/*
	@RequestMapping("/myOrderList")
	public String myOrderList(HttpSession session) {
		String username = (String) session.getAttribute("user_name");
		List<Basket> list = basket.findByUsername(username);
		for(int i = 0; i < list.size(); i++) {
			Long pno = list.get(i).getPno().getPno();
		}
		return "/mem/myOrderList";
	}
	*/
	
	@RequestMapping("/regOrderForm")
	public String regOrder(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user_name") == null) {
			return "/error403";
		}
		
		String pno_ = request.getParameter("pno");
		Long pno = Long.parseLong(pno_);
		Product pro = pRepository.findByPno(pno);
		String amount_ = request.getParameter("amount");
		int amount = Integer.parseInt(amount_);
		
		model.addAttribute("product", pro);
		model.addAttribute("amount", amount);
		
		if(pro.getPstock() < amount) {
			return "productDetail?pno=" + pno;
		}
		
		return "member/regOrderForm";
	}
	
	@RequestMapping("/Order")
	public String Order(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		Member member = new Member();
		member = mRepository.findByUserName(username);
		String pno_ = request.getParameter("pno");
		Long pno = Long.parseLong(pno_);
		Product prod = new Product();
		prod = pRepository.findByPno(pno);
		String oaddr1 = request.getParameter("oaddr1");
		String oaddr2 = request.getParameter("oaddr2");
		String amount_ = request.getParameter("amount");
		int amount = Integer.parseInt(amount_);
		LocalDate orderDate = LocalDate.now();
		OrderInfo order = new OrderInfo(null, member, prod, amount, oaddr1, oaddr2, orderDate);
		oRepository.save(order);
		Basket basket = bRepository.findByUsernameAndPno(username, pno);
		if(basket != null) {
			bRepository.delete(basket);
			System.out.println(basket);
		}
		Product product = pRepository.findByPno(pno);
		Long newStock = product.getPstock() - Long.parseLong(amount_);
		System.out.println(newStock);
		product.setPstock(newStock);
		pRepository.save(product);
		return "redirect:/productList";
	}
	
	/*
	@RequestMapping("/myOrderList")
	public String myOrderList(HttpSession session, Model model) {
		
		if(session.getAttribute("user_name") == null) {
			return "/error403";
		}
		
		String user_name = (String) session.getAttribute("user_name");
		Member mem = new Member();
		mem = mRepository.findByUserName(user_name);
		List<OrderInfo> list = oRepository.findByUsername(mem);
		List<OrderDto> list2 = new ArrayList<>();
		for(int i = 0; i < list.size(); i++) {
			Long pno = list.get(i).getPno().getPno();
			System.out.println("pno : " + pno);
			Product prod = pRepository.findByPno(pno);
			System.out.println("===============");
			System.out.println(prod.getPname());
			System.out.println("===============");
			OrderDto orderDto = new OrderDto(prod.getPno(), prod.getPname(), prod.getPrice(), prod.getPtype(), list.get(i).getOrderDate(), prod.getManufacturingCompany(), list.get(i).getAmount());
			list2.add(orderDto);
		}
		System.out.println("===============");
		System.out.println(list2);
		System.out.println("===============");
		model.addAttribute("list2", list2);
		
		
		return "member/myOrderList";
	}
	*/
	
	@RequestMapping("/myOrderList")
	public String myOrderList(HttpSession session, Model model) {
		String user_name = (String) session.getAttribute("user_name");
		Member member = mRepository.findByUserName(user_name);
		
		List<OrderInfo> list = oRepository.findByUsername(member);
		System.out.println("-------------------------------------");
		System.out.println(list);
		model.addAttribute("list", list);
		
		
		return "member/myOrderList";
	}
	
	@RequestMapping("/basket")
	public @ResponseBody String basket(@RequestBody BasketJson bjson , HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String user_name = (String) session.getAttribute("user_name");
		Member member = mRepository.findByUserName(user_name);
		Product prod = pRepository.findByPno(bjson.getPno());
		Basket basket = bRepository.findByUsernameAndPno(user_name, bjson.getPno());
		if(basket != null) {
			Long num = basket.getAmount();
			basket.setAmount(bjson.getAmount() + num);
			basket.setBregdate(LocalDate.now());
			bRepository.save(basket);
		}else {
			Basket basket2 = new Basket();
			basket2.setUsername(member);
			basket2.setPno(prod);
			basket2.setAmount(bjson.getAmount());
			basket2.setBregdate(LocalDate.now());
			bRepository.save(basket2);
		}
		
		return "장바구니에 추가되었습니다.";
	}
	
	@RequestMapping("/myBasketList")
	public String myBasketList(HttpSession session, Model model) {
		String user_name = (String) session.getAttribute("user_name");
		Member member = mRepository.findByUserName(user_name);
		List<Basket> basketList = bRepository.findByUsername(member);
		model.addAttribute("basketList", basketList);
		
		return "/member/myBasketList";
	}
	
	@RequestMapping("/basketOrder")
	public String basketOrder(HttpServletRequest request) {
		
		
		
		return "member/myOrderList";
	}
	
	@RequestMapping("/deleteBasket")
	public @ResponseBody String delete(@RequestBody Long pno, HttpSession session) {
		/*
		String user_name = (String) session.getAttribute("user_name");
		Product prod = pRepository.findByPno(pno);
		Basket basket = bRepository.findByUsernameAndPno(user_name, pno);
		bRepository.delete(basket);
		*/
		Product prod = pRepository.findByPno(pno);
		String user_name = (String) session.getAttribute("user_name");
		Basket basket = bRepository.findByUsernameAndPno(user_name, pno);
		bRepository.delete(basket);
		
		return prod.getPname() + "이 장바구니에서 삭제 되었습니다.";
	}
	
	
	
	
}
