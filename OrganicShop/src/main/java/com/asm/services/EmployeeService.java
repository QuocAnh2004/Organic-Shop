package com.asm.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.entity.Employees;
import com.asm.entity.dao.EmployeesDAO;

import jakarta.validation.Valid;

@Service
public class EmployeeService {
	@Autowired
	private EmployeesDAO employeesDAO;

	public List<Employees> getAllEmployee() {
		return employeesDAO.findAll();
	}

	public void save(@Valid Employees employee) {
		// TODO Auto-generated method stub
		employeesDAO.save(employee);

	}

	public void deleteEmployeeById(int id) {
		employeesDAO.deleteById(id);
	}

	public Employees getEmployeeById(int id) {
		return employeesDAO.findById(id).orElse(null);
	}
	   public void deleteEmployeeFile(String uploadDir,String fileName) throws IOException {
	        Path filePath = Paths.get(uploadDir, fileName);
	        if (Files.exists(filePath)) {
	            Files.delete(filePath);
	        }
	    }
}
