package com.adaming.crowdBackend.services;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.adaming.crowdBackend.models.Project;

public interface ProjectService {
	
	public List<Project> findAll();
	public List<Project> findTop8ByOrderBySubmissionDateDesc();
	
	public Project findById(Long id);
	public Project save(Project proj);
	public void deleteById(Long id);
	
	public List<Project> findAllWithSearch(String str, Sort sort);
	public List<Project> findAllWithSearchForCategory(String str, Long catId, Sort sort);
	/*public List<Project> findAllWithSearchOrderByTitleAsc(String str);
	public List<Project> findAllWithSearchOrderByTitleDesc(String str);
	public List<Project> findAllWithSearchOrderByIdAsc(String str);
	public List<Project> findAllWithSearchOrderByIdDesc(String str);*/
	
	public List<Project> getUserProjects(Long id);
	public List<Project> getUserBackedProjects(Long id);
}
