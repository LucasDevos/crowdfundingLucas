package com.adaming.crowdBackend.controllers.api;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adaming.crowdBackend.models.Project;
import com.adaming.crowdBackend.models.ProjectUpdate;
import com.adaming.crowdBackend.services.ProjectService;
import com.adaming.crowdBackend.services.ProjectUpdateService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/crow/projects")
public class ProjectControllerApi {
	
	@Autowired
	private ProjectService ps;
	
	@Autowired
	private ProjectUpdateService pus;
	
	// --------- Méthodes pour récupérer les projets ------------------------------------
	
	// Retourne le projet d'id voulue
	@GetMapping(value = "/{id}")
	public Project findById(@PathVariable("id") Long id){
		return ps.findById(id);
	}
	
	// Retourne les quelques projets qui seront affichés sur la page d'accueil
	@GetMapping(value = "")
	public List<Project> homeProjects(){
		return ps.findTop8ByOrderBySubmissionDateDesc();
	}
	
	// Retourne tous les projets
	@GetMapping(value = "/all")
	public List<Project> allProjects(){
		System.out.println("on récupère tous les projets");
		return ps.findAll();
	}
	
	// Recherche de projets par terme, les résultats sont ordonnés selon le field + descending (bool)
	// ex : /search=o/field=title/descending=false --> retourne les projets dont le titre contient un o,
	//     classés par titre (ordre croissant)
	@GetMapping(value = "/search={str}/cat={catId}/field={field}/descending={isDesc}")
	public List<Project> searchProjects(@PathVariable("str") String str, @PathVariable("field") String field,
			@PathVariable("isDesc") Boolean isDesc, @PathVariable("catId") Long catId) {
		
		JpaSort sort;
		if (field.equals("creator"))
			sort = JpaSort.unsafe("creator.nickname");
		else
			sort = JpaSort.unsafe(field);
		
		if (catId == 9999) {
			if (isDesc)
				return ps.findAllWithSearch(str, sort.descending());
			else
				return ps.findAllWithSearch(str, sort);
		} else {
			if (isDesc)
				return ps.findAllWithSearchForCategory(str, catId, sort.descending());
			else
				return ps.findAllWithSearchForCategory(str, catId, sort);
		}
		
	}
	
	// Récupère les projets crées par ce Contributor
	@GetMapping(value="/user={id}")
	public List<Project> getUserProjects(@PathVariable("id") Long id) {
		return ps.getUserProjects(id);
	}
	
	// Récupère les projets pour lesquels ce Contributor a fait une donation
	@GetMapping(value="/backed/user={id}")
	public List<Project> getUserBackedProjects(@PathVariable("id") Long id) {
		return ps.getUserBackedProjects(id);
	}
	
	
	
	// ------------------ Sauvegarde / Suppression de projet ------------------------------------
	
	//Appelé lorsque l'on sauvegarde un nouveau projet
	@PostMapping(value = "/saveProject")
	public Project saveProject(@RequestBody Project proj) {
		System.out.println("on va sauvegarder le projet sur la DB");
		Instant instant = Instant.now();
		proj.setSubmissionDate(LocalDateTime.ofInstant(instant, ZoneOffset.UTC));
		return ps.save(proj);
	}
	
	//Suppression du projet voulu
	@GetMapping(value = "/delete={id}")
	public void deleteProject(@PathVariable("id") Long id){
		ps.deleteById(id);
	}
	
	
	
	// -------------------------- Autres --------------------------------------------------------
	
	// Sauvegarde la ProjectUpdate (et l'associe au projet avec l'id spécifié)
	@PostMapping(value = "/updates/save/projId={projId}")
	public ProjectUpdate saveUpdate(@RequestBody ProjectUpdate update, @PathVariable("projId") Long projId){
		update.setProject(ps.findById(projId));
		Instant instant = Instant.now();
		update.setDate(LocalDateTime.ofInstant(instant, ZoneOffset.UTC));
		return pus.save(update);
	}

}
