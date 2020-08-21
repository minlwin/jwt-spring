package com.jdc.hello.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.hello.model.entity.Category;
import com.jdc.hello.model.service.CategoryService;

@RestController
@RequestMapping("categories")
public class CategoryApi {

	@Autowired
	private CategoryService categoreis;
	
	@GetMapping
	public List<Category> getAll() {
		return categoreis.getAll();
	}
}
