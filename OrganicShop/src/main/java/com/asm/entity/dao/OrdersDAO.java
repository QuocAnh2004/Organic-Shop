package com.asm.entity.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asm.entity.Customers;
import com.asm.entity.Orders;

public interface OrdersDAO extends JpaRepository<Orders, Integer> {
    // Bổ sung các phương thức tùy chỉnh nếu cần
	  @Query("SELECT c FROM Customers c WHERE c.user.userId = ?1")
	    Customers findByUserId(String userId);

	    @Query("SELECT c FROM Customers c WHERE c.user.username = ?1")
	    Customers findByUsername(String username);

//	    Customers findByEmail(String email);
	    @Query("SELECT o FROM Orders o WHERE o.customer.email = :email")
	    List<Orders> findByCustomerEmail(@Param("email") String email);
	    @Query("SELECT COUNT(o) FROM Orders o WHERE o.orderStatus = :status")
	    long countByOrderStatus(@Param("status") String status);
		
		@Query("SELECT SUM(o.totalAmount) FROM Orders o WHERE o.orderDate = CURRENT_DATE" )
	    BigDecimal sumTotalAmountByDate();
		@Query("SELECT SUM(o.totalAmount) FROM Orders o")
	    BigDecimal sumTotalRevenue();
		
		@Query("SELECT o FROM Orders o WHERE o.customer.customerId = ?1")
	    List<Orders> findOrdersByCustomerId(int customerId);
}
