package com.adaming.crowdBackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adaming.crowdBackend.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
