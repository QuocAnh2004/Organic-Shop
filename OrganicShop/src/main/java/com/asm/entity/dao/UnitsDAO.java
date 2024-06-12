package com.asm.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.asm.entity.Units;

public interface UnitsDAO extends JpaRepository<Units, Integer> {
    // Bổ sung các phương thức tùy chỉnh nếu cần
}
