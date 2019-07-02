package com.adaming.crowdBackend.controllers.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adaming.crowdBackend.models.Category;
import com.adaming.crowdBackend.services.CategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/crow/categories")
public class CategoryControllerApi {

	@Autowired
	CategoryService cats;
	
	// Retrouve toutes les catégories
	@GetMapping(value="")
	public List<Category> allCategories(){
		return cats.findAll();
	}
	
	// Sauvegarde la catégorie donnée
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value="/save")
	public Category save(@RequestBody Category c){
		return cats.save(c);
	}
}
