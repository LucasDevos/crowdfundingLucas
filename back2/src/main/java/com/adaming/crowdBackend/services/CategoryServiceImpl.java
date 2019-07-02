package com.adaming.crowdBackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adaming.crowdBackend.models.Category;
import com.adaming.crowdBackend.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryRepository cr;
	
	@Override
	public Category findById(Long id) {
		return cr.findById(id).get();
	}

	@Override
	public List<Category> findAll() {
		return cr.findAll();
	}

	@Override
	public Category save(Category cat) {
		return cr.save(cat);
	}

	@Override
	public void deleteById(Long id) {
		cr.deleteById(id);
	}

}
