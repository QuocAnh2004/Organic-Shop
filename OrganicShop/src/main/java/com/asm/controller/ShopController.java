//package com.asm.controller;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import com.asm.entity.Products;
//import com.asm.entity.Reviews;
//import com.asm.entity.Vouchers;
//import com.asm.entity.dao.ProductsDAO;
//import com.asm.entity.dao.ReviewsDAO;
//import com.asm.entity.dao.VouchersDAO;
//import com.asm.services.CookieService;
//import com.asm.services.SessionService;
//import com.asm.services.ShoppingCartService;
//
//import jakarta.servlet.http.HttpSession;
//
//@Controller
//@RequestMapping("product")
//public class ShopController {
//	@Autowired
//	ProductsDAO productsDAO;
//	@Autowired
//	ReviewsDAO reviewsDAO;
//    @Autowired
//    ShoppingCartService shoppingCartService;
//    @Autowired
//    CookieService cookieService;
//    @Autowired
//    VouchersDAO vouchersDAO;
//
//	@RequestMapping("/index")
//	public String product(Model model, @RequestParam("page") Optional<Integer> page) {
//		Pageable pageable = PageRequest.of(page.orElse(0), 9); // truy xuất trang thứ 0 có 2 sản phẩm
//		Page<Products> items_products = productsDAO.findAll(pageable); // truy vấn tất cả
//		model.addAttribute("subtotal", shoppingCartService.getAmount()); // Thêm tổng giá trị vào model
//        model.addAttribute("cartCount", shoppingCartService.getCount()); 
//		model.addAttribute("items_products", items_products);
//
//		return "user/shop";
//	}
//
//	// Phương thức hiển thị chi tiết sản phẩm
//	@GetMapping("/detail/{id}")
//	public String productDetail(@PathVariable("id") int id_product, Model model) {
//		Optional<Products> product = productsDAO.findById(id_product);
//		if (product.isPresent()) {
//			// Lấy danh sách đánh giá theo id sản phẩm
//			List<Reviews> reviews = reviewsDAO.find_ReviewProduct_By_ProductId(id_product);
//			model.addAttribute("product", product.get());
//			model.addAttribute("reviews", reviews);
//			model.addAttribute("subtotal", shoppingCartService.getAmount()); // Thêm tổng giá trị vào model
//	        model.addAttribute("cartCount", shoppingCartService.getCount()); 
//			return "user/shop-detail"; // Tên của trang JSP hiển thị chi tiết sản phẩm
//		} else {
//			return "redirect:/product/index"; // Nếu sản phẩm không tồn tại, chuyển hướng về danh sách sản phẩm
//		}
//	}
//	@PostMapping("add-to-cart/{id}")
//    public String addToCart(@PathVariable("id") int productId, HttpSession session,Model model) {
//        // Thêm sản phẩm vào giỏ hàng
//        shoppingCartService.add(productId);
//        // Redirect người dùng đến trang giỏ hàng sau khi thêm sản phẩm thành công
//        model.addAttribute("cart", shoppingCartService.getItems());
//        model.addAttribute("subtotal", shoppingCartService.getAmount()); // Thêm tổng giá trị vào model
//        model.addAttribute("cartCount", shoppingCartService.getCount()); 
//        
////        return "redirect:/cart";
//        return "user/cart";
//    }
//
//    @PostMapping("/cart/remove/{id}")
//    public String removeFromCart(@PathVariable("id") int productId, HttpSession session,Model model) {
//        // Xóa sản phẩm khỏi giỏ hàng
//        shoppingCartService.remove(productId);
//        // Redirect người dùng đến trang giỏ hàng sau khi xóa sản phẩm thành công
//        model.addAttribute("subtotal", shoppingCartService.getAmount()); // Thêm tổng giá trị vào model
//        model.addAttribute("cartCount", shoppingCartService.getCount()); 
//        return "user/cart";
//    }
//    
//    @GetMapping("/cart")
//    public String viewCart(Model model, HttpSession session) {
//    	System.out.print(""+shoppingCartService.getAmount());
//        model.addAttribute("cart", shoppingCartService.getItems());
//        model.addAttribute("cartCount", shoppingCartService.getCount()); 
//        model.addAttribute("subtotal", shoppingCartService.getAmount()); // Thêm tổng giá trị vào model
//        return "user/cart";
//    }
//
//    @PostMapping("/cart/update/{id}")
//    public String updateCart(@PathVariable("id") int productId, @RequestParam("action") String action, Model model) {
//        shoppingCartService.update(productId, action);
//        model.addAttribute("cart", shoppingCartService.getItems());
//        model.addAttribute("subtotal", shoppingCartService.getAmount()); // Thêm tổng giá trị vào model
//        model.addAttribute("cartCount", shoppingCartService.getCount()); 
//        
//        return "user/cart";
//    }
//    @GetMapping("/cart/apply-voucher")
//    public String redirectToCart() {
//        return "redirect:/product/cart";
//    }
//    
//    @PostMapping("/cart/apply-voucher")
//    public String applyVoucher(@RequestParam("coupon") Optional<String> voucherCodeOpt, Model model) {
//        if (!voucherCodeOpt.isPresent() || voucherCodeOpt.get().isEmpty()) {
//            return "redirect:/product/cart";
//        }
//        
//        String voucherCode = voucherCodeOpt.get();
//        Optional<Vouchers> voucherOpt = vouchersDAO.findByVoucherCode(voucherCode);
//        double subtotal = shoppingCartService.getAmount();
//        double discountAmount = 0;
//
//        if (voucherOpt.isPresent()) {
//            Vouchers voucher = voucherOpt.get();
//            if (voucher.isActive() && voucher.getExpiryDate().after(new Date())) {
//                discountAmount = voucher.getDiscountAmount() / 100.0 * subtotal; // Tính giảm giá theo phần trăm
//                subtotal -= discountAmount;
//                model.addAttribute("message", "Coupon applied");
//            } else {
//                model.addAttribute("message", "Invalid Coupon");
//            }
//        } else {
//            model.addAttribute("message", "Invalid Coupon");
//        }
//
//        model.addAttribute("cart", shoppingCartService.getItems());
//        model.addAttribute("subtotal", shoppingCartService.getAmount());
//        model.addAttribute("discountAmount", discountAmount);
//        model.addAttribute("totalAfterDiscount", subtotal);
//        model.addAttribute("cartCount", shoppingCartService.getCount());
//        return "user/cart";
//    }
//}
package com.asm.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asm.entity.Categories;
import com.asm.entity.Products;
import com.asm.entity.Reviews;
import com.asm.entity.Vouchers;
import com.asm.entity.dao.CatogoriesDAO;
import com.asm.entity.dao.ProductsDAO;
import com.asm.entity.dao.ReviewsDAO;
import com.asm.entity.dao.VouchersDAO;
import com.asm.services.CookieService;
import com.asm.services.SessionService;
import com.asm.services.ShoppingCartService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("app/user/product")
public class ShopController {
    @Autowired
    ProductsDAO productsDAO;
    @Autowired
    ReviewsDAO reviewsDAO;
    @Autowired
    ShoppingCartService shoppingCartService;
    @Autowired
    CookieService cookieService;
    @Autowired
    VouchersDAO vouchersDAO;
    @Autowired
	SessionService sessionService;
    @Autowired
    CatogoriesDAO categoriesDAO;

//    @RequestMapping("/index")
//    public String product(Model model, @RequestParam("page") Optional<Integer> page) {
//        Pageable pageable = PageRequest.of(page.orElse(0), 9);
//        Page<Products> items_products = productsDAO.findAll(pageable);
//        model.addAttribute("subtotal", shoppingCartService.getAmount());
//        model.addAttribute("cartCount", shoppingCartService.getCount());
//        model.addAttribute("items_products", items_products);
//
//        return "user/shop";
//    }
    @RequestMapping("/index")
    public String product(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("categoryId") Optional<Integer> categoryId, @RequestParam("minPrice") Optional<Float> minPrice, @RequestParam("maxPrice") Optional<Float> maxPrice, @RequestParam("sort") Optional<String> sort) {
        Pageable pageable;

        // Handle sorting by price
        if (sort.isPresent()) {
            Sort.Direction direction = sort.get().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
            pageable = PageRequest.of(page.orElse(0), 9, Sort.by(direction, "price"));
        } else {
            pageable = PageRequest.of(page.orElse(0), 9);
        }

        Page<Products> items_products;

        // Handle filtering by category
        if (categoryId.isPresent()) {
            items_products = productsDAO.findAllByCategoryCategoryId(categoryId.get(), pageable);
            model.addAttribute("categoryId", categoryId.get());
        } else {
            items_products = productsDAO.findAll(pageable);
        }

        // Handle filtering by price range
        if (minPrice.isPresent() && maxPrice.isPresent()) {
            items_products = productsDAO.findAllByPriceBetween(minPrice.get(), maxPrice.get(), pageable);
            model.addAttribute("minPrice", minPrice.get());
            model.addAttribute("maxPrice", maxPrice.get());
        }

        List<Categories> categories = categoriesDAO.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("subtotal", shoppingCartService.getAmount());
        model.addAttribute("cartCount", shoppingCartService.getCount());
        model.addAttribute("items_products", items_products);

        return "user/shop";
    }

    @GetMapping("/detail/{id}")
    public String productDetail(@PathVariable("id") int id_product, Model model) {
        Optional<Products> product = productsDAO.findById(id_product);
        if (product.isPresent()) {
            List<Reviews> reviews = reviewsDAO.find_ReviewProduct_By_ProductId(id_product);
            model.addAttribute("product", product.get());
            model.addAttribute("reviews", reviews);
            model.addAttribute("subtotal", shoppingCartService.getAmount());
            model.addAttribute("cartCount", shoppingCartService.getCount());
            return "user/shop-detail";
        } else {
            return "redirect:/app/user/product/index";
        }
    }
    
    @PostMapping("/detail/add-to-cart/{id}")
    public String addToCartFormDeatil(@PathVariable("id") int productId, HttpSession session, Model model) {
        shoppingCartService.add(productId);
        model.addAttribute("cart", shoppingCartService.getItems());
        model.addAttribute("subtotal", shoppingCartService.getAmount());
        model.addAttribute("cartCount", shoppingCartService.getCount());

        // Redirect người dùng về trang hiện tại hoặc trang sản phẩm
        return "redirect:/app/user/product/detail/"+productId; // Hoặc "redirect:/product/detail/" + productId nếu muốn trở về trang chi tiết sản phẩm
    }

    @PostMapping("add-to-cart/{id}")
    public String addToCart(@PathVariable("id") int productId, HttpSession session, Model model) {
        shoppingCartService.add(productId);
        model.addAttribute("cart", shoppingCartService.getItems());
        model.addAttribute("subtotal", shoppingCartService.getAmount());
        model.addAttribute("cartCount", shoppingCartService.getCount());

        // Redirect người dùng về trang hiện tại hoặc trang sản phẩm
        return "redirect:/app/user/product/index"; // Hoặc "redirect:/product/detail/" + productId nếu muốn trở về trang chi tiết sản phẩm
    }

    @PostMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable("id") int productId, HttpSession session, Model model) {
        shoppingCartService.remove(productId);
        model.addAttribute("subtotal", shoppingCartService.getAmount());
        model.addAttribute("cartCount", shoppingCartService.getCount());
        return "user/cart";
    }

    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        model.addAttribute("cart", shoppingCartService.getItems());
        model.addAttribute("cartCount", shoppingCartService.getCount());
        model.addAttribute("subtotal", shoppingCartService.getAmount());
        return "user/cart";
    }

    @PostMapping("/cart/update/{id}")
    public String updateCart(@PathVariable("id") int productId, @RequestParam("action") String action, Model model) {
        shoppingCartService.update(productId, action);
        model.addAttribute("cart", shoppingCartService.getItems());
        model.addAttribute("subtotal", shoppingCartService.getAmount());
        model.addAttribute("cartCount", shoppingCartService.getCount());

        return "redirect:/app/user/product/cart";

//        return "user/cart";
    }

    @GetMapping("/cart/apply-voucher")
    public String redirectToCart() {
        return "redirect:/app/user/product/cart";
    }

//    @PostMapping("/cart/apply-voucher")
//    public String applyVoucher(@RequestParam("coupon") Optional<String> voucherCodeOpt, Model model) {
//        if (!voucherCodeOpt.isPresent() || voucherCodeOpt.get().isEmpty()) {
//            return "redirect:/product/cart";
//        }
//
//        String voucherCode = voucherCodeOpt.get();
//        Optional<Vouchers> voucherOpt = vouchersDAO.findByVoucherCode(voucherCode);
//        double subtotal = shoppingCartService.getAmount(); // tổng giá tiền
//        double discountAmount = 0;
//
//        if (voucherOpt.isPresent()) {
//            Vouchers voucher = voucherOpt.get();
//            if (voucher.isActive() && voucher.getExpiryDate().after(new Date())) {
//                discountAmount = voucher.getDiscountAmount() / 100.0 * subtotal; // 10/ (100* 10) = 0.01 * 1000 = 10%
//                // 10 
//                System.out.println("discaount "+ discountAmount); 
//                System.out.println("discaount PHAN TRAM "+ voucher.getDiscountAmount()); 
//                subtotal -= discountAmount; // subtoatal = subtotal - discount
//                System.out.println("sub "+subtotal);
//                model.addAttribute("message", "Coupon applied");
//                vouchersDAO.updateVoucherQuantityByVoucherCode(voucher.getVoucherCode(), voucher.getQuantity()-1);
//
//            } else {
//                model.addAttribute("message", "Invalid Coupon");
//            }
//        } else {
//            model.addAttribute("message", "Invalid Coupon");
//        }
//        
//        model.addAttribute("cart", shoppingCartService.getItems());
//        model.addAttribute("subtotal", shoppingCartService.getAmount());
//        model.addAttribute("discountAmount", discountAmount);
//        model.addAttribute("totalAfterDiscount", subtotal);
//        model.addAttribute("cartCount", shoppingCartService.getCount());
//        return "user/cart";
//    }
    
    @RequestMapping("/search-and-page")
	public String searchAndPage(Model model,@RequestParam("keywords") Optional<String> kw, @RequestParam("page") Optional<Integer> page) {
		String kwords = kw.orElse(sessionService.get("keywords", ""));
		sessionService.set("keywords", kwords); // lưu sesion để duy trì list chứa từ khóa đang tìm kiếm
		Pageable pageable = PageRequest.of(page.orElse(0), 9); // truy xuất trang thứ 0 có 2 sản phẩm
		Page<Products> items_products = productsDAO.findAllByProductNameLike("%"+kwords+"%", pageable); // truy vấn tất cả
		
		if(items_products.isEmpty())
		{
			model.addAttribute("message", sessionService.get("keywords"));
			return "user/404";
		}else {
			 List<Categories> categories = categoriesDAO.findAll();
		        model.addAttribute("categories", categories);
		        model.addAttribute("subtotal", shoppingCartService.getAmount());
		        model.addAttribute("cartCount", shoppingCartService.getCount());
			model.addAttribute("keyword", sessionService.get("keywords"));
			model.addAttribute("items_products", items_products);
			
		}
		
		return "user/shop";
	}
}
