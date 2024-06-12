package com.asm.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asm.entity.Categories;
import com.asm.entity.Suppliers;
import com.asm.entity.dao.CatogoriesDAO;
import com.asm.entity.dao.SuppliersDAO;
import com.asm.services.CategoryService;
import com.asm.services.SuppliersService;

@Controller
@RequestMapping("app/admin")
public class SuppliersController {
	
	@Autowired
	SuppliersService suppliersService;
	@Autowired
	SuppliersDAO suppliersDAO;
	
	@RequestMapping("/suppliers_list")
	public String index(Model model) {
		List<Suppliers> suppliers = suppliersDAO.findAll();
		Suppliers item = new Suppliers();
		model.addAttribute("item", item);
		List<Suppliers> items = suppliersDAO.findAll();
		model.addAttribute("items", items);
		model.addAttribute("suppliers", suppliers);
		return "/admin/suppliers_list";
	}
	
	@RequestMapping("/suppliers/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		Suppliers item = suppliersDAO.findById(id).get();
		model.addAttribute("item", item);
		List<Suppliers> items = suppliersDAO.findAll();
		model.addAttribute("items", items);
		return "/admin/suppliers_list";
	}
	
	@RequestMapping("/suppliers/create")
	public String create(Suppliers supplier) {
		suppliersDAO.save(supplier);
		return "redirect:/app/admin/suppliers_list";
	}
	
	@RequestMapping("/suppliers/update")
	public String update(Suppliers supplier) {
		suppliersDAO.save(supplier);
		return "redirect:/app/admin/suppliers/edit/" + supplier.getSupplierId();
	}
	
	@RequestMapping("/suppliers/delete/{id}")
	public String create(@PathVariable("id") int id) {
		suppliersDAO.deleteById(id);
		return "redirect:/app/admin/suppliers_list";
	}
	
}
