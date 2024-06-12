package com.asm.controller;

import com.asm.entity.Customers;
import com.asm.entity.OrderDetails;
import com.asm.entity.Orders;
import com.asm.entity.Products;
import com.asm.entity.Users;
import com.asm.entity.Vouchers;
import com.asm.entity.dao.CustomersDAO;
import com.asm.entity.dao.OrderDetailsDAO;
import com.asm.entity.dao.OrdersDAO;
import com.asm.entity.dao.VouchersDAO;
import com.asm.services.SessionService;
import com.asm.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/app/user")
public class CheckOutController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private CustomersDAO customersDAO;

    @Autowired
    private OrdersDAO ordersDAO;

    @Autowired
    private OrderDetailsDAO orderDetailsDAO;
    @Autowired
    VouchersDAO vouchersDAO;

    @GetMapping("/checkout")
    public String showCheckoutPage(Model model) {
        model.addAttribute("subtotal", shoppingCartService.getAmount());
        model.addAttribute("cartCount", shoppingCartService.getCount());
        model.addAttribute("cartItems", shoppingCartService.getItems());

        Users username = (Users) sessionService.get("loggedInUser");
        if (username.getUsername() != null) {
            Customers customer = customersDAO.findByUsername(username.getUsername());
            if (customer != null) {
                model.addAttribute("customer", customer);
            }
        }
        return "user/checkout";
    }

    @PostMapping("/checkout")
    public String placeOrder(Model model) {
    	Users username = (Users) sessionService.get("loggedInUser");
        if (username.getUsername() != null) {
            Customers customer = customersDAO.findByUsername(username.getUsername());
            if (customer != null) {
                // Create order
                Orders order = new Orders();
                order.setCustomer(customer);
                order.setOrderDate(new Date());
              //  Double discountValueSession= sessionService.get("discountValueSession");
//                float discountValueSession = (float) sessionService.get("discountValueSession");
                Float discountValueSession = (Float) sessionService.get("discountValueSession");
                if (discountValueSession == null) {
                    discountValueSession = 0.0f;
                }
                Double subtotalAfterDiscount = shoppingCartService.getAmount() -(shoppingCartService.getAmount() * (discountValueSession/100));

//                BigDecimal subtotal = BigDecimal.valueOf(shoppingCartService.getAmount());
//                BigDecimal subtotal = BigDecimal.valueOf(subtotalAfterDiscount);
                BigDecimal subtotal = BigDecimal.valueOf(subtotalAfterDiscount).setScale(2, RoundingMode.DOWN);

                BigDecimal shippingFee = new BigDecimal("15.00");
//                BigDecimal totalAmount = subtotal.add(shippingFee);
                BigDecimal totalAmount = subtotal.add(shippingFee).setScale(2, RoundingMode.DOWN);

                System.out.println("shoppingCartService.getAmount() "+ shoppingCartService.getAmount());
                System.out.println("discountValueSession "+ discountValueSession);
                System.out.println("subtotalAfterDiscount "+  subtotalAfterDiscount);
                System.out.println("subtotal "+ subtotal);
                System.out.println("totalAmount "+ totalAmount);
                
                order.setTotalAmount(totalAmount);
                order.setShippingAddress(customer.getAddress());
                order.setOrderStatus("Pending");
                Orders savedOrder = ordersDAO.save(order);

                // Create order details
                List<Products> cartItems = shoppingCartService.getItems();
                for (Products product : cartItems) {
                    OrderDetails orderDetails = new OrderDetails();
                    orderDetails.setOrder(savedOrder); 
                    orderDetails.setProduct(product);
                    orderDetails.setQuantity(product.getQuantity());
                    orderDetails.setUnitPrice(BigDecimal.valueOf(product.getPrice()));
                    orderDetails.setTotalPrice(BigDecimal.valueOf(product.getPrice() * product.getQuantity()));
                    orderDetailsDAO.save(orderDetails);
                }

                // Clear the shopping cart
                shoppingCartService.clear();

                // Add order and order details to model
                model.addAttribute("order", savedOrder);
                model.addAttribute("orderDetails", orderDetailsDAO.findByOrderOrderId(savedOrder.getOrderId()));

                // Add calculated total amount to model
                model.addAttribute("totalAmount", totalAmount);

                // Redirect to successful order page
                sessionService.remove("discountValueSession");
                return "user/successful";
            }
        }
        return "redirect:/app/user/checkout";
    }
    @PostMapping("/checkout/apply-voucher")
    public String applyVoucher(@RequestParam("coupon") Optional<String> voucherCodeOpt, Model model) {
    	 model.addAttribute("subtotal", shoppingCartService.getAmount());
         model.addAttribute("cartCount", shoppingCartService.getCount());
         model.addAttribute("cartItems", shoppingCartService.getItems());
//         String username = (String) sessionService.get("loggedInUser");
         Users username = (Users) sessionService.get("loggedInUser");
         
         if (username != null) {
             Customers customer = customersDAO.findByUsername(username.getUsername());
             if (customer != null) {
                 model.addAttribute("customer", customer);
             }
         }

    	if (!voucherCodeOpt.isPresent() || voucherCodeOpt.get().isEmpty()) {
            return "redirect:/app/user/checkout";
        }

        String voucherCode = voucherCodeOpt.get();
        System.out.println("voucherCode "+voucherCode);
        Optional<Vouchers> voucherOpt = vouchersDAO.findByVoucherCode(voucherCode);
        double subtotal = shoppingCartService.getAmount(); // tổng giá tiền
        double discountAmount = 0;

        if (voucherOpt.isPresent()) {
            Vouchers voucher = voucherOpt.get();
            if (voucher.isActive() && voucher.getExpiryDate().after(new Date())) {
                discountAmount = voucher.getDiscountAmount() / 100.0 * subtotal; // 10/ (100* 10) = 0.01 * 1000 = 10%
                // 10 
                System.out.println("discaount "+ discountAmount); 
                System.out.println("discaount PHAN TRAM "+ voucher.getDiscountAmount()); 
                
                model.addAttribute("discountValue", voucher.getDiscountAmount());
                System.out.println("discountValue "+voucher.getDiscountAmount());
                
                sessionService.set("discountValueSession", +voucher.getDiscountAmount());
                
                subtotal -= discountAmount; // subtoatal = subtotal - discount
                
                System.out.println("sub "+subtotal);
                model.addAttribute("message", "Coupon applied");
                
                vouchersDAO.updateVoucherQuantityByVoucherCode(voucher.getVoucherCode(), voucher.getQuantity()-1);

            } else {
                model.addAttribute("message", "Invalid Coupon");
            }
        } else {
            model.addAttribute("message", "Invalid Coupon");
        }
        
        model.addAttribute("cart", shoppingCartService.getItems());
        model.addAttribute("subtotal", shoppingCartService.getAmount());
        model.addAttribute("discountAmount", discountAmount);
        model.addAttribute("totalAfterDiscount", subtotal);
        model.addAttribute("cartCount", shoppingCartService.getCount());
        return "user/checkout";
    }
}
