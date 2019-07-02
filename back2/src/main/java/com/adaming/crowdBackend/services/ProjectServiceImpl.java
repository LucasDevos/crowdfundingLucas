package com.adaming.crowdBackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.adaming.crowdBackend.models.Project;
import com.adaming.crowdBackend.repositories.ContributorRepository;
import com.adaming.crowdBackend.repositories.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	ProjectRepository pr;
	
	@Autowired
	ContributorRepository cr;
	
	@Override
	public List<Project> findAll() {
		return pr.findAll();
	}

	@Override
	public Project findById(Long id) {
		return pr.findById(id).get();
	}

	@Override
	public Project save(Project proj) {
		return pr.save(proj);
	}

	@Override
	public void deleteById(Long id) {
		pr.deleteById(id);
	}
	
	

	@Override
	public List<Project> findAllWithSearch(String str, Sort sort) {
		return pr.findAllWithSearch(str, sort);
	}

	@Override
	public List<Project> findAllWithSearchForCategory(String str, Long catId, Sort sort) {
		return pr.findAllWithSearchForCategory(str, catId, sort);
	}

	@Override
	public List<Project> getUserProjects(Long id) {
		return pr.projectsWithUser(id);
	}

	@Override
	public List<Project> getUserBackedProjects(Long id) {
		return pr.projectsBackedByUser(id);
	}

	@Override
	public List<Project> findTop8ByOrderBySubmissionDateDesc() {
		return pr.findTop8ByOrderBySubmissionDateDesc();
	}

}
