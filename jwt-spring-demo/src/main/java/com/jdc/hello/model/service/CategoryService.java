package com.jdc.hello.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.hello.model.entity.Category;
import com.jdc.hello.model.repo.CategoryRepo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo repo;

	public List<Category> getAll() {
		return repo.findAll();
	}
}
