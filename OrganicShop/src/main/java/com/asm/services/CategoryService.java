package com.asm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.entity.Categories;
import com.asm.entity.Units;
import com.asm.entity.dao.CatogoriesDAO;
import com.asm.entity.dao.UnitsDAO;


@Service
public class CategoryService {
	@Autowired
    private CatogoriesDAO categoryDAO;

    public List<Categories> getAllCategory() {
        return categoryDAO.findAll();
    }
    
    public Optional<Categories> getCategoryById(int id) {
        return categoryDAO.findById(id);
    }
    
    public void addCategory(Categories category) {
        categoryDAO.save(category);
    }
    
    public void updateCategory(Categories category) {
        categoryDAO.save(category);
    }
    
    public void deleteCategoryById(int id) {
        categoryDAO.deleteById(id);
    }
}
