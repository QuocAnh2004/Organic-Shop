//package com.asm.entity.dao;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import com.asm.entity.Customers;
//
//public interface CustomersDAO extends JpaRepository<Customers, Integer> {
//    // Tìm khách hàng bằng userId
//    @Query("SELECT c FROM Customers c WHERE c.user.userId = ?1")
//    Customers findByUserId(String userId);
//
//    // Tìm khách hàng bằng username
//    @Query("SELECT c FROM Customers c WHERE c.user.username = ?1")
//    Customers findByUsername(String username);
//
//    Customers findByEmail(String email);
//}
package com.asm.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.asm.entity.Customers;

public interface CustomersDAO extends JpaRepository<Customers, Integer> {
    @Query("SELECT c FROM Customers c WHERE c.user.userId = ?1")
    Customers findByUserId(String userId);

//    @Query("SELECT c FROM Customers c WHERE c.user.username = ?1")
//    Customers findByUsername(String username);
    @Query("SELECT c FROM Customers c WHERE c.user.username = ?1")
    Customers findByUsername(String username);

    Customers findByEmail(String email);
}
