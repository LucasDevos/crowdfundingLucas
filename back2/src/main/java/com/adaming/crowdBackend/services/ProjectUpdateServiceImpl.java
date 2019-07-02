package com.adaming.crowdBackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adaming.crowdBackend.models.ProjectUpdate;
import com.adaming.crowdBackend.repositories.ProjectUpdateRepository;

@Service
public class ProjectUpdateServiceImpl implements ProjectUpdateService {

	@Autowired
	ProjectUpdateRepository pur;
	
	@Override
	public List<ProjectUpdate> findAll() {
		return pur.findAll();
	}

	@Override
	public ProjectUpdate findById(Long id) {
		return pur.findById(id).get();
	}

	@Override
	public ProjectUpdate save(ProjectUpdate update) {
		return pur.save(update);
	}

	@Override
	public void deleteById(Long id) {
		pur.deleteById(id);
	}

}
