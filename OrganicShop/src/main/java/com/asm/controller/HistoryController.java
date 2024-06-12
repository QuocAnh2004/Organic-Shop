package com.asm.controller;

import com.asm.entity.Customers;
import com.asm.entity.OrderDetails;
import com.asm.entity.Orders;
import com.asm.entity.Users;
import com.asm.entity.dao.CustomersDAO;
import com.asm.entity.dao.OrderDetailsDAO;
import com.asm.entity.dao.OrdersDAO;
import com.asm.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/app/user")
public class HistoryController {

    @Autowired
    private OrdersDAO ordersDAO;

    @Autowired
    private OrderDetailsDAO orderDetailsDAO;
    
    @Autowired
    private CustomersDAO customersDAO;

    @Autowired
    private SessionService sessionService;

    @GetMapping("/order-history")
    public String viewOrderHistory(Model model) {
    	Users username = (Users) sessionService.get("loggedInUser");
        if (username.getUsername() != null) {
            Customers customer = customersDAO.findByUsername(username.getUsername());
            if (customer != null) {
                List<Orders> orders = ordersDAO.findOrdersByCustomerId(customer.getCustomerId());
                model.addAttribute("orders", orders);
            }
        } else {
            return "redirect:/app/user/login";
        }
        return "user/order-history";
    }

    @GetMapping("/order-details/{orderId}")
    public String viewOrderDetails(@PathVariable("orderId") int orderId, Model model) {
        List<OrderDetails> orderDetails = orderDetailsDAO.findOrderDetailsByOrderId(orderId);
        model.addAttribute("orderDetails", orderDetails);
        return "user/order-details";
    }
}
