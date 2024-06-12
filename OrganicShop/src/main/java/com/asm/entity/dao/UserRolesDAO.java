package com.asm.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.asm.entity.UserRoles;

public interface UserRolesDAO extends JpaRepository<UserRoles, Integer> {
    // Bổ sung các phương thức tùy chỉnh nếu cần
	UserRoles findByRoleId(int roleId);
}
