package com.asm.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	@Autowired
    private JdbcTemplate jdbcTemplate;

    public double getTotalRevenueForToday() {
        String sql = "SELECT SUM(total_amount) AS total_revenue " +
                     "FROM orders " +
                     "WHERE CAST(order_date AS DATE) = CAST(GETDATE() AS DATE)";
        Double result = jdbcTemplate.queryForObject(sql, Double.class);
        return result != null ? result : 0.0; // Trả về 0.0 nếu kết quả là null
    }
    public List<Map<String, Object>> getTop10MostPurchasedProducts() {
        String  sql = "SELECT TOP 6 " +
                "p.product_id, " +
                "p.product_name, " +
                "p.description, " +
                "p.price_product, " +
                "p.sale_price, " +
                "p.unit, " +
                "p.image_url, " +
                "p.import_date, " +
                "p.quantity, " +
                "p.exp_date, " +
                "p.category_id, " +
                "p.supplier_id, " +
                "p.is_active, " +
                "SUM(od.quantity) AS total_quantity_sold, " +
                "SUM(od.quantity * p.sale_price) AS total_revenue " +
                "FROM " +
                "products p " +
                "JOIN " +
                "order_details od ON p.product_id = od.product_id " +
                "GROUP BY " +
                "p.product_id, " +
                "p.product_name, " +
                "p.description, " +
                "p.price_product, " +
                "p.sale_price, " +
                "p.unit, " +
                "p.image_url, " +
                "p.import_date, " +
                "p.quantity, " +
                "p.exp_date, " +
                "p.category_id, " +
                "p.supplier_id, " +
                "p.is_active " +
                "ORDER BY " +
                "total_quantity_sold DESC";
        return jdbcTemplate.queryForList(sql);
    }
}
