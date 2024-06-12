package com.asm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.entity.Units;
import com.asm.entity.dao.UnitsDAO;

@Service
public class UnitService {
	@Autowired
    private UnitsDAO unitDAO;

    public List<Units> getAllUnits() {
        return unitDAO.findAll();
    }
}
