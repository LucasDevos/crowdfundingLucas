package com.adaming.crowdBackend.controllers.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adaming.crowdBackend.models.Category;
import com.adaming.crowdBackend.models.Contributor;
import com.adaming.crowdBackend.models.Project;
import com.adaming.crowdBackend.services.CategoryService;
import com.adaming.crowdBackend.services.ContributorService;
import com.adaming.crowdBackend.services.ProjectService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/crow/contributors")
public class ContributorControllerApi {

	@Autowired
	ContributorService cs;
	@Autowired
	CategoryService cats;
	@Autowired
	ProjectService ps;
	
	// -------- Méthodes pour récupérer 1 ou plusieurs contributeurs ---------------------------
	
	//renvoie le contributeur ayant ce nickname (utilisé pour retrouver l'user actuel)
	@GetMapping(value="/user={name}")
	public Contributor findUser(@PathVariable("name") String name) {
		System.out.println("le nom est "+name);
		return cs.findByNickname(name);
	}
	
	// renvoie tous les contributeurs
	@GetMapping(value="")
	public List<Contributor> allContributors(){
		System.out.println("on recupere les contributeurs");
		return cs.findAll();
	}
	
	// renvoie tous les contributeurs, classés par ordre alphabétique
	@GetMapping(value="/orderedByNickname")
	public List<Contributor> allContributorsByNickname(){
		return cs.findAllByNickname();
	}
	
	// renvoie tous les contributeurs, classés de plus donateur au moins donateur
	@GetMapping(value="/orderedByDonations")
	public List<Contributor> allContributorsByDonations(){
		return cs.findTop5ByDonations();
	}
	
	//renvoie tous les contributeurs, classés de plus donateur au moins donateur dans la catégorie voulue
	@GetMapping(value="/orderedByDonations/cat={categoryId}")
	public List<Contributor> allContributorsByDonationsInCategory(@PathVariable("categoryId") Long categoryId){
		return cs.findAllByDonationsInCategory(categoryId);
	}
	
	//renvoie tous les contributeurs, classés de plus donateur au moins donateur pour le projet voulu
	@GetMapping(value="/orderedByDonations/proj={projectId}")
	public List<Contributor> allContributorsByDonationsInProject(@PathVariable("projectId") Long projectId){
		return cs.findAllByDonationsInProject(projectId);
	}
	
	
	
	// ------------------ Méthodes permettant de retrouver les montants donnés par un / des contributeurs ---------
	
	// Renvoie le montant total des donations du contributeur
	@GetMapping(value="/id={id}")
	public Double amount(@PathVariable("id") Long id) {
		Contributor contributor = cs.findById(id);
		return null; //contributor.amountDonated();
	}
	
	// Renvoie le montant total des donations du contributeur pour la catégorie voulue
	@GetMapping(value="/id={id}/cat={categoryId}")
	public Double amountForCategory(@PathVariable("id") Long id, @PathVariable("categoryId") Long categoryId) {
		Contributor contributor = cs.findById(id);
		Category cat = cats.findById(categoryId);
		return null; //contributor.amountDonatedForCategory(cat);
	}
	
	// Renvoie les montants totaux des donations du contributeur pour chaque catégorie
	@GetMapping(value="/id={id}/allCategories")
	public List<Double> amountForAllCategories(@PathVariable("id") Long id) {
		Contributor contributor = cs.findById(id);
		List<Category> categories = cats.findAll();
		List<Double> amounts = new ArrayList<Double>();
		for (Category c : categories) {
			amounts.add(contributor.amountDonatedForCategory(c));
		}
		return amounts;
	}
	
	// Renvoie les montant de donation max, tous contributeurs confondus
	@GetMapping(value="/maxAmount")
	public Double maxAmount() {
		return cs.maxDonation().getTotal();
	}
	
	
	
	
	// ------------------- OLD --------------------------------------------------
	
	// Renvoie le montant total de donation donné par le contributeur voulu pour le projet voulu
	/*@GetMapping(value="/{id}/proj={projectId}")
	public Double amountForProject(@PathVariable("id") Long id, @PathVariable("projectId") Long projectId) {
		Contributor contributor = cs.findById(id);
		Project proj = ps.findById(projectId);
		return contributor.amountDonatedForProject(proj);
	}
	
	@GetMapping("/truc")
	public String bidule() {
		return "truc !";
	}*/
}
