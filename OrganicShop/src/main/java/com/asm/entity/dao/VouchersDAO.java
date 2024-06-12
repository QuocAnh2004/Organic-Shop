package com.asm.entity.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.asm.entity.Vouchers;

public interface VouchersDAO extends JpaRepository<Vouchers, Integer> {
    // Bổ sung các phương thức tùy chỉnh nếu cần
    Optional<Vouchers> findByVoucherCode(String voucherCode);
    
    @Modifying
    @Transactional
    @Query("UPDATE Vouchers v SET v.quantity = :quantity WHERE v.voucherCode = :voucherCode")
    void updateVoucherQuantityByVoucherCode(String voucherCode, int quantity);

}
