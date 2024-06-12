package com.asm.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asm.entity.Products;
import com.asm.entity.dao.ProductsDAO;
import com.asm.services.OrderService;
import com.asm.services.SessionService;
import com.asm.services.ShoppingCartService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("app/user/home")
public class HomeController {

	@Autowired
	ProductsDAO productsDAO;
	@Autowired
	ShoppingCartService shoppingCartService;
	@Autowired
	OrderService orderSerivce;
	

	@RequestMapping("/index")
	public String index(Model model) {
		Pageable pageable = PageRequest.of(0, 8); // Lấy 8 sản phẩm đầu tiên
	    Page<Products> productsPage = productsDAO.findAll(pageable);
	    List<Products> products = productsPage.getContent();
	    List<Map<String, Object>> top10Products = orderSerivce.getTop10MostPurchasedProducts();
	    model.addAttribute("top10Products", top10Products);
	    model.addAttribute("subtotal", shoppingCartService.getAmount()); // Thêm tổng giá trị vào model
        model.addAttribute("cartCount", shoppingCartService.getCount()); 
	    model.addAttribute("products", products);
		return "user/index";
	}
	
	  @PostMapping("add-to-cart/{id}")
	    public String addToCart(@PathVariable("id") int productId, HttpSession session, Model model) {
	        shoppingCartService.add(productId);
	        model.addAttribute("cart", shoppingCartService.getItems());
	        model.addAttribute("subtotal", shoppingCartService.getAmount());
	        model.addAttribute("cartCount", shoppingCartService.getCount());

	        // Redirect người dùng về trang hiện tại hoặc trang sản phẩm
	        return "redirect:/app/user/home/index"; // Hoặc "redirect:/product/detail/" + productId nếu muốn trở về trang chi tiết sản phẩm
	    }
	  
	 
	

}
