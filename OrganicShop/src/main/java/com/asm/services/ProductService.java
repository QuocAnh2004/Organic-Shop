package com.asm.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.asm.entity.Products;
import com.asm.entity.dao.ProductsDAO;

@Service
public class ProductService {

    @Autowired
    private ProductsDAO productDAO;

    // Thêm sản phẩm mới
    public void addProduct(Products product) {
    	productDAO.save(product);
    }

    public void updateProduct(Products product) {
        productDAO.save(product); // Khi ID tồn tại, save sẽ thực hiện cập nhật
    }

    // Xóa sản phẩm
    public void deleteProduct(int productId) {
        productDAO.deleteById(productId);
    }

    // Lấy sản phẩm theo ID
    public Products getProductById(int productId) {
        return productDAO.findById(productId).orElse(null);
    }
    
    public List<Products> getAllProducts() {
        return productDAO.findAll();
    }
}

