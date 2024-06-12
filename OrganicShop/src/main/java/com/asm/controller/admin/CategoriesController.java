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
import com.asm.entity.dao.CatogoriesDAO;
import com.asm.services.CategoryService;

@Controller
@RequestMapping("app/admin")
public class CategoriesController {
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	CatogoriesDAO categoryDAO;
	
	@RequestMapping("/categories_list")
	public String index(Model model) {
		List<Categories> categories = categoryDAO.findAll();
		Categories item = new Categories();
		model.addAttribute("item", item);
		List<Categories> items = categoryDAO.findAll();
		model.addAttribute("items", items);
		model.addAttribute("categories", categories);
		return "/admin/category_list";
	}
	
	@RequestMapping("/category/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		Categories item = categoryDAO.findById(id).get();
		model.addAttribute("item", item);
		List<Categories> items = categoryDAO.findAll();
		model.addAttribute("items", items);
		return "/admin/category_list";
	}
	
	@RequestMapping("/category/create")
	public String create(Categories category) {
		categoryDAO.save(category);
		return "redirect:/app/admin/categories_list";
	}
	
	@RequestMapping("/category/update")
	public String update(Categories item) {
		categoryDAO.save(item);
		return "redirect:/app/admin/category/edit/" + item.getCategoryId();
	}
	
	@RequestMapping("/category/delete/{id}")
	public String create(@PathVariable("id") int id) {
		categoryDAO.deleteById(id);
		return "redirect:/app/admin/categories_list";
	}
	
}
