package com.asm.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asm.entity.OrderDetails;
import com.asm.entity.Orders;

import java.util.List;

public interface OrderDetailsDAO extends JpaRepository<OrderDetails, Integer> {
//    List<OrderDetails> findByOrderOrderId(int orderId);
	List<OrderDetails> findByOrderOrderId(int orderId);
	List<OrderDetails> findByOrder(Orders order);
	 @Query("SELECT od FROM OrderDetails od WHERE od.order.orderId = ?1")
	    List<OrderDetails> findOrderDetailsByOrderId(int orderId);
}
