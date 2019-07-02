package com.adaming.crowdBackend.services;

import java.util.List;

import com.adaming.crowdBackend.models.Category;

public interface CategoryService {
	public Category findById(Long id);
	public List<Category> findAll();
	public Category save(Category cat);
	public void deleteById(Long id);
}
