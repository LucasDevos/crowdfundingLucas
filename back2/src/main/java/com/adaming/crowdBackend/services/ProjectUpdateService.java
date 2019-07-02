package com.adaming.crowdBackend.services;

import java.util.List;

import com.adaming.crowdBackend.models.ProjectUpdate;


public interface ProjectUpdateService {
	
	public List<ProjectUpdate> findAll();
	public ProjectUpdate findById(Long id);
	public ProjectUpdate save(ProjectUpdate update);
	public void deleteById(Long id);

}
