package com.adaming.crowdBackend.controllers.api;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adaming.crowdBackend.models.Contributor;
import com.adaming.crowdBackend.models.Donation;
import com.adaming.crowdBackend.models.Project;
import com.adaming.crowdBackend.services.ContributorService;
import com.adaming.crowdBackend.services.DonationService;
import com.adaming.crowdBackend.services.ProjectService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/crow/donations")
public class DonationControllerApi {

	@Autowired
	DonationService ds;
	
	@Autowired
	ProjectService ps;
	
	@Autowired
	ContributorService cs;
	
	// --------- Méthodes pour récupérer les donations ----------------------------
	
	@GetMapping(value="")
	public List<Donation> allDonations(){
		return ds.findAll();
	}
	@GetMapping(value="/id_asc")
	public List<Donation> allDonationsByIdAsc(){
		return ds.findAllByOrderByIdAsc();
	}
	@GetMapping(value="/id_desc")
	public List<Donation> allDonationsByIdDesc(){
		return ds.findAllByOrderByIdDesc();
	}
	@GetMapping(value="/dateTime_asc")
	public List<Donation> allDonationsByDateTimeAsc(){
		return ds.findAllByOrderByDateTimeAsc();
	}
	@GetMapping(value="/dateTime_desc")
	public List<Donation> allDonationsByDateTimeDesc(){
		return ds.findAllByOrderByDateTimeDesc();
	}
	@GetMapping(value="/amount_asc")
	public List<Donation> allDonationsByAmountAsc(){
		return ds.findAllByOrderByAmountAsc();
	}
	@GetMapping(value="/amount_desc")
	public List<Donation> allDonationsByAmountDesc(){
		return ds.findAllByOrderByAmountDesc();
	}
	@GetMapping(value="/contributor_asc")
	public List<Donation> allDonationsByContributorAsc(){
		return ds.findAllByOrderByContributorAsc();
	}
	@GetMapping(value="/contributor_desc")
	public List<Donation> allDonationsByContributorDesc(){
		return ds.findAllByOrderByContributorDesc();
	}
	@GetMapping(value="/project_asc")
	public List<Donation> allDonationsByProjectAsc(){
		System.out.println("donations by project asc");
		return ds.findAllByOrderByProjectAsc();
	}
	@GetMapping(value="/project_desc")
	public List<Donation> allDonationsByProjectDesc(){
		return ds.findAllByOrderByProjectDesc();
	}
	@GetMapping(value="/donationType_asc")
	public List<Donation> allDonationsByTypeAsc(){
		System.out.println("donations by type asc");
		return ds.findAllByOrderByDonationTypeAsc();
	}
	@GetMapping(value="/donationType_desc")
	public List<Donation> allDonationsByTypeDesc(){
		return ds.findAllByOrderByDonationTypeDesc();
	}
	
	
	// -------------------- Autres méthodes liées aux donations -------------------------------
	
	// récupère tous les types de donations (PARTICULIER, ENTREPRISE...  récupéré depuis l'enum DonationTypeEnum)
	@GetMapping(value="/types")
	public List<String> allDonationTypes(){
		return ds.getDonationTypes();
	}
	
	// Sauvegarde la donation, met à jour le projet correspondant, et recalcule le total de donation de l'utilisateur
	@PostMapping(value="/save")
	public Donation save(@RequestBody Donation d) {
		
		// On met à jour le Contributor
		Contributor c = d.getContributor();
		c.getDonations().add(d);
		c.setTotal(c.amountDonated());
		System.out.println("le total de donations de "+c.getNickname()+" devient "+c.getTotal());
		
		// On met à jour le projet
		Instant instant = Instant.now();
		d.setDateTime(LocalDateTime.ofInstant(instant, ZoneOffset.UTC));
		Project p = d.getProject();
		p.getDonations().add(d);
		p.setDonationCurrent(p.getDonationCurrent()+d.getAmount());
		System.out.println("le total de donations de "+p.getTitle()+" devient "+p.getDonationCurrent());
		
		// On pousse nos changements sur la DB
		cs.save(c);
		ps.save(p);
		return ds.save(d);
		//return ds.save(d);
	}
}
