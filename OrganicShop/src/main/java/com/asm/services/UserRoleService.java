package com.asm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.entity.UserRoles;
import com.asm.entity.dao.UserRolesDAO;

@Service
public class UserRoleService {
	@Autowired
	UserRolesDAO userRolesDAO;
	
	  public List<UserRoles> getAllRole() {
	        return userRolesDAO.findAll();
	    }
	  
	  public UserRoles getRoleById(int roleId)
	  {
		  return userRolesDAO.findByRoleId(roleId);
	  }

}
