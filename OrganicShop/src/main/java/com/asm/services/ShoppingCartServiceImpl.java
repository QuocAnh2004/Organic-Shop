package com.asm.services;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.context.annotation.SessionScope;
//
//import com.asm.entity.Products;
//import com.asm.entity.dao.ProductsDAO;
//
//@SessionScope
//@Service
//public class ShoppingCartServiceImpl implements ShoppingCartService {
//
//    private final Map<Integer, Products> cart = new HashMap<>();
//
//    @Autowired
//    private ProductsDAO productsDAO;
//
//    @Override
//    @Transactional // Đảm bảo giao dịch cho việc lấy thông tin sản phẩm từ cơ sở dữ liệu
//    public Products add(Integer productId) {
//        Optional<Products> optionalProduct = productsDAO.findById(productId);
//        if (optionalProduct.isPresent()) {
//            Products product = optionalProduct.get();
//            if (!cart.containsKey(productId)) {
//                product.setQuantity(1);
//                cart.put(productId, product);
//            } else {
//                Products existingProduct = cart.get(productId);
//                existingProduct.setQuantity(existingProduct.getQuantity() + 1);
//            }
//            return product;
//        } else {
//            throw new RuntimeException("Product with ID " + productId + " not found.");
//        }
//    }
//
//    @Override
//    public void remove(Integer productId) {
//        cart.remove(productId);
//    }
//
//    @Override
//    public Products update(Integer productId, String action) {
//        if (cart.containsKey(productId)) {
//            Products product = cart.get(productId);
//            if ("minus".equals(action) && product.getQuantity() > 1) {
//                product.setQuantity(product.getQuantity() - 1);
//            } else if ("plus".equals(action)) {
//                product.setQuantity(product.getQuantity() + 1);
//            }
//            return product;
//        } else {
//            throw new RuntimeException("Product with ID " + productId + " not found in cart.");
//        }
//    }
//
//    @Override
//    public void clear() {
//        cart.clear();
//    }
//
//    @Override
//    public Collection<Products> getItems() {
//        return cart.values();
//    }
//
//    @Override
//    public int getCount() {
//        return cart.values().stream().mapToInt(Products::getQuantity).sum();
//    }
//
//    @Override
//    public double getAmount() {
//        return cart.values().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
//    }
//}
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import com.asm.entity.Products;
import com.asm.entity.dao.ProductsDAO;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private static final String SESSION_KEY = "cart";

    @Autowired
    private SessionService session;

    @Autowired
    private ProductsDAO productsDAO;

    @Override
    @Transactional
    public void add(Integer productId) {
        List<Products> cart = (List<Products>) session.get(SESSION_KEY);
        if (cart == null) {
            cart = new ArrayList<>();
        }
        Products product = productsDAO.findById(productId).orElse(null);
        if (product != null) {
            boolean exists = false;
            for (Products p : cart) {
                if (p.getProductId() == productId) {
                    p.setQuantity(p.getQuantity() + 1);
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                product.setQuantity(1);
                cart.add(product);
            }
            session.set(SESSION_KEY, cart);
        }
    }

    @Override
    public void remove(Integer productId) {
        List<Products> cart = (List<Products>) session.get(SESSION_KEY);
        if (cart != null) {
            // Xóa sản phẩm khỏi giỏ hàng
            cart.removeIf(product -> product.getProductId()==productId);
            // Lưu lại danh sách mới vào session
            session.set(SESSION_KEY, cart);
        }
    }

    @Override
    public void clear() {
        // Xóa toàn bộ sản phẩm khỏi giỏ hàng
        session.remove(SESSION_KEY);
    }

    @Override
    public List<Products> getItems() {
        // Lấy danh sách sản phẩm từ session
        return (List<Products>) session.get(SESSION_KEY);
    }

    @Override
    public int getCount() {
        List<Products> cart = (List<Products>) session.get(SESSION_KEY);
        int count = 0;
        if (cart != null) {
            for (Products product : cart) {
                count += product.getQuantity();
            }
        }
        return count;
    }


    @Override
    public double getAmount() {
        List<Products> cart = (List<Products>) session.get(SESSION_KEY);
        double amount = 0.0;
        if (cart != null) {
            for (Products product : cart) {
                amount += product.getPrice() * product.getQuantity();
            }
        }
        return amount;
    }
    @Override
    public Products update(Integer productId, String action) {
        List<Products> cart = (List<Products>) session.get(SESSION_KEY);
        if (cart != null) {
            for (Products product : cart) {
                if (product.getProductId() == productId) {
                    if ("minus".equals(action)) {
                        if (product.getQuantity() > 1) {
                            product.setQuantity(product.getQuantity() - 1);
                        } else {
                            cart.remove(product);
                        }
                    } else if ("plus".equals(action)) {
                        product.setQuantity(product.getQuantity() + 1);
                    }
                    session.set(SESSION_KEY, cart);
                    return product;
                }
            }
        }
        throw new RuntimeException("Product with ID " + productId + " not found in cart.");
    }
}

