package com.asm.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asm.entity.OrderDetails;
import com.asm.entity.Orders;
import com.asm.entity.Products;
import com.asm.entity.Reviews;
import com.asm.entity.dao.OrderDetailsDAO;
import com.asm.entity.dao.OrdersDAO;
import com.asm.entity.dao.ProductsDAO;

@Controller
@RequestMapping("/app/admin")
public class OrderController {
	@Autowired
	OrdersDAO ordersDAO;

	@Autowired
	OrderDetailsDAO orderdetailDAO;

	@Autowired
	ProductsDAO productDAO;

	@RequestMapping("/order_list")
	public String order_list(Model model) {
		List<Orders> orders = ordersDAO.findAll();
		model.addAttribute("orders", orders);
		return "admin/order_list";
	}

	@RequestMapping("/order_returned")
	public String order_returned(Model model) {
		List<Orders> orders = ordersDAO.findAll();
		model.addAttribute("orders", orders);
		return "admin/order_returned";
	}

	@RequestMapping("/order_processing")
	public String order_processing(Model model) {
		List<Orders> orders = ordersDAO.findAll();
		model.addAttribute("orders", orders);
		return "admin/order_processing";
	}

	@RequestMapping("/order_delivering")
	public String order_delivering(Model model) {
		List<Orders> orders = ordersDAO.findAll();
		model.addAttribute("orders", orders);
		return "admin/order_delivering";
	}

	@RequestMapping("/order_cancelled")
	public String order_canceled(Model model) {
		List<Orders> orders = ordersDAO.findAll();
		model.addAttribute("orders", orders);
		return "admin/order_cancelled";
	}

	@RequestMapping("/order_success")
	public String order_success(Model model) {
		List<Orders> orders = ordersDAO.findAll();
		model.addAttribute("orders", orders);
		return "admin/order_success";
	}

	@RequestMapping("/order-detail/{orderId}")
	public String showOrderDetail(@PathVariable int orderId, Model model) {
		Optional<Orders> orderOptional = ordersDAO.findById(orderId);
		if (orderOptional.isPresent()) {
			Orders order = orderOptional.get();
			List<OrderDetails> orderDetails = orderdetailDAO.findByOrder(order);
			model.addAttribute("order", order);
			model.addAttribute("orderDetails", orderDetails);
			return "admin/order_detail";
		} else {
			// Xử lý khi không tìm thấy đơn hàng với orderId tương ứng
			return "redirect:/app/admin/order_processing"; // Hoặc chuyển hướng đến trang nào đó khác
		}
	}

	@PostMapping("/setDelivering")
	public String setDelivering(@RequestParam("orderId") int orderId) {
		Optional<Orders> orderOptional = ordersDAO.findById(orderId);
		if (orderOptional.isPresent()) {
			Orders order = orderOptional.get();
			order.setOrderStatus("Delivering");
			ordersDAO.save(order);
			return "redirect:/app/admin/order_processing"; // Hoặc chuyển hướng đến trang nào đó khác
		} else {
			// Xử lý khi không tìm thấy đơn hàng với orderId tương ứng
			return "redirect:/app/admin/order_processing"; // Hoặc chuyển hướng đến trang nào đó khác
		}
	}

	@PostMapping("/setSuccess")
	public String setSuccess(@RequestParam("orderId") int orderId) {
		Optional<Orders> orderOptional = ordersDAO.findById(orderId);
		if (orderOptional.isPresent()) {
			Orders order = orderOptional.get();
			order.setOrderStatus("Success");
			ordersDAO.save(order);
			return "redirect:/app/admin/order_success"; // Hoặc chuyển hướng đến trang nào đó khác
			// Hoặc chuyển hướng đến trang nào đó khác
		} else {
			// Xử lý khi không tìm thấy đơn hàng với orderId tương ứng
			return "admin/order_delivering"; // Hoặc chuyển hướng đến trang nào đó khác
		}
	}

	@PostMapping("/setCancelled")
	public String setCancelled(@RequestParam("orderId") int orderId) {
		Optional<Orders> orderOptional = ordersDAO.findById(orderId);
		if (orderOptional.isPresent()) {
			Orders order = orderOptional.get();
			order.setOrderStatus("Cancelled");
			ordersDAO.save(order);
			return "redirect:/app/admin/order_processing"; // Hoặc chuyển hướng đến trang nào đó khác
		} else {
			// Xử lý khi không tìm thấy đơn hàng với orderId tương ứng
			return "redirect:/app/admin/order_processing"; // Hoặc chuyển hướng đến trang nào đó khác
		}
	}

	@PostMapping("/setReturned")
	public String setReturned(@RequestParam("orderId") int orderId) {
		Optional<Orders> orderOptional = ordersDAO.findById(orderId);
		if (orderOptional.isPresent()) {
			Orders order = orderOptional.get();
			order.setOrderStatus("Returned");
			ordersDAO.save(order);
			return "redirect:/app/admin/order_processing"; // Hoặc chuyển hướng đến trang nào đó khác
		} else {
			// Xử lý khi không tìm thấy đơn hàng với orderId tương ứng
			return "redirect:/app/admin/order_processing"; // Hoặc chuyển hướng đến trang nào đó khác
		}
	}

}
