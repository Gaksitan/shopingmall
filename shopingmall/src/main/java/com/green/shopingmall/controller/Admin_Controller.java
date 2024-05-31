package com.green.shopingmall.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
	public String regProduct(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file, Model model) throws IOException {
		HttpSession session = request.getSession();
		String user_name = (String) session.getAttribute("user_name");
		String role = (String) session.getAttribute("role");
		if(!role.equals("ROLE_ADMIN")) {
			return "/error403";
		}
		
		String pname = request.getParameter("pname");
		String price_ = request.getParameter("price");
		Long price = Long.parseLong(price_);
		String pintro = request.getParameter("pintro");
		
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
		Product product = new Product(null, pname, price, pintro, null, ptype, pregDate, porder, pstock, pstate, manufacturingCompany, user_name);
		
		String fileRealName = file.getOriginalFilename(); // 파일명을 얻어낼 수 있는 메서드
		Long size = file.getSize();
		if(!fileRealName.isEmpty()) {
			
			System.out.println("파일명 : "  + fileRealName);
			System.out.println("용량크기(byte) : " + size);
			//서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
			String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
			String uploadFolder = "C:\\dev\\shopingmall\\shopingmall\\src\\main\\resources\\static\\upload-image";
			
			/*
			  파일 업로드시 파일명이 동일한 파일이 이미 존재할 수도 있고 사용자가 
			  업로드 하는 파일명이 언어 이외의 언어로 되어있을 수 있습니다. 
			  타인어를 지원하지 않는 환경에서는 정상 동작이 되지 않습니다.(리눅스가 대표적인 예시)
			  고유한 랜덤 문자를 통해 db와 서버에 저장할 파일명을 새롭게 만들어 준다.
			 */
			
			
			UUID uuid = UUID.randomUUID();
			System.out.println(uuid.toString());
			String[] uuids = uuid.toString().split("-");
			
			String uniqueName = uuids[0];
			System.out.println("생성된 고유문자열 : " + uniqueName);
			System.out.println("확장자명 : " + fileExtension);
			
			product.setPimgPath("/upload-image/" + uniqueName + fileExtension);
			
			// File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid 적용 전
			
			File saveFile = new File(uploadFolder+"\\"+uniqueName + fileExtension);  // 적용 후
			try {
				file.transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			pRepository.save(product);
			
			model.addAttribute("result", "상품이 성공적으로 등록됬습니다.");
			model.addAttribute("url", "/admin/registedProductManagement");
			
		}else {
			model.addAttribute("result", "등록에 실패했습니다.");
			model.addAttribute("url", "/index");
		}
		
		return "/alert";
	}
	
	/*
	@RequestMapping("/productList")
	public String productList(Model model) {
		
		List<Product> list = admin.findAll();
		model.addAttribute("productList", list);
		
		return "/admin/productList";
	}
	*/
	
	@RequestMapping("/editProductForm")
	public String editProductForm(@RequestParam("pno") Long pno, Model model) {
		
		Product prod = pRepository.findByPno(pno);
		model.addAttribute("product", prod);
		
		return "/admin/editProductForm";
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
	
	@RequestMapping("/registedProductManagement")
	public String RegistedProductManagement(HttpSession session, Model model) {
		
		String user_name = (String) session.getAttribute("user_name");
		List<Product> prod = pRepository.findByRegistrant(user_name);
		model.addAttribute("productList", prod);
		
		return "/admin/registedProductManagement";
	}
	
	
	
	
	
	
}
