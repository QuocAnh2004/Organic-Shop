package com.asm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.entity.dao.UsersDAO;

@Service
public class UserService {
	@Autowired
	UsersDAO usersDAO;
	
    public void deleteUserById(String id) {
        usersDAO.deleteById(id);
    }

}
