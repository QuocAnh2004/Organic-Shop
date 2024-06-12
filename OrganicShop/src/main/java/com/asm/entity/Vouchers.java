package com.asm.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Vouchers")
public class Vouchers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voucher_id")
    private int voucherId;

    @Column(name = "voucher_code", nullable = false, length = 50)
    private String voucherCode;

    @Column(name = "discount_amount", nullable = false)
    private float discountAmount;

    @Column(name = "quantity_voucher", nullable = false)
    private int quantity;

    @Column(name = "expiry_date_voucher", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiryDate;

    @Column(name = "is_active_voucher", nullable = false)
    private boolean isActive;

    // Getters and setters
}

