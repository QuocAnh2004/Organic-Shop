package com.asm.entity.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asm.entity.Reviews;

public interface ReviewsDAO extends JpaRepository<Reviews, Integer> {
	
	 // Phương thức lấy danh sách đánh giá theo id sản phẩm sử dụng @Query
    @Query("SELECT r FROM Reviews r WHERE r.product.productId = :productId")
    List<Reviews> find_ReviewProduct_By_ProductId(@Param("productId") int productId);
}
