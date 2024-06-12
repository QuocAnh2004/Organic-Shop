package com.asm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.entity.Suppliers;
import com.asm.entity.Units;
import com.asm.entity.dao.SuppliersDAO;
import com.asm.entity.dao.UnitsDAO;

@Service
public class SuppliersService {
	@Autowired
    private SuppliersDAO suppliersDAO;

    public List<Suppliers> getAllSuppliers() {
        return suppliersDAO.findAll();
    }
    
    public Optional<Suppliers> getSuppliersById(int id){
    	return suppliersDAO.findById(id);
    }
}
