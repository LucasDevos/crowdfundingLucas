package com.adaming.crowdBackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adaming.crowdBackend.models.Contributor;
import com.adaming.crowdBackend.models.Project;
import com.adaming.crowdBackend.services.ContributorService;
import com.adaming.crowdBackend.services.ContributorServiceImpl;
import com.adaming.crowdBackend.services.ProjectService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestRestAPIs {
	
	@Autowired
	ContributorService cs;
	
	@Autowired
	ProjectService ps;
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public String userAccess() {
		return "Dispaly Users Contents!";
	}

	@GetMapping("/pm")
	@PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
	public String projectManagementAccess() {
		return "Display PMs Content here.";
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Only Admin Content here. No unauthorized access!";
	}
	
	/*@GetMapping("/bidule")
	public String bidule() {
		return "bidule !";
	}
	
	@GetMapping("/number")
	public Long number() {
		return (long) 12345;
	}
	
	// renvoie tous les contributeurs
	@GetMapping(value="/contributors")
	public List<Contributor> allContributors(){
		System.out.println("on recupere les contributeurs");
		List<Contributor> myList = cs.findAll();
		System.out.println("my list is "+myList);
		return myList;
	}
	
	// renvoie tous les contributeurs
	@GetMapping(value="/projects")
	public List<Project> allProjects(){
		System.out.println("on recupere les projets");
		List<Project> myList = ps.findAll();
		System.out.println("my list is "+myList);
		return myList;
	}*/
}