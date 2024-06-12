package com.asm.entity.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.asm.entity.Products;

public interface ProductsDAO extends JpaRepository<Products, Integer> {

	// PHÂN TRANG VỚI THAM SỐ PAGEABLE
    Page<Products> findAll(Pageable pageable);
    
    Page<Products> findAllByProductNameLike(String keyword, Pageable pageable ); // dsl;
    
    // New method to find products by category
    Page<Products> findAllByCategoryCategoryId(int categoryId, Pageable pageable);
    Page<Products> findAllByPriceBetween(float minPrice, float maxPrice, Pageable pageable);
}
