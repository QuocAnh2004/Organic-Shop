package com.asm.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asm.entity.Users;

public interface UsersDAO extends JpaRepository<Users, String> {
	// Bổ sung các phương thức tùy chỉnh nếu cần
	Users findByUsername(String username);

	@Query("SELECT u FROM Users u WHERE u.username = ?1")
	Users findByUserIdByUsername(String username);

	// Thêm phương thức này để lấy UserRoles dựa trên userId
	@Query("SELECT u.userRole FROM Users u WHERE u.userId = ?1")
	Users findRoleByUserId(String usersIdByUserName);
}
