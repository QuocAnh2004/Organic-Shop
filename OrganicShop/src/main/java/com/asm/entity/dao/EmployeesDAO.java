package com.asm.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.asm.entity.Employees;

public interface EmployeesDAO extends JpaRepository<Employees, Integer> {
    // Bổ sung các phương thức tùy chỉnh nếu cần
}
