package com.asm.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.asm.entity.Suppliers;

public interface SuppliersDAO extends JpaRepository<Suppliers, Integer> {
    // Bổ sung các phương thức tùy chỉnh nếu cần
}
